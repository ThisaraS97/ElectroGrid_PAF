package com; 

import model.customer;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Customer") 
public class CustomerService{ 
	
	customer cusObj = new customer(); 
			
	/*Read Customer Details*/
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readCusDetail() 
			 { 
				return cusObj.readCusDetail(); 
			}
			
	/*Insert Customer Details*/
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
			
	/*Update Customer Details*/
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updateCustomer(String cusData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject cusObject = new JsonParser().parse(cusData).getAsJsonObject(); 
				//Read the values from the JSON object
				String CusId = cusObject.get("CusId").getAsString(); 
				String CusName = cusObject.get("CusName").getAsString(); 
				String AccNo = cusObject.get("AccNo").getAsString(); 
				String Moblie = cusObject.get("Moblie").getAsString(); 
				String Password = cusObject.get("Password").getAsString(); 
				String output = cusObj.updateCustomer(CusId, CusName, AccNo, Moblie, Password); 
				return output; 
				
			}
			
			
	/*Delete Customer Details*/
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteCustomer(String cusData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(cusData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <CudId>
				String CusId = doc.select("CusId").text(); 
				String output = cusObj.deleteCustomer(CusId); 
				return output; 
			}

			
					
}