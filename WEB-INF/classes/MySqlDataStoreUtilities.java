import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Date;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;

public static void getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorondemand?autoReconnect=true&useSSL=false","root","root");							
	}
	catch(Exception e)
	{
	
	}
}

public static void Insertservices()
{
	try{
		
		getConnection();
		String truncatetableprod = "delete from  servicedetails;";
        PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
        psttprod.executeUpdate();
		String insertProductQurey = "INSERT INTO  servicedetails(ServiceType,Id,servicename,clinicName,DoctorName,Price,TypeImage,Type,Location,Discount,latt,longi)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		
		
		for(Map.Entry<String,Physician> entry : SaxParserDataStore.physicianservices.entrySet())
		{   
	        Physician phi = entry.getValue();
			String name = "physicians";
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,phi.getId());
			pst.setString(3,phi.getName());
			pst.setString(4,phi.getclinicname());
			pst.setString(5,phi.getDoctorname());
			pst.setDouble(6,phi.getPrice());
			pst.setString(7,phi.getImage());
			pst.setString(8,phi.getType());
			pst.setString(9,phi.getLocation());
			pst.setDouble(10,phi.getDiscount());
			pst.setDouble(11,phi.getLat());
			pst.setDouble(12,phi.getLong());
		
			
			pst.executeUpdate();
		
		}
		
		for(Map.Entry<String,Dental> entry : SaxParserDataStore.dentals.entrySet())
		{   
			String name = "dentals";
	        Dental den = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,den.getId());
			pst.setString(3,den.getName());
			pst.setString(4,den.getclinicname());
			pst.setString(5,den.getDoctorname());
			pst.setDouble(6,den.getPrice());
			pst.setString(7,den.getImage());
			pst.setString(8,den.getType());
			pst.setString(9,den.getLocation());
			pst.setDouble(10,den.getDiscount());
			pst.setDouble(11,den.getLat());
			pst.setDouble(12,den.getLong());
			
			pst.executeUpdate();
			
			
			
		}
		
		for(Map.Entry<String,Eyecare> entry : SaxParserDataStore.eyecares.entrySet())
		{   
			String name = "eyecares";
	        Eyecare eye = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,eye.getId());
			pst.setString(3,eye.getName());
			pst.setString(4,eye.getclinicname());
			pst.setString(5,eye.getDoctorname());
			pst.setDouble(6,eye.getPrice());
			pst.setString(7,eye.getImage());
			pst.setString(8,eye.getType());
			pst.setString(9,eye.getLocation());
			pst.setDouble(10,eye.getDiscount());
			pst.setDouble(11,eye.getLat());
			pst.setDouble(12,eye.getLong());
			pst.executeUpdate();
					
		}	
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
} 

