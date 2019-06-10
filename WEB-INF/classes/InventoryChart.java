import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

@WebServlet("/InventoryChart")

public class InventoryChart extends HttpServlet {
		
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
       	
		
		displayPage(request, response, pw);			
		
		}
		
		protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {
		
		
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBarStore.html");

        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Inventory chart</a></h2>"
                + "<div class='entry'>");
        
			 try {
			ArrayList<Inventory> inventories = MySqlDataStoreUtilities.getInventorylist();        
            System.out.println("gson to json");
            String reviewJson = new Gson().toJson(inventories);
            System.out.println("gson to json done"+reviewJson);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);
			System.out.println(""+reviewJson);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
		
        pw.println("<div id='chart_div'></div>");
        pw.println("</div></div></div>");
		
        pw.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
        pw.println("<script type='text/javascript' src='visualization-chart-script.js'></script>");
        utility.printHtml("Footer.html");

    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       

    }
}