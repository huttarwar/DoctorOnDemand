	

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
	//once the user clicks writereview button from products page he will be directed
 	//to write review page where he can provide reqview for item rating reviewtext	
	
public class WriteReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
			HttpSession session = request.getSession(true);	
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
						
			session.setAttribute("login_msg", "Please Login to Write a Review");
			response.sendRedirect("Login");
			return;
		}
                String servicename=request.getParameter("name");		
                String producttype=request.getParameter("type");
                String doctorname=request.getParameter("doctorname");
				String productprice=request.getParameter("price");
				String discount=request.getParameter("discount");
		
		
      // on filling the form and clicking submit button user will be directed to submit review page
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
		pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
                pw.print("<table class='gridtable'>");
		pw.print("<tr><td> Medical Service: </td><td>");
		pw.print(servicename);
		pw.print("<input type='hidden' name='servicename' value='"+servicename+"'>");
		pw.print("</td></tr>");
	        pw.print("<tr><td> Service Type:</td><td>");
                pw.print(producttype);
	        pw.print("<input type='hidden' name='producttype' value='"+producttype+"'>");
		pw.print("</td></tr>");
		pw.print("<tr><td> Product Price:</td><td>");
                pw.print(productprice);
	        pw.print("<input type='hidden' name='productprice' value='"+productprice+"'>");
		pw.print("</td></tr>");		
		 pw.print("<tr><td>Discounts:</td><td>");
                pw.print(discount);
	        pw.print("<input type='hidden' name='discount' value='"+discount+"'>");
		pw.print("</td></tr>");
                pw.print("<tr><td> Doctor name: </td><td>");
                pw.print(doctorname);
		pw.print("<input type='hidden' name='doctorname' value='"+doctorname+"'>");
                pw.print("</td></tr><table>");
		
  				pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
					pw.print("<td>");
						pw.print("<select name='reviewrating'>");
						pw.print("<option value='1' selected>1</option>");
						pw.print("<option value='2'>2</option>");
						pw.print("<option value='3'>3</option>");
						pw.print("<option value='4'>4</option>");
						pw.print("<option value='5'>5</option>");  
					pw.print("</td></tr>");
				
					pw.print("<tr>");
					pw.print("<td> Clinic Zip Code: </td>");
					pw.print("<td> <input type='text' name='zipcode'> </td>");
			        pw.print("</tr>");		


					pw.print("<tr>");
					pw.print("<td> Clinic City: </td>");
					pw.print("<td> <input type='text' name='cliniccity'> </td>");
			        pw.print("</tr>");

					pw.print("<tr>");
					pw.print("<td> Clinic State: </td>");
					pw.print("<td> <input type='text' name='clinicstate'> </td>");
			        pw.print("</tr>");						
		
				pw.print("<tr>");
					pw.print("<td> Review Date: </td>");
					pw.print("<td> <input type='date' name='reviewdate'> </td>");
			       pw.print("</tr>");	
					
					pw.print("<tr>");
					pw.print("<td> User age: </td>");
					pw.print("<td> <input type='text' name='userage'> </td>");
			        pw.print("</tr>");	

					pw.print("<table><tr></tr><tr></tr><tr><td> User Gender: </td>");
					pw.print("<td>");
						pw.print("<select name='usergender'>");
						pw.print("<option value='1' selected>Male</option>");
						pw.print("<option value='2'>Female</option>");
						pw.print("<option value='3'>Don't want to disclose</option>");  
					pw.print("</td></tr>");	

					pw.print("<tr>");
					pw.print("<td> User occupation: </td>");
					pw.print("<td> <input type='text' name='useroccupation'> </td>");
			        pw.print("</tr>");

				pw.print("<tr>");
					pw.print("<td> Review Text: </td>");
					pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
				pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");

                pw.print("</h2></div></div></div>");		
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
