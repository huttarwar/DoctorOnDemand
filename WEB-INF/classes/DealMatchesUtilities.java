import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DealMatchesUtilities")

public class DealMatchesUtilities extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
			String line=null;
			HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		try
			{
				
		pw.print("<div id='content' style='padding-left: 320px;'>");
		pw.print("<div class='post'>");
		pw.print("<h2 class='title'>");
		pw.print("<a href='#'>Shop the Best Deals </a></h2>");
		
		pw.print("<div class='entry'>");
		pw.print("<br> <br>");
		pw.print("<h1>We beat our competitors with our New best Price-Match Guaranteed</h2>");
		
			
			String TOMCAT_HOME = System.getProperty("catalina.home");

			HashMap<String,Product> productmap=MySqlDataStoreUtilities.getData();
			System.out.println("Before hashmap");
			for(Map.Entry<String, Product> entry : productmap.entrySet())
			{
				System.out.println(""+entry.getKey());
				
			if(selectedproducts.size()<3 && !selectedproducts.containsKey(entry.getKey()))
			{		
			BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\DoctorOnDemand\\DealMatches.txt")));
			line=reader.readLine().toLowerCase();
			System.out.println(line);
//		

			if(line==null)
			{
				System.out.println("zin line");
				pw.print("<h2 align='center'>No Offers Found</h2>");
				break;
			}	
			else
			{	
			do {	
				  if(line.contains(entry.getKey()))
				  {
				 System.out.println("Inside lineeeeee");
					pw.print("<h2>"+line+"</h2>");
					pw.print("<br>");
					selectedproducts.put(entry.getKey(),entry.getValue());
					break;
				  }
				  
			    }while((line = reader.readLine()) != null);
			
			 }
			 }
			}
			}
			catch(Exception e)
			{
			System.out.println(""+line+e);
			pw.print("<h2 align='center'>No Offers Found</h2>");
			}
		pw.print("</div>");
		pw.print("</div>");
		pw.print("<div class='post'>");
		pw.print("<h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Deal Matches</a>");
		pw.print("</h2>");
		pw.print("<div class='entry'>");
		System.out.println(""+selectedproducts.size());
		if(selectedproducts.size()==0)
		{
		pw.print("<h2 align='center'>No Deals Found</h2>");	
		}
		else
		{
		pw.print("<table id='bestseller'>");
		pw.print("<tr>");
		for(Map.Entry<String, Product> entry : selectedproducts.entrySet()){
		pw.print("<td><div id='shop_item'><h3>"+entry.getValue().getName()+"</h3>");
		pw.print("<h3>"+entry.getValue().getclinicname()+"</h3>");
		pw.print("<strong>$"+entry.getValue().getPrice()+"</strong>");
		pw.print("<h3>"+entry.getValue().getLocation()+"</h3>");
		pw.print("<ul>");
		pw.print("<li id='item'><img src='images/"+entry.getValue().getType()+"/"+entry.getValue().getImage()+"' alt='' />");
		pw.print("</li><li>");
		pw.print("<form action='Cart' method='post'><input type='submit' class='btnbuy' value='Book Appointment'>");
		pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
		pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getclinicname()+"'>");
		pw.print("<input type='hidden' name='access' value=''>");
		pw.print("</form></li><li>");
		pw.print("<form action='WriteReview' method='post'><input type='submit' class='btnreview' value='WriteReview'>");
		pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
		pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getclinicname()+"'>");
		pw.print("<input type='hidden' name='price' value='"+entry.getValue().getPrice()+"'>");
		pw.print("</form></li>");
		pw.print("<li>");
		pw.print("<form action='ViewReview' method='post'><input type='submit' class='btnreview' value='ViewReview'>");
		pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
		pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		pw.print("</form></li></ul></div></td>");
		}
		pw.print("</tr></table>");
		}
		pw.print("</div></div></div>");
		
	}
}
