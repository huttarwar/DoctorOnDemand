
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
	Physician physician;
	Dental dental;
	Eyecare eyecare;
   // WearableTechnology wearableTechnology;
   // Laptop laptop;
   // Phone phone;
	
    static HashMap<String,Physician> physicianservices;
    static HashMap<String,Dental> dentals;
    static HashMap<String,Eyecare> eyecares;
    String XmlFileName;
	//HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String XmlFileName) {
    this.XmlFileName = XmlFileName;
    physicianservices = new HashMap<String, Physician>();
	dentals=new  HashMap<String, Dental>();
	eyecares=new HashMap<String, Eyecare>();
	/*smartSpeakerss = new HashMap<String, SmartSpeakers>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();*/
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(XmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("This: +SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for wearableTechnology,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
			
        if (elementName.equalsIgnoreCase("physician")) 
		{
			currentElement="physician";
			physician = new Physician();
            physician.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("eyecare"))
		{
			currentElement="eyecare";
			eyecare = new Eyecare();
            eyecare.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("dental"))
		{
			currentElement="dental";
			dental= new Dental();
            dental.setId(attributes.getValue("id"));
        }
    }
	// when xml end element is parsed store the data into respective hashmap for wearableTechnology,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        if (element.equals("physician")) {
			System.out.println("\nInside the Parser " + "\n" + physician.getId());
			physicianservices.put(physician.getId(),physician);
			return;
        }
        if (element.equals("eyecare")) {
			eyecares.put(eyecare.getId(),eyecare);
			return;
        }
        if (element.equals("dental")) {	 
			
			dentals.put(dental.getId(),dental);
			return;
        }
		
		
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("physician"))
				physician.setImage(elementValueRead);
        	if(currentElement.equals("dental"))
				dental.setImage(elementValueRead);
            if(currentElement.equals("eyecare"))
				eyecare.setImage(elementValueRead);
        
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("physician"))
				physician.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("dental"))
				dental.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("eyecare"))
				eyecare.setDiscount(Double.parseDouble(elementValueRead));
			         
			return;
	    }


		if (element.equalsIgnoreCase("location")) {
            if(currentElement.equals("physician"))
				physician.setLocation(elementValueRead);
        	if(currentElement.equals("dental"))
				dental.setLocation(elementValueRead);
            if(currentElement.equals("eyecare"))
				eyecare.setLocation(elementValueRead);
			        
			return;  
		}

		if (element.equalsIgnoreCase("type")) {
            if(currentElement.equals("physician"))
				physician.setType(elementValueRead);
        	if(currentElement.equals("dental"))
				dental.setType(elementValueRead);
            if(currentElement.equals("eyecare"))
				eyecare.setType(elementValueRead);
			        
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("physician"))
				physician.setName(elementValueRead);
        	if(currentElement.equals("dental"))
				dental.setName(elementValueRead);
            if(currentElement.equals("eyecare"))
				eyecare.setName(elementValueRead);
			          
			return;
	    }
	     if (element.equalsIgnoreCase("clinicname")) {
            if(currentElement.equals("physician"))
				physician.setclinicname(elementValueRead);
        	if(currentElement.equals("dental"))
				dental.setclinicname(elementValueRead);
            if(currentElement.equals("eyecare"))
				eyecare.setclinicname(elementValueRead);
			return;
	    }
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("physician"))
				physician.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("dental"))
				dental.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("eyecare"))
				eyecare.setPrice(Double.parseDouble(elementValueRead));
			      
			return;
        }
		if (element.equalsIgnoreCase("Doctorname")) {
            if(currentElement.equals("physician"))
				physician.setDoctorname(elementValueRead);
        	if(currentElement.equals("dental"))
				dental.setDoctorname(elementValueRead);
            if(currentElement.equals("eyecare"))
				eyecare.setDoctorname(elementValueRead);
			          
			return;
	    }
			if (element.equalsIgnoreCase("Latitude")) {
            if(currentElement.equals("physician"))
				physician.setLat(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("dental"))
				dental.setLat(Double.parseDouble(elementValueRead));
            if(currentElement.equals("eyecare"))
				eyecare.setLat(Double.parseDouble(elementValueRead));
			          
			return;
	    }
		if (element.equalsIgnoreCase("Longitude")) {
            if(currentElement.equals("physician"))
				physician.setLong(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("dental"))
				dental.setLong(Double.parseDouble(elementValueRead));
            if(currentElement.equals("eyecare"))
				eyecare.setLong(Double.parseDouble(elementValueRead));
			          
			return;
	    }
		

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\DoctorOnDemand\\ProductCatalog.xml");
    } 
}
