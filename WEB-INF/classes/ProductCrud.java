import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String producttype= "",productId="",productName="",clinicname="",DoctorName="",TypeImage="",Type="",Location="",prod = "";
			double productPrice=0.0,productDiscount = 0.0,latt= 0.0, longi = 0.0;
			HashMap<String,Physician> phy = new HashMap<String,Physician> ();
			HashMap<String,Eyecare> eye = new HashMap<String,Eyecare> ();
			HashMap<String,Dental> den = new HashMap<String,Dental> ();
			//HashMap<String,SmartSpeakers> ss = new HashMap<String,SmartSpeakers> ();
			//HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
			if (action.equals("Add") || action.equals("Update"))
			{	
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 clinicname = request.getParameter("clinicname");
				 DoctorName = request.getParameter("DoctorName");
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 TypeImage = request.getParameter("TypeImage");
				 Type = request.getParameter("Type");
				 Location = request.getParameter("Location");
				 latt = Double.parseDouble(request.getParameter("latt"));
				 longi = Double.parseDouble(request.getParameter("longi"));
				 productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
				 System.out.println(" "+producttype+" "+productId+" "+productName+" "+clinicname+" "+DoctorName+" "+productPrice+" "+TypeImage+" "+Type+" "+Location+" "+productDiscount);
				 
			}
			else{
				productId   = request.getParameter("productId");
			}	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBarStore.html");

			if(action.equals("Add"))
			{
			  			if(producttype.equals("physicians")){
				  phy = MySqlDataStoreUtilities.getphysicians();
				  if(phy.containsKey(productId)){
					  msg = "Service already available";
					  
				  }
					  
			  }else if(producttype.equals("eyecares"))
			  {
				  eye = MySqlDataStoreUtilities.getEyecares();
				  if(eye.containsKey(productId)){
					  msg = "Service already available";
				  }
			  }else if (producttype.equals("dentals"))
			  {
				  den = MySqlDataStoreUtilities.getDentals();
				  if(den.containsKey(productId)){
					  msg = "Service already available";
				  }
			  }
							
							  
			  if (msg.equals("good"))
			  {  
				  try
				  {
					  msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,clinicname,DoctorName,productPrice,TypeImage,Type,Location,productDiscount,latt,longi);
				  }
				  catch(Exception e)
				  { 
					msg = "Service cannot be inserted";
				  }
				  msg = "Service has been successfully added";
			  }					
			}else if(action.equals("Update"))
			{
				
			  if(producttype.equals("physicians")){
				  phy = MySqlDataStoreUtilities.getphysicians();
				  if(!phy.containsKey(productId)){
					  msg = "Service not available";
				  }
					  
			  }else if(producttype.equals("eyecares"))
			  {
				  eye = MySqlDataStoreUtilities.getEyecares();
				  if(!eye.containsKey(productId)){
					  msg = "Service not available";
				  }
			  }else if (producttype.equals("dentals"))
			  {
				  den = MySqlDataStoreUtilities.getDentals();
				  if(!den.containsKey(productId)){
					  msg = "Service not available";
				  }
			  }
			  	
			  if(msg.equals("good"))
			  {		
				
				  try
				  {
					msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,clinicname,DoctorName,productPrice,TypeImage,Type,Location,productDiscount,latt,longi);
				  }
				  catch(Exception e)
				  { 
					msg = "Service cannot be updated";
				  }
				  msg = "Service has been successfully updated";
			  } 
			}else if(action.equals("Delete"))
			{
				  msg = "bad";
				  phy = MySqlDataStoreUtilities.getphysicians();
				  if(phy.containsKey(productId)){
					  msg = "good";
					  
				  }
					  
			  
				  eye = MySqlDataStoreUtilities.getEyecares();
				  if(eye.containsKey(productId)){
					  msg = "good";
				  }
			  
				  den = MySqlDataStoreUtilities.getDentals();
				  if(den.containsKey(productId)){
					  msg = "good";
				  }
		       		
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						
						 msg = MySqlDataStoreUtilities.deleteproducts(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Service cannot be deleted";
					  }
					   msg = "Service has been successfully deleted";
				  }else{
					  msg = "Service not available";
				  }
			}	
				
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>COnfirmation</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
			
	}
}