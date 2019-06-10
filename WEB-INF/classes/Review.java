import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String serviceName;
	private String userName;
	private String producttype;
	private String doctorname;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String zipcode;
	private String price;
	private String city;
	private String discount;
	private String userage;
	private String usergender;
	private String useroccupation;
	private String clinicstate;
	
	public Review (String serviceName,String userName,String producttype,String doctorname,String reviewRating,String reviewDate,String reviewText,String zipcode,String price,String city,String discount,String userage,String usergender,String useroccupation,String clinicstate){
		this.serviceName=serviceName;
		this.userName=userName;
		this.producttype=producttype;
		this.doctorname=doctorname;
	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;
		this.zipcode=zipcode;
		this.price= price;
		this.city= city;
		this.discount = discount;
		this.userage = userage;
		this.useroccupation = useroccupation;
		this.usergender = usergender;
		this.clinicstate = clinicstate;
	}

	public Review(String serviceName, String zipcode, String reviewRating, String reviewText) {
       this.serviceName = serviceName;
       this.zipcode = zipcode;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }

	public String getdoctorname(){
		return doctorname;
	}
	public void setdoctorname(String doctorname){
		this.doctorname = doctorname;
	}
	
	public String getDiscount(){
		return discount;
	}
	public void setDiscount(String discount){
		this.discount = discount;
	}
	
	public String getUserage(){
		return userage;
	}
	public void setUserage(String userage){
		this.userage = userage;
	}

	public String getUsergender(){
		return usergender;
	}
	public void setUsergender(String usergender){
		this.usergender = usergender;
	}
	
	public String getUseroccupation(){
		return useroccupation;
	}
	public void setUseroccupation(String useroccupation){
		this.useroccupation = useroccupation;
	}
	
	public String getclinicstate(){
		return clinicstate;
	}
	public void setclinicstate(String clinicstate){
		this.clinicstate = clinicstate;
	}
	
	public String getserviceName() {
		return serviceName;
	}
	public String getUserName() {
		return userName;
	}

	public void setserviceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getproductType() {
		return producttype;
	}

	public void setproductType(String producttype) {
		this.producttype = producttype;
	}


	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
    
		public String getzipcode() {
		return zipcode;
	}

	public void setzipcode(String zipcode) {
		this.zipcode = zipcode;
	}
			public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
