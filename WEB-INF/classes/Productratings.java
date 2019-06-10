import java.io.*;
public class Productratings
{
String productname ;
String rating;


public  Productratings(String productname,String rating)
{
	
	this.productname = productname ;
    this.rating = rating;
}


public String getProductname(){
 return productname;
}

public String getRating () {
 return rating;
}
}