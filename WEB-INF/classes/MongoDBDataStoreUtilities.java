import com.mongodb.Mongo;	
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
static DBCollection myReviews;
public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
 myReviews= db.getCollection("myReviewsDoctor");	
return myReviews; 
}


public static String insertReview(String servicename,String username,String producttype,String doctorname,String reviewrating,String reviewdate,String reviewtext,String zipcode,String price,String cliniccity,String discount,String userage,String usergender,String useroccupation,String clinicstate)
{
	try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myReviews").
				append("userName", username).
				append("servicename", servicename).
				append("productType", producttype).
				append("doctorname", doctorname).
				append("reviewRating",Integer.parseInt(reviewrating)).
				append("reviewDate", reviewdate).
				append("reviewText", reviewtext).
				append("zipcode", zipcode).
				append("cliniccity", cliniccity).
				append("price",(int) Double.parseDouble(price)).
				append("discount", discount).
				append("userage", userage).
				append("usergender", usergender).
				append("useroccupation", useroccupation).
				append("clinicstate", clinicstate);
			myReviews.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
		return "UnSuccessfull";
		}	
		
}

public static HashMap<String, ArrayList<Review>> selectReview()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("servicename")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("servicename"), arr);
			}
			ArrayList<Review> listReview = reviews.get(obj.getString("servicename"));		
			Review review =new Review(obj.getString("servicename"),obj.getString("userName"),obj.getString("productType"),obj.getString("doctorname"),obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("zipcode"),obj.getString("price"),obj.getString("cliniccity")
				,obj.getString("discount"),obj.getString("userage"),obj.getString("usergender"),obj.getString("useroccupation"),obj.getString("clinicstate"));
			//add to review hashmap
			listReview.add(review);
		
			}
 		return reviews;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return reviews;	
		}	

     
	}
	
	public static ArrayList<Review> getReview3()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	ArrayList<Review> listReview = new ArrayList<>();
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	
	
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("servicename")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("servicename"), arr);
			}
					
			Review review =new Review(obj.getString("servicename"),obj.getString("userName"),obj.getString("productType"),obj.getString("doctorname"),obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("zipcode"),obj.getString("price"),obj.getString("cliniccity")
				,obj.getString("discount"),obj.getString("userage"),obj.getString("usergender"),obj.getString("useroccupation"),obj.getString("clinicstate"));
			//add to review hashmap
			if(Integer.parseInt(obj.getString("reviewRating")) > 3){
				listReview.add(review);
			}
			
		
			}
 		return listReview;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return listReview;	
		}	

     
	}
	
	public static ArrayList<Review> getProduct1000()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	ArrayList<Review> listReview = new ArrayList<>();
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	
	
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("productName")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("productName"), arr);
			}
					
			Review review =new Review(obj.getString("servicename"),obj.getString("userName"),obj.getString("productType"),obj.getString("doctorname"),obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("zipcode"),obj.getString("price"),obj.getString("cliniccity")
				,obj.getString("discount"),obj.getString("userage"),obj.getString("usergender"),obj.getString("useroccupation"),obj.getString("clinicstate"));
			//add to review hashmap
			if(Integer.parseInt(obj.getString("reviewRating")) > 3 && Double.parseDouble(obj.getString("price")) > 200){
				listReview.add(review);
			}
			
		
			}
 		return listReview;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return listReview;	
		}	

     
	}
	
	

  public static  ArrayList <Bestrating> topProducts(){
	  ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
	  try{
		  System.out.println("top5");
	  getConnection();
	  int retlimit =5;
	  DBObject sort = new BasicDBObject();
	  sort.put("reviewRating",-1);
	  DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
	  while(cursor.hasNext()) {
		  BasicDBObject obj = (BasicDBObject) cursor.next();
		  		  		   
		  String prodcutnm = obj.get("servicename").toString();
		  String rating = obj.get("reviewRating").toString();
	      Bestrating best = new Bestrating(prodcutnm,rating);
		  Bestrate.add(best);
	  }
	
	}catch (Exception e){ System.out.println(e.getMessage());}
   return Bestrate;
  }
  
  
  public static  ArrayList <Productratings> productsratings(){
	  ArrayList <Productratings> ProductRatings = new ArrayList <Productratings> ();
	  try{
		  System.out.println("top5");
	  getConnection();
	  int retlimit =100;
	  DBObject sort = new BasicDBObject();
	  sort.put("reviewRating",-1);
	  DBCursor cursor = myReviews.find();
	  while(cursor.hasNext()) {
		  BasicDBObject obj = (BasicDBObject) cursor.next();
		  String prodcutnm = obj.get("servicename").toString();
		  String rating = obj.get("reviewRating").toString();
	      Productratings best = new Productratings(prodcutnm,rating);
		  ProductRatings.add(best);
	  }
	
	}catch (Exception e){ System.out.println(e.getMessage());}
   return ProductRatings;
  }
  
  	  public static ArrayList <Mostsoldzip> mostsoldZip(){
	  ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
	  try{
		  System.out.println("top5");
	  getConnection();
      DBObject groupProducts = new BasicDBObject("_id","$zipcode"); 
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  
	  DBObject sortFields = new BasicDBObject("count",-1);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
      for (DBObject res : output.results()) {
        System.out.println(res);
		String zipcode =(res.get("_id")).toString();
        String count = (res.get("count")).toString();	
        Mostsoldzip mostsldzip = new Mostsoldzip(zipcode,count);
		mostsoldzip.add(mostsldzip);
	
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return mostsoldzip;
  }
  
   public static ArrayList <Mostsold> mostsoldProducts(){
	  ArrayList<Mostsold> mostsold = new ArrayList<Mostsold>();
	  try{
		  System.out.println("top5product");
	  
      getConnection();
      DBObject groupProducts = new BasicDBObject("_id","$servicename"); 
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  
	  DBObject sortFields = new BasicDBObject("count",-1);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
	  System.out.println("oun"+output);
      for (DBObject res : output.results()) {
	  
      
        System.out.println("res"+res);
		String prodcutname =(res.get("_id")).toString();
        String count = (res.get("count")).toString();	
        Mostsold mostsld = new Mostsold(prodcutname,count);
		mostsold.add(mostsld);
	
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return mostsold;
  }	

  //Get all the reviews grouped by product and zip code;
public static ArrayList<Review> selectReviewForChart() {

		
        ArrayList<Review> reviewList = new ArrayList<Review>();
        try {

            getConnection();
            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
            dbObjIdMap.put("retailerpin", "$retailerpin");
            dbObjIdMap.put("productName", "$productName");
            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
            groupFields.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields);

            DBObject projectFields = new BasicDBObject("_id", 0);
            projectFields.put("retailerpin", "$_id");
            projectFields.put("productName", "$productName");
            projectFields.put("reviewCount", "$count");
            DBObject project = new BasicDBObject("$project", projectFields);

            DBObject sort = new BasicDBObject();
            sort.put("reviewCount", -1);

            DBObject orderby = new BasicDBObject();            
            orderby = new BasicDBObject("$sort",sort);
            

            AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

            for (DBObject result : aggregate.results()) {

                BasicDBObject obj = (BasicDBObject) result;
                Object o = com.mongodb.util.JSON.parse(obj.getString("retailerpin"));
                BasicDBObject dbObj = (BasicDBObject) o;
                Review review = new Review(dbObj.getString("productName"), dbObj.getString("retailerpin"),
                        obj.getString("reviewCount"), null);
                reviewList.add(review);
                
            }
            return reviewList;

        }

        catch (

        Exception e) {
            reviewList = null;
            
            return reviewList;
        }

    }
  

	
}	