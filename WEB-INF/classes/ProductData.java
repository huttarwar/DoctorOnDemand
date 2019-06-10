	
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;


@WebServlet("/ProductData")
public class ProductData extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session=request.getSession(true); 
			PrintWriter pw= response.getWriter();
			response.setContentType("text/html");			
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		if(session.getAttribute("usertype").equals("patient") || session.getAttribute("usertype").equals(null)){
			utility.printHtml("LeftNavigationBar.html");
			}
			else if(session.getAttribute("usertype").equals("doctor")){
			utility.printHtml("LeftNavigationBarStore.html");
			}
			else{
			utility.printHtml("LeftNavigationBar.html");
			}
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		pw.print("<tr>");
		pw.print("<td><div id='shop_item'>");
			Product data= (Product)request.getAttribute("data");
			pw.print("<h3>"+data.getName()+"</h3>");
			pw.print("<h3>"+data.getclinicname()+"</h3>");
			pw.print("<strong>"+"$"+data.getPrice()+"</strong><ul>");
			pw.print("<strong>"+"Discount applied : " + data.getDiscount()+"%"+"</strong><ul>");
			pw.print("<strong><span class='glyphicon glyphicon-map-marker'></span>"+" "+ data.getLocation().toUpperCase()+"</strong><ul>");
			System.out.println(" "+data.getservicetype());
			pw.print("<li id='item'><img src='images/"+data.getservicetype()+"/"+data.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+data.getName()+"'>"+
					"<input type='hidden' name='type' value='"+data.getservicetype()+"'>"+
					"<input type='hidden' name='maker' value='"+data.getType()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Appointment'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+
			"<input type='hidden' name='name' value='"+data.getId()+"'>"+
					"<input type='hidden' name='type' value='"+data.getservicetype()+"'>"+
					"<input type='hidden' name='doctorname' value='"+data.getDoctorname()+"'>"+
					"<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
					"<input type='hidden' name='discount' value='"+data.getDiscount()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+
					"<input type='hidden' name='type' value='"+data.getservicetype()+"'>"+
					"<input type='hidden' name='maker' value='"+data.getType()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
		
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	

}