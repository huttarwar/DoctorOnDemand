import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session1 = request.getSession(true);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//String usertype ="";
		HttpSession session = request.getSession(true);	
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
						
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		
		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		String email=request.getParameter("email");
		String Customername= request.getParameter("Name");
		String phone=request.getParameter("phone");
		String id = request.getParameter("itemid");
		System.out.print("the user address is" +userAddress);
		System.out.print(creditCardNo);
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() && !email.isEmpty() && !phone.isEmpty())
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{
				LocalDate date = LocalDate.now();
				//set the parameter for each column and execute the prepared statement
				String date1 = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo, Customername, date1,email,phone,id);
			}

			//remove the order details from cart after processing
			LocalDate dateTime = LocalDate.now();
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			if(session.getAttribute("usertype").equals("patient")){
			utility.printHtml("LeftNavigationBar.html");
			}
			else if(session.getAttribute("usertype").equals("doctor")){
			utility.printHtml("LeftNavigationBarStore.html");
			}
			else{
			utility.printHtml("LeftNavigationBar.html");
			}
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your Service request");
			pw.print("&nbsp&nbsp");  
			pw.print("has been stored ");
			pw.print("<br>Your Service request is "+(orderId));
			pw.print("<br>Your service will be confirmed by " + dateTime.plusDays(1));
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Service</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red;font-size: 18px;'>You need to enter all the details. Please enter the valid information</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
