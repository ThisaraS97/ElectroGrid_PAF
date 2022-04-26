package billing;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.billing;

public class billingService {

	
	billing cusObj = new billing(); 
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCusDetail() 
	 { 
		return cusObj.readCusDetail(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(@FormParam("CusName") String cusname, 
							     @FormParam("AccNo") String accno,
							     @FormParam("Moblie") String mobile, 
							     @FormParam("Password") String password ){ 
		
		String output = cusObj.insertCustomer(cusname, accno, mobile, password); 
		return output; 
		
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String cusData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject CustomerObject = new JsonParser().parse(cusData).getAsJsonObject(); 
		//Read the values from the JSON object
		String CusId = CustomerObject.get("CusId").getAsString(); 
		String CusName = CustomerObject.get("CusName").getAsString();
		String AccNo = CustomerObject.get("AccNo").getAsString(); 
		String Moblie = CustomerObject.get("Moblie").getAsString(); 
		String Password = CustomerObject.get("Password").getAsString(); 
		String output = cusObj.updateCustomer(CusId, CusName, AccNo, Moblie, Password); 
		return output; 
		
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String cusData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(cusData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String CusId = doc.select("CusId").text(); 
		String output = cusObj.deleteCustomer(CusId); 
		return output; 
	}

	
			
}

