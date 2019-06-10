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

@WebServlet("/physicianlist")

public class  physicianlist extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		HttpSession session=request.getSession(true); 
		HashMap<String, Physician> hm = new HashMap<String, Physician>();
		if(CategoryName==null){
			hm = MySqlDataStoreUtilities.getphysicians();
			name = "";
		}
		else
		{
		   if(CategoryName.equals("pediatricians"))
		   {
			 for(Map.Entry<String,Physician> entry :MySqlDataStoreUtilities.getphysicians().entrySet())
			 {
				if(entry.getValue().getType().equals("Pediatrician"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Pediatricians";
		   }
		   else if(CategoryName.equals("dermatologist"))
		    {
			for(Map.Entry<String,Physician> entry :MySqlDataStoreUtilities.getphysicians().entrySet())
				{
				 if(entry.getValue().getType().equals("Dermatologist"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Dermatologist";
			}
			else if(CategoryName.equals("psychiatrists"))
			{
				for(Map.Entry<String,Physician> entry :MySqlDataStoreUtilities.getphysicians().entrySet())
				{
				 if(entry.getValue().getType().equals("Psychiatrist"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Psychiatrists";
			}
			
			
		}


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
		pw.print("<a style='font-size: 24px;'>"+name+" Physician</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Physician> entry : hm.entrySet())
		{
			Physician physician = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+physician.getName()+"</h3>");
			pw.print("<h3>"+"Clinic:- "+physician.getclinicname()+"</h3>");
			pw.print("<strong>"+"$"+physician.getPrice()+"</strong><ul>");
			pw.print("<strong>"+"Discount applied : " +physician.getDiscount()+"%"+"</strong><ul>");
			pw.print("<a href='Locate?latt="+physician.getLat()+"&longi="+physician.getLong()+"' style='font-size:12px;'>"+"<strong><span class='glyphicon glyphicon-map-marker'></span>"+physician.getLocation().toUpperCase()+"</strong></a><ul>");			
			pw.print("<li id='item'><img src='images/physicians/"+physician.getImage()+"' alt='' /></li>");
			//style='font-size: 10px; padding:0px 0px 0px 0px; margin-top:0px;line-height: normal;'
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='physicians'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Appointment'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='physicians'>"+
					"<input type='hidden' name='doctorname' value='"+physician.getDoctorname()+"'>"+
					"<input type='hidden' name='price' value='"+physician.getPrice()+"'>"+
					"<input type='hidden' name='discount' value='"+physician.getDiscount()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='physicians'>"+
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
