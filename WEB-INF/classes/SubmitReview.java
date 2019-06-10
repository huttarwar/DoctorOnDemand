import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		storeReview(request, response);
	}
	protected void storeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
				HttpSession session = request.getSession(true);	
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
							
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
                String servicename=request.getParameter("servicename");		
                String producttype=request.getParameter("producttype");
				String productprice=request.getParameter("productprice");
                String doctorname=request.getParameter("doctorname");
                String reviewrating=request.getParameter("reviewrating");
                String reviewdate=request.getParameter("reviewdate");
                String reviewtext=request.getParameter("reviewtext");
				String zipcode=request.getParameter("zipcode");
				String cliniccity = request.getParameter("cliniccity");
				String discount = request.getParameter("discount");
				String userage = request.getParameter("userage");
				String usergender = request.getParameter("usergender");
				String useroccupation = request.getParameter("useroccupation");
				String clinicstate = request.getParameter("clinicstate");
		String message=utility.storeReview(servicename,producttype,doctorname,reviewrating,reviewdate,reviewtext,zipcode,productprice,cliniccity,discount,userage,usergender,useroccupation,clinicstate);				     
       		
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
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
      		if(message.equals("Successfull")){
      		pw.print("<h2>Review for &nbsp"+servicename+" Stored </h2>");}
                else{
				pw.print("<h2>Mongo Db is not up and running </h2>");}
                
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	                     	
                    }
              	catch(Exception e)
		{
                 System.out.println(e.getMessage());
		}  			
       
	 	
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
            }
}
