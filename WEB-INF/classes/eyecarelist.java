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

@WebServlet("/eyecarelist")

public class eyecarelist extends HttpServlet {

	/* Trending Page Displays all the phones and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session=request.getSession(true); 


	/* Checks the phones type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Eyecare> hm = new HashMap<String, Eyecare>();

		if (CategoryName == null)	
		{
			hm =  MySqlDataStoreUtilities.getEyecares();
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("optometrist")) 
			{	
				for(Map.Entry<String,Eyecare> entry :  MySqlDataStoreUtilities.getEyecares().entrySet())
				{
				  if(entry.getValue().getType().equals("Optometrist"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Optometrist";
			} 
			else if (CategoryName.equals("optician"))
			{
				for(Map.Entry<String,Eyecare> entry : MySqlDataStoreUtilities.getEyecares().entrySet())
				{
				  if(entry.getValue().getType().equals("Optician"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Optician";
			} 
			else if (CategoryName.equals("orthoptist")) 
			{
				for(Map.Entry<String,Eyecare> entry : MySqlDataStoreUtilities.getEyecares().entrySet())
				{
				  if(entry.getValue().getType().equals("Orthoptist"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Orthoptist";
			}
	    }

		/* Header, Left Navigation Bar are Printed.

		All the phones and Phone information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request, pw);
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
		pw.print("<a style='font-size: 24px;'>" + name + " Eyecare</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Eyecare> entry : hm.entrySet()) {
			Eyecare Eyecare = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Eyecare.getName() + "</h3>");
			pw.print("<h3>" + "Clinic:- "+Eyecare.getclinicname() + "</h3>");
			pw.print("<strong>"+"$"+Eyecare.getPrice()+"</strong><ul>");
			pw.print("<strong>"+"Discount applied : " +Eyecare.getDiscount()+"%"+"</strong><ul>");
			pw.print("<a href='Locate?latt="+Eyecare.getLat()+"&longi="+Eyecare.getLong()+"' style='font-size:12px;'>"+"<strong><span class='glyphicon glyphicon-map-marker'></span>"+Eyecare.getLocation().toUpperCase()+"</strong></a><ul>");			
			pw.print("<li id='item'><img src='images/eyecares/"+Eyecare.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='eyecares'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Appointment'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='eyecares'>"+
					"<input type='hidden' name='doctorname' value='"+Eyecare.getDoctorname()+"'>"+
					"<input type='hidden' name='price' value='"+Eyecare.getPrice()+"'>"+
					"<input type='hidden' name='discount' value='"+Eyecare.getDiscount()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='eyecares'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
