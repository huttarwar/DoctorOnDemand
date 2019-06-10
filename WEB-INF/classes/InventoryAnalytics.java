import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;
import javax.servlet.http.HttpSession;
@WebServlet("/InventoryAnalytics")

public class InventoryAnalytics extends HttpServlet {
	
	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
				
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}
		
						
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBarStore.html");
		pw.print("<form name ='Inventory' action='InventoryData' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Appointment information for services</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show appointments of all services &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Appointments' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show table of services which have discounts &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Discounts' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show per service sales &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Sales' class='btnbuy'></td></tr></table>");
		pw.print("</form></div></div>");
		
		pw.print("<form name ='Inventory' action='InventoryData' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Appointment chart for all services</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show bar chart for services and their appointments &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Chart' class='btnbuy'></td></tr></table>");
			pw.print("</form></div></div>");
		utility.printHtml("Footer.html");
	
	
	
			
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
