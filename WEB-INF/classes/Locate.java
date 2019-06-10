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
@WebServlet("/Locate")

public class Locate extends HttpServlet {
	

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/
	
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);				
						
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.println("<div id=\"googleMap\" style='width:850px;height:500px;'></div>");
		
		pw.println("<script>");
		pw.println("function myMap() {");
		pw.println("var myLatLng = {lat:"+request.getParameter("latt")+", lng:"+request.getParameter("longi")+"};");	
		pw.println("var mapProp= {");
		pw.println("center:new google.maps.LatLng("+request.getParameter("latt")+","+request.getParameter("longi")+"),");
		pw.println("zoom:18,");
		pw.println("};");
		pw.println("var map=new google.maps.Map(document.getElementById(\"googleMap\"),mapProp);");
		pw.println("var marker = new google.maps.Marker({position: myLatLng});");
		pw.println("marker.setMap(map);"); 
		pw.println("}");
		pw.println("</script>");

		pw.println("<script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyAF06ZJ_GBdssvk5ln0uFWQPEGojihE8_E&callback=myMap\"></script>");
			
		utility.printHtml("Footer.html");			
	
} 
		
	 
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}