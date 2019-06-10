import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Product {
	private String id;
	private String name;
	private String clinicname;
	private String Doctorname;
	private double price;
	private String image;
	private String type;
	private String location;
	private double discount;
	private String servicetype;
	private double latt;
	private double longi;
	
	public Product(String servicetype, String id, String name,String clinicname, String Doctorname,double price, String image, String type,String location,double discount, double latt, double longi){
		this.name=name;
		this.clinicname=clinicname;
		this.Doctorname=Doctorname;
		this.price=price;
		this.image=image;
		this.type = type;
		this.location=location;
		this.discount = discount;
		this.id = id;
		this.servicetype = servicetype;
		this.latt = latt;
		this.longi = longi;
	}
	
    
	public Product(){
		
	}
	public String getservicetype(){
		return servicetype;
	}
	public void setservicetype(String servicetype) {
		this.servicetype = servicetype;
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
	public String getclinicname() {
		return clinicname;
	}
	public void setclinicname(String clinicname) {
		this.clinicname = clinicname;
	}
	public String getDoctorname() {
		return Doctorname;
	}
	public void setDoctorname(String Doctorname) {
		this.Doctorname = Doctorname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getLat() {
		return latt;
	}

	public void setLat(double latt) {
		this.latt = latt;
	}
	public double getLong() {
		return longi;
	}

	public void setLong(double longi) {
		this.longi = longi;
	}
	
}
