import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBarStore.html");
		if(action.equals("Addproduct"))
		{
			
			
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Product</a></h2>"
					+ "<div class='entry'>");
				
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Service Type</h3></td><td><select name='producttype' class='input'><option value='physicians' selected>Physicians</option><option value='eyecares'>Eyecares</option><option value='dentals'>Dentals</option></select>"
					+ "</td></tr><tr><td>"
					//+"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					//+ "</td></tr><tr><td>"
					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Clinic Name</h3></td><td><input type='text' name='clinicname' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Doctor Name</h3></td><td><input type='text' name='DoctorName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Image</h3></td><td><input type='text' name='TypeImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Type</h3></td><td><input type='text' name='Type' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Location</h3></td><td><input type='text' name='Location' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Latitude</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='latt' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Longitude</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='longi' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
			
		
		
		}else if (action.equals("Updateproduct"))
		{
		     pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Product</a></h2>"
					+ "<div class='entry'>");
					
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Service Type</h3></td><td><select name='producttype' class='input'><option value='physicians' selected>Physicians</option><option value='eyecares'>Eyecares</option><option value='dentals'>Dentals</option></select>"
					+ "</td></tr><tr><td>"
					//+"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					//+ "</td></tr><tr><td>"
					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Clinic Name</h3></td><td><input type='text' name='clinicname' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Doctor Name</h3></td><td><input type='text' name='DoctorName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Image</h3></td><td><input type='text' name='TypeImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Type</h3></td><td><input type='text' name='Type' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Location</h3></td><td><input type='text' name='Location' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Latitude</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='latt' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Longitude</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='longi' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"			
					+ "<input type='submit' class='btnbuy' name='button' value='Update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");	
		}else
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Delete Product</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}