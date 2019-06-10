import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

@WebServlet("/Home")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Smart Portable Application.

*/

public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session=request.getSession(true); 
		String usertype = (String) session.getAttribute("usertype");
		//User user = new User();
		//String usertype = user.getUsertype(); 
		Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Content.html");
   		
		}
		else if(usertype.equals("doctor")){
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBarStore.html");
		utility.printHtml("Content.html");
		
		}
		else if(usertype.equals("patient") || usertype.equals("customerservice") || usertype.equals(null)){
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Content.html");
		
		}
		RequestDispatcher rd=request.getRequestDispatcher("DealMatchesUtilities");
		rd.include(request,response);
		utility.printHtml("Footer.html");
		}
		
	}


