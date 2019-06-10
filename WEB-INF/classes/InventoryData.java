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
@WebServlet("/InventoryData")

public class InventoryData extends HttpServlet {
	

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/
	
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String para = request.getParameter("ShowAll");
		
		HashMap<String,Inventory> inventories = new HashMap<String,Inventory> ();
		
		try
		{
			inventories = MySqlDataStoreUtilities.getInventory();
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
		utility.printHtml("LeftNavigationBarStore.html");
		pw.print("<form name ='Inventory' action='InventoryData' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Appointment information for services</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show appointments of all services &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Appointments' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show table of services with discounts &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Discounts' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show service sales &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Sales' class='btnbuy'></td></tr></table>");
		
		pw.print("</form></div></div>");
		
		pw.print("<form name ='Inventory' action='InventoryData' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Appointment chart for services</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show bar chart for services and their appointments &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Chart' class='btnbuy'></td></tr></table>");
			pw.print("</form></div></div>");
		
		if(para.equals("Appointments")){
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>History and Records for all services</a>");
		pw.print("</h2><div class='entry'><table style = 'width:100%'");
		pw.print("<tr>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Service Name");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Price");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Items available");
		pw.println("</th>");
		for (Map.Entry<String, Inventory> entry : inventories.entrySet()) {
		Inventory inv = entry.getValue();
		double price = inv.getPrice();
		if(price > 0){
 		pw.print("<tr>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println(inv.getName());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     $"+inv.getPrice());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     "+inv.getAppointments());
		pw.println("</td>");
		pw.println("</tr>");
        }
		}
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
        }
		else if(para.equals("Discounts")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>History and Records for all services</a>");
		pw.print("</h2><div class='entry'><table style = 'width:100%'");
		pw.print("<tr>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Service Name");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Price");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Discount");
		pw.println("</th>");
		for (Map.Entry<String, Inventory> entry : inventories.entrySet()) {
		Inventory inv = entry.getValue();
		Double disc = inv.getDiscount();
		System.out.println(""+inv.getDiscount());
		if(disc > 0){
 		pw.print("<tr>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println(inv.getName());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     $"+inv.getPrice());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     "+disc+"%");
		pw.println("</td>");
		pw.println("</tr>");
        }
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
		}
		else if (para.equals("Sales")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>History and Records for all services</a>");
		pw.print("</h2><div class='entry'><table style = 'width:100%'");
		pw.print("<tr>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Product Name");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Price");
		pw.println("</th>");
		pw.println("<th style='font-size: 20px;border: 3px solid black;'>");
		pw.println("Sales");
		pw.println("</th>");
		for (Map.Entry<String, Inventory> entry : inventories.entrySet()) {
		Inventory inv = entry.getValue();
		double reb = inv.getsales();
		if(reb > 0){
 		pw.print("<tr>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println(inv.getName());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     $"+inv.getPrice());
		pw.println("</td>");
		pw.println("<td style='font-size: 20px;border: 3px solid black;'>");
		pw.println("     $"+inv.getsales() );
		pw.println("</td>");
		pw.println("</tr>");
        }
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
		}
		else{
			//displayPage(request, response, pw);
			pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");

			pw.println("<script type='text/javascript'>");



										// Load the Visualization API and the corechart package.

			pw.println("google.charts.load('current', {'packages':['corechart']});");



										// Set a callback to run when the Google Visualization API is loaded.

			pw.println("google.charts.setOnLoadCallback(drawChart);");



										// Callback that creates and populates a data table,

	

			pw.println("function drawChart() {");



										// Create the data table.

			pw.println("var data = new google.visualization.DataTable();");

			pw.println("data.addColumn('string', 'productName');");

			pw.println("data.addColumn('number', 'Quantity');");

			pw.println(" data.addRows([");
			StringBuilder sb = new StringBuilder("");
			for (Map.Entry<String, Inventory> entry : inventories.entrySet()) {
			Inventory inv = entry.getValue();			
			sb.append("['" + inv.getName() + "'," + inv.getAppointments() + "],");
			//pw.print(" ['" + inv.getName() + "'," + inv.getQuantity() + "],");
			}
			System.out.println(""+sb);
			pw.println(sb);
			pw.println("]);");
			

										// Set chart options

										pw.println(" var options = {'title':'Appointment Chart',");

										pw.println(" 'width':900,");

										pw.println(" 'height':1500,");

										pw.println(" 'hAxis':{format:'0'}};");

										// Instantiate and draw our chart, passing in some options.

										pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");

										pw.println("  chart.draw(data, options);}");

										pw.println(" </script>");
																		
										pw.println("<div id='chart_div'></div>");
										
		 
	
			
		utility.printHtml("Footer.html");			
	}
} 
		
	 
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}
