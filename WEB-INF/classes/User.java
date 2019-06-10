import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private int id;
	private String name;
	private String firstname;
	private String lastname;
	private String password;
	private String usertype;
	private String repassword;
	
	public User(String firstname,String lastname,String name, String password,String repassword, String usertype) {
		this.firstname=firstname;
		this.lastname = lastname;
		this.name=name;
		this.password=password;
		this.repassword=repassword;
		this.usertype=usertype;
	}

	
	public String getRePassword(){
		return repassword;
	}
	
	public void setRePassword(String repassword){
		this.repassword = repassword;
	}
	
	public String getfirstName() {
		return firstname;
	}

	public void setfirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public String getlastName() {
		return lastname;
	}

	public void setlastName(String lastname) {
		this.lastname = lastname;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
