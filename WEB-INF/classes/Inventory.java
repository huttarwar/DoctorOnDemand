import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Inventory")


public class Inventory extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private int appointments;
	private double sales;
	private double discount;
	
	public Inventory(String id,String name, double price, int appointments,double sales,double discount){
		this.id=id;
		this.name=name;
		this.price=price;
		this.appointments=appointments;
		this.sales=sales;		
		this.discount = discount;
		
	}
	
	public Inventory(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getAppointments() {
		return appointments;
	}
	public void setAppointments(int appointments) {
		this.appointments = appointments;
	}

	public double getsales() {
		return sales;
	}
	public void setsales(double sales) {
		this.sales = sales;
	}


	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
