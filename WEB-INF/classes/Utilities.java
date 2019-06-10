import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				if(session.getAttribute("usertype").equals("doctor"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'>Add Service</span></a></li>"
						+ "<li><a href='ProductModify?button=Updateproduct'>Update Service</span></a></li>"
						+"<li><a href='ProductModify?button=Deleteproduct'>Delete Service</span></a></li>"
						+ "<li><a>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'>Logout</span></a></li>";
				}
				else if(session.getAttribute("usertype").equals("customerservice")){
					result = result + "<li><a href='Specialist'>Appointments</span></a></li>"
						+ "<li><a>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'>Logout</span></a></li>";
				}
				else
				{
				result = result + "<li><a href='ViewOrder'>ViewOrder</span></a></li>"
						+ "<li><a>Hello,"+username+"</a></li>"
						+ "<li><a href='Account'>Account</a></li>"
					+ "<li><a href='Logout'>Logout</a></li>";
				}
			}
			else
				result = result +"<li><a href='ViewOrder'>ViewOrder</a></li>"+ "<li><a href='Login'>Login</a></li>";
				result = result +"<li><a href='Cart'>Cart("+CartCount()+")</a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} 
		else
				pw.print(result);
	}

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\DoctorOnDemand\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			try
			{
				orderPayments =MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=entry.getKey();
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("physicians")){
			Physician physician;
			physician = SaxParserDataStore.physicianservices.get(name);
			OrderItem orderitem = new OrderItem(physician.getId(),physician.getName(),physician.getclinicname(), physician.getPrice(), physician.getImage(), physician.getDoctorname(), physician.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("dentals")){
			Dental dental = null;
			dental = SaxParserDataStore.dentals.get(name);
			OrderItem orderitem = new OrderItem(dental.getId(),dental.getName(),dental.getclinicname(), dental.getPrice(), dental.getImage(), dental.getDoctorname(), dental.getDiscount());
			orderItems.add(orderitem);
		}
		
		if(type.equals("eyecares")){
			Eyecare eyecare = null;
			eyecare = SaxParserDataStore.eyecares.get(name);
			OrderItem orderitem = new OrderItem(eyecare.getId(),eyecare.getName(),eyecare.getclinicname(), eyecare.getPrice(), eyecare.getImage(), eyecare.getDoctorname(), eyecare.getDiscount());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,String orderName,double orderPrice,String userAddress,String creditCardNo,String customer, String date,String email, String phone, String id){
		System.out.println(""+id);
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				orderPayments=MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,date,email,phone);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
			System.out.println("Writing order");
				MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,LocalDate.now(),email,phone);
				int ham = 0;
				int inventory = MySqlDataStoreUtilities.updateProducts(id,orderPrice,ham);
			}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
		
	}
	
	public String storeReview(String servicename,String producttype,String doctorname,String reviewrating,String reviewdate,String reviewtext,String zipcode,String productprice,String cliniccity,String discount,String userage,String usergender,String useroccupation,String clinicstate){
	String message=MongoDBDataStoreUtilities.insertReview(servicename,username(),producttype,doctorname,reviewrating,reviewdate,reviewtext,zipcode,productprice,cliniccity,discount,userage,usergender,useroccupation,clinicstate);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(servicename)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(servicename, arr);
		}
		ArrayList<Review> listReview = reviews.get(servicename);		
		Review review = new Review(servicename,username(),producttype,doctorname,reviewrating,reviewdate,reviewtext,zipcode,productprice,cliniccity,discount,userage,usergender,useroccupation,clinicstate);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}

	
	public HashMap<String, Physician> getphysicians(){
			HashMap<String, Physician> hm = new HashMap<String, Physician>();
			hm.putAll(SaxParserDataStore.physicianservices);
			return hm;
	}

	public HashMap<String, Dental> getdentals(){
			HashMap<String, Dental> hm = new HashMap<String, Dental>();
			hm.putAll(SaxParserDataStore.dentals);
			return hm;
	}


	public HashMap<String, Eyecare> geteyecares(){
			HashMap<String, Eyecare> hm = new HashMap<String, Eyecare>();
			hm.putAll(SaxParserDataStore.eyecares);
			return hm;
	}
	

}
