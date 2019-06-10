import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorondemand?autoReconnect=true&useSSL=false","root","root");
			System.out.println("Connection");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Product> data;
		data=getData();
		System.out.println("data:"+data);
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Product p=(Product)pi.getValue();                   
                if (p.getName().toLowerCase().startsWith(searchId))
                {
						System.out.println(""+p.getId()+""+ p.getName());
                        sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getName() + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	   
	   return sb;
	}
	
	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			
		    String selectproduct="select * from  servicedetails";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	Product p = new Product(rs.getString("ServiceType"),rs.getString("Id"),rs.getString("servicename"),rs.getString("clinicName"),rs.getString("DoctorName"),rs.getDouble("Price"),rs.getString("TypeImage"),rs.getString("Type"),rs.getString("Location"),rs.getDouble("Discount"),rs.getDouble("latt"),rs.getDouble("longi"));
				hm.put(rs.getString("Id"), p);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	} 

}
