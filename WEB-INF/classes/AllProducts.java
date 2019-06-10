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
@WebServlet("/AllProducts")

public class AllProducts extends HttpServlet {
	static DBCollection myReviews;
	ArrayList <Productratings> productratings = new ArrayList <Productratings> ();
	ArrayList<Review> review3 = new ArrayList<Review>();
	ArrayList<Review> review1000 = new ArrayList<Review>();	
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String para = request.getParameter("ShowAll");
		productratings      = MongoDBDataStoreUtilities.productsratings();
		review3 = MongoDBDataStoreUtilities.getReview3();
		review1000 = MongoDBDataStoreUtilities.getProduct1000();
		String userName = "";
		String reviewRating = "";
		String reviewDate;
		String reviewText = "";	
		String price = "";
		String city ="";
		String productType;
		String retailer;
		String retailerpin;
		String state;
		String discount;
		String manufacturer;
		String rebate;
		String age;
		String gender;
		String occupation;
		String productName;
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to access the Data Analytics feature");
			response.sendRedirect("Login");
			return;
		}
		
						
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBarStore.html");
		pw.print("<form name ='Data Analytics' action='AllProducts' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Data Analytics</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table align='center'><tr><td style='font-size:20px;'>Show services with ratings &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Show' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show reviews with ratings more than 3 &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='ShowReview3' class='btnbuy'></td></tr>");
			pw.print("<tr><td style='font-size:20px;'>Show services with price above 200 and rating 5 &nbsp;</td>");
			pw.print("<td><input type='submit' name='ShowAll' value='Show200' class='btnbuy'></td></tr></table>");
		pw.print("</form></div></div>");
		
		if(para.equals("Show")){
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Show all Services and their ratings</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		Iterator itr2 = productratings.iterator();
        while(itr2.hasNext()) {
         Productratings best = (Productratings)itr2.next();
 		pw.print("<tr>");
		pw.print("<td style='font-size: 20px;'>");
		pw.print(best.getProductname());
		pw.print("</td>");
		pw.print("<td style='font-size: 20px;'>");
		pw.print(best.getRating());
		pw.print("</td>");
		pw.print("</tr>");
        }}
		else if(para.equals("ShowReview3")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Showing reviews with ratings above 3</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		Iterator itr2 = review3.iterator();
        while(itr2.hasNext()) {
			Review r = (Review)itr2.next();
		pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> Product Name: </td>");
				productName = r.getserviceName();
				pw.print("<td>" +productName+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Product category: </td>");
				productType = r.getproductType();
				pw.print("<td>" +productType+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Price: </td>");
				price = r.getPrice();
				pw.print("<td>" +price+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<tr>");
				pw.print("<td> Retailer: </td>");
				retailer = r.getdoctorname();
				pw.print("<td>" +retailer+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<tr>");
				pw.print("<td> Retailer Zip: </td>");
				retailerpin = r.getzipcode();
				pw.print("<td>" +retailerpin+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Retailer City: </td>");
				city = r.getCity();
				pw.print("<td>" +city+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Retailer State: </td>");
				state = r.getclinicstate();
				pw.print("<td>" +state+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Product on sale and Discounts: </td>");
				discount = r.getDiscount();
				pw.print("<td>" +discount+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User: </td>");
				userName = r.getUserName();
				pw.print("<td>" +userName+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User age: </td>");
				age = r.getUserage();
				pw.print("<td>" +age+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User gender: </td>");
				gender = r.getUsergender();
				pw.print("<td>" +gender+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> User occupation: </td>");
				occupation = r.getUseroccupation();
				pw.print("<td>" +occupation+ "</td>");
				pw.print("</tr>");
				pw.println("<tr>");
				pw.println("<td> Review Rating: </td>");
				reviewRating = r.getReviewRating().toString();
				pw.print("<td>" +reviewRating+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td> Review Date: </td>");
				reviewDate = r.getReviewDate().toString();
				pw.print("<td>" +reviewDate+ "</td>");
				pw.print("</tr>");			
				pw.print("<tr>");
				pw.print("<td> Review Text: </td>");
				reviewText = r.getReviewText();
				pw.print("<td>" +reviewText+ "</td>");
				pw.print("</tr>");
				pw.print("<br><br>");
				pw.println("</table>");
				}					
        
		}
		else{
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Show services with price above 200 and rating 5</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		Iterator itr2 = review1000.iterator();
        while(itr2.hasNext()) {
			Review r = (Review)itr2.next();
		pw.print("<tr>");
		pw.print("<td style='font-size: 20px;'>");
		pw.print(r.getserviceName());
		pw.print("</td>");
		pw.print("<td style='font-size: 20px;'>");
		pw.print(r.getReviewRating());
		pw.print("</td>");
		pw.print("<td style='font-size: 20px;'>");
		pw.print("$"+r.getPrice());
		pw.print("</td>");
		pw.print("</tr>");
		}
		}
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	
	
	
			
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
