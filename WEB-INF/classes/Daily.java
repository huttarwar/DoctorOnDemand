import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Daily")

/* 
	Game class contains class variables name,price,image,retailer,condition,discount.

	Game class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Game class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class Daily extends HttpServlet{
	
	private String date;
	private double dailySum;
	
	
	public Daily(String date,double dailySum){
		this.date=date;
		this.dailySum=dailySum;
		
		
	}
	
	public Daily(){
		
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getDailySum() {
		return dailySum;
	}
	public void setDailySum(double dailySum) {
		this.dailySum = dailySum;
	}
}