public static HashMap<String,Physician> getphysicians()
{	
	HashMap<String,Physician> hm=new HashMap<String,Physician>();
	try 
	{
		getConnection();
		
		String selectPhysician="select * from  servicedetails where ServiceType=?";
		PreparedStatement pst = conn.prepareStatement(selectPhysician);
		pst.setString(1,"physicians");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Physician  phi = new Physician(rs.getString("servicename"),rs.getString("clinicName"),rs.getString("DoctorName"),rs.getDouble("Price"),rs.getString("TypeImage"),rs.getString("Type"),rs.getString("Location"),rs.getDouble("Discount"),rs.getDouble("latt"),rs.getDouble("longi"));
				hm.put(rs.getString("Id"),  phi);
				 phi.setId(rs.getString("Id"));
				System.out.println(rs.getString("Id"));
				
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



public static HashMap<String,Dental> getDentals()
{	
	HashMap<String,Dental> hm=new HashMap<String,Dental>();
	try 
	{
		getConnection();
		
		String selectDental="select * from  servicedetails where ServiceType=?";
		PreparedStatement pst = conn.prepareStatement(selectDental);
		pst.setString(1,"dentals");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Dental den = new Dental(rs.getString("servicename"),rs.getString("clinicName"),rs.getString("DoctorName"),rs.getDouble("Price"),rs.getString("TypeImage"),rs.getString("Type"),rs.getString("Location"),rs.getDouble("Discount"),rs.getDouble("latt"),rs.getDouble("longi"));
				hm.put(rs.getString("Id"), den);
				den.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Eyecare> getEyecares()
{	
	HashMap<String,Eyecare> hm=new HashMap<String,Eyecare>();
	try 
	{
		getConnection();
		
		String selectEyecare="select * from  servicedetails where ServiceType=?";
		PreparedStatement pst = conn.prepareStatement(selectEyecare);
		pst.setString(1,"eyecares");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Eyecare eye = new Eyecare(rs.getString("servicename"),rs.getString("clinicName"),rs.getString("DoctorName"),rs.getDouble("Price"),rs.getString("TypeImage"),rs.getString("Type"),rs.getString("Location"),rs.getDouble("Discount"),rs.getDouble("latt"),rs.getDouble("longi"));
				hm.put(rs.getString("Id"), eye);
				eye.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



public static String addproducts(String ServiceType,String Id,String servicename,String clinicname,String DoctorName,double Price,String TypeImage,String Type,String Location,double Discount,double latt,double longi)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  servicedetails(ServiceType,Id,servicename,clinicname,DoctorName,Price,TypeImage,Type,Location,Discount,latt,longi)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		   
			String name = ServiceType;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,Id);
			pst.setString(3,servicename);
			pst.setString(4,clinicname);
			pst.setString(5,DoctorName);
			pst.setDouble(6,Price);
			pst.setString(7,TypeImage);
			pst.setString(8,Type);
			pst.setString(9,Location);
			pst.setDouble(10,Discount);
			pst.setDouble(11,latt);
			pst.setDouble(12,longi);
			
			pst.executeUpdate();			
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateproducts(String ServiceType,String Id,String servicename,String clinicname,String DoctorName,double Price,String TypeImage,String Type,String Location,double Discount,double latt,double longi)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE servicedetails SET servicename=?,clinicname=?,DoctorName=?,Price=?,TypeImage=?,Type=?,Location=?,Discount=?,latt=?,longi=? where Id =?;" ;
		

		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,servicename);
			pst.setString(2,clinicname);
			pst.setString(3,DoctorName);
			pst.setDouble(4,Price);
			pst.setString(5,TypeImage);
			pst.setString(6,Type);
			pst.setString(7,Location);
			pst.setDouble(8,Discount);
			pst.setDouble(9,latt);
			pst.setDouble(10,longi);
			pst.setString(11,Id);
			
			pst.executeUpdate();
			
			String updateRecords = "UPDATE records SET servicename=?,Price=?,Discount=? where Id =?;" ;
			PreparedStatement pst1 = conn.prepareStatement(updateRecords);
			pst1.setString(1,servicename);
			pst1.setDouble(2,Price);
			pst1.setDouble(3,Discount);
			pst1.setString(4,Id);
			
			pst1.executeUpdate();
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteproducts(String Id)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from servicedetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,Id);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
} 


 public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from orders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}
 
public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,LocalDate date,String email ,String phonenumber)
{
	try
	{
	
		getConnection();
		String date1 = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String insertIntoCustomerOrderQuery = "INSERT INTO orders(OrderId,userName,orderName,orderPrice,userAddress,creditCardNo,date,email,phonenumber)"
		+ "VALUES (?,?,?,?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
	    pst.setString(6,creditCardNo);
		pst.setString(7,date1);
		pst.setString(8,email);
		pst.setString(9,phonenumber);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
} 

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from orders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("orderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
				System.out.println(""+arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("orderId"));		
	
			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("orderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditcardNo"),rs.getString("date"),rs.getString("email"),rs.getString("phonenumber"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	
	return orderPayments;
}


public static void insertUser(User user)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO insertuser(firstname,lastname,username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,user.getfirstName());
		pst.setString(2,user.getlastName());
		pst.setString(3,user.getName());
		pst.setString(4,user.getPassword());
		pst.setString(5,user.getRePassword());
		pst.setString(6,user.getUsertype());
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  insertuser";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username"),rs.getString("password"),rs.getString("repassword"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
} 
public static int updateProducts(String id ,Double price,int desh)
{
	if(desh==0)
	{
	try{
		System.out.println(""+id+price+desh);
		getConnection();
		PreparedStatement pst = null;
		String ProductsQuery ="select * from records where Id='"+id+"'";
pst = conn.prepareStatement(ProductsQuery);
ResultSet rs = pst.executeQuery();
int quantity = 0;
double productsale = 0;
while(rs.next()){
quantity = rs.getInt("Appointments");	
productsale = rs.getDouble("sales");
} 
int appointments =  quantity + 1;
double sales = productsale + price;

String updateProductsQuery = "update records set Appointments='"+appointments+"',sales ='"+sales+"' where Id='"+id+"'";
pst = conn.prepareStatement(updateProductsQuery);
pst.executeUpdate();

pst.close();
	
return 1;	
}catch(Exception e){
e.printStackTrace();
return -1;
}	
}
else
{
try{
getConnection();
PreparedStatement pst = null;
String ProductsQuery ="select * from records where Id='"+id+"'";
pst = conn.prepareStatement(ProductsQuery);
ResultSet rs = pst.executeQuery();
int quantity = 0;

double productsale = 0;
while(rs.next()){
quantity = rs.getInt("Appointments");	
productsale = rs.getDouble("sales");
} 
int appointments =  quantity - 1;
double sales = productsale - price;

String updateProductsQuery = "update records set Appointments='"+appointments+"',sales ='"+sales+"' where Id='"+id+"'";
pst = conn.prepareStatement(updateProductsQuery);
pst.executeUpdate();

pst.close();
	
return 1;	
}catch(Exception e){
e.printStackTrace();
return -1;
}		
}
}

public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			
		    String selectproduct="select * from  servicedetails";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Product p = new Product(rs.getString("ServiceType"),rs.getString("Id"),rs.getString("servicename"),rs.getString("clinicName"),rs.getString("DoctorName"),rs.getDouble("Price"),rs.getString("TypeImage"),rs.getString("Type"),rs.getString("Location"),rs.getDouble("Discount"),rs.getDouble("latt"),rs.getDouble("longi"));
				hm.put(rs.getString("Id"), p);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	} 
	
	public static HashMap<String,Inventory> getInventory()
	{	
	HashMap<String,Inventory> hm=new HashMap<String,Inventory>();
	try 
	{
		getConnection();
		
		String selectinvent="select * from  records";
		PreparedStatement pst = conn.prepareStatement(selectinvent);
		ResultSet rs = pst.executeQuery();
				System.out.println("Query executed");

		while(rs.next())
		{	
				Inventory inventory = new Inventory(rs.getString("Id"),rs.getString("servicename"),rs.getDouble("Price"),rs.getInt("Appointments"),rs.getDouble("Sales"),rs.getDouble("Discount"));
				hm.put(rs.getString("Id"), inventory);
				System.out.println("Query executed"+rs.getString("Id"));			

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static ArrayList<Inventory> getInventorylist(){
	
	ArrayList<Inventory> inlist = new ArrayList<Inventory>();
	try 
	{
		System.out.println("Query");
		getConnection();
		System.out.println("Query executed");
	String selectinvent="select * from  records";
		PreparedStatement pst = conn.prepareStatement(selectinvent);
		ResultSet rs = pst.executeQuery();
				System.out.println("Query executed");

		while(rs.next())
		{	
				
				Inventory inventory = new Inventory(rs.getString("Id"),rs.getString("servicename"),rs.getDouble("Price"),rs.getInt("Appointments"),rs.getDouble("Sales"),rs.getDouble("Discount"));
				inlist.add(inventory);
				System.out.println("increment");
				
		}
	}
	catch(Exception e)
	{
	}
	System.out.println(""+inlist);
	return inlist;	

}

public static HashMap<String,Daily> getDailySales()
{	
	HashMap<String,Daily> hm=new HashMap<String,Daily>();
	try 
	{
		getConnection();
		
		String selectinvent="SELECT date, SUM(orderPrice) AS dailySum FROM orders GROUP BY date";
		PreparedStatement pst = conn.prepareStatement(selectinvent);
		ResultSet rs = pst.executeQuery();
				System.out.println("Query executed");

		while(rs.next())
		{	
				Daily daily = new Daily(rs.getString("date"),rs.getDouble("dailySum"));
				hm.put(rs.getString("date"), daily);
							

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}
public static HashMap<Integer,OrderPayment> getOrder()
{	

	HashMap<Integer,OrderPayment> orderPayments=new HashMap<Integer,OrderPayment>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from orders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		while(rs.next())
		{	
			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("orderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditcardNo"),rs.getString("date"),rs.getString("email"),rs.getString("phonenumber"));
			orderPayments.put(rs.getInt("orderId"), order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	
	return orderPayments;
}
	
}	