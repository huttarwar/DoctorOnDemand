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
@WebServlet("/DataAnalytics")

public class DataAnalytics extends HttpServlet {
	static DBCollection myReviews;
	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		HttpSession session = request.getSession(true);		
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
							
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}
		
						
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

		pw.print("<form name ='Data Analytics' action='AllProducts' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Data Analytics</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show all Services and their ratings &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Show' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show reviews with ratings more than 3 &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='ShowReview3' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show services with price above 200 and rating 5 &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Show200' class='btnbuy'></td></tr></table>");
		pw.print("</form></div></div>");
		utility.printHtml("Footer.html");
	
	
	
			
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
