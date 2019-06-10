import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dentallist")

public class dentallist extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session=request.getSession(true); 

		/* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Dental> hm = new HashMap<String, Dental>();
		
		if(CategoryName==null)
		{
			hm = MySqlDataStoreUtilities.getDentals();
			name = "";
		}
		else
		{
		  if(CategoryName.equals("generaldentist"))
		  {
			for(Map.Entry<String,Dental> entry : MySqlDataStoreUtilities.getDentals().entrySet())
				{
				if(entry.getValue().getType().equals("General Dentist"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Generaldentist";
		  }
		  else if(CategoryName.equals("endodontist"))
		  {
			for(Map.Entry<String,Dental> entry : MySqlDataStoreUtilities.getDentals().entrySet())
				{
				if(entry.getValue().getType().equals("Endodontist"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "Endodontist";
		  }
		  else if(CategoryName.equals("orthodontist"))
		  {
			for(Map.Entry<String,Dental> entry : MySqlDataStoreUtilities.getDentals().entrySet())
				{
				if(entry.getValue().getType().equals("Orthodontist"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Orthodontist";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Games and Games information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		if(session.getAttribute("usertype") == null){
			utility.printHtml("LeftNavigationBar.html");
			}
			else if(session.getAttribute("usertype").equals("doctor")){
			utility.printHtml("LeftNavigationBarStore.html");
			}
			else if(session.getAttribute("usertype").equals("patient") || session.getAttribute("usertype").equals("customerservice")){
			
			utility.printHtml("LeftNavigationBar.html");
			}
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Dental</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Dental> entry : hm.entrySet()){
			Dental dental = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+dental.getName()+"</h3>");
			pw.print("<h3>"+"Clinic:- "+dental.getclinicname()+"</h3>");
			pw.print("<strong>"+"$"+dental.getPrice()+"</strong><ul>");
			pw.print("<strong>"+"Discount applied : " +dental.getDiscount()+"%"+"</strong><ul>");
			//<strong><span class='glyphicon glyphicon-map-marker'></span>
			pw.print("<a href='Locate?latt="+dental.getLat()+"&longi="+dental.getLong()+"' style='font-size:12px;'>"+"<strong><span class='glyphicon glyphicon-map-marker'></span>"+dental.getLocation().toUpperCase()+"</strong></a><ul>");			
			pw.print("<li id='item'><img src='images/dentals/"+dental.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='dentals'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Appointment'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='dentals'>"+
					"<input type='hidden' name='doctorname' value='"+dental.getDoctorname()+"'>"+
					"<input type='hidden' name='price' value='"+dental.getPrice()+"'>"+
					"<input type='hidden' name='discount' value='"+dental.getDiscount()+"'>"+					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='dentals'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}

}
