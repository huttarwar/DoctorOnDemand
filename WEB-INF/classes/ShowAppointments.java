import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.*; 
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
@WebServlet("/ShowAppointments")

public class ShowAppointments extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String para = request.getParameter("Show");
		
		HashMap<Integer,OrderPayment> appoint = new HashMap<Integer,OrderPayment>();
		
		try
		{
			appoint = MySqlDataStoreUtilities.getOrder();
		}
		catch(Exception e)
		{
			
		}
		
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to access the Data Analytics feature");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='AppointmentDetails' action='ShowAppointments' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Appointments information</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show the patient information for scheduling appointments&nbsp;</td>");
		pw.print("<td><input type='submit' name='Show' value='Info' class='btnbuy'></td></tr></table>");
		pw.print("</form></div></div>");	
		
		
		if(para.equals("Info")){
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Details</a>");
		pw.print("</h2><div class='entry'><table style = 'width:100%'");
		pw.print("<tr>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Order Number</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Service Name");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Email");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Phone Number");
		pw.println("</th>");
		for (Map.Entry<Integer,OrderPayment> entry : appoint.entrySet()) {
		OrderPayment op = entry.getValue();
		int id = op.getOrderId();
		if(id > 0){
 		pw.print("<tr>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println(op.getOrderId());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("	"+op.getOrderName());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     "+op.getEmail());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     "+op.getPhone());
		pw.println("</td>");
		pw.println("</tr>");
        }
		}
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
        }
		
} 
		
	 
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}
