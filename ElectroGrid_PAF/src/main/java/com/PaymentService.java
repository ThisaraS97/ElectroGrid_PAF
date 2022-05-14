package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Payment") 
public class PaymentService{ 
	
	Payment payObj = new Payment(); 
			
	/*Read Customer Details*/
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readPayDetail() 
			 { 
				return payObj.readPayDetail(); 
			}
			
	/*Insert Customer Details*/
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertPayment(@FormParam("AccNo") String AccNo, 
									     @FormParam("BillID") String BillID,
									     @FormParam("Payment_Date") String Payment_Date, 
									     @FormParam("Amount") String Amount ){ 
				
				String output = payObj.insertPayment(AccNo, BillID, Payment_Date, Amount); 
				return output; 
				
			}
			
	/*Update Customer Details*/
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updatePayment(String payData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject payObject = new JsonParser().parse(payData).getAsJsonObject(); 
				//Read the values from the JSON object
				String PayId = payObject.get("PayId").getAsString(); 
				String AccNo = payObject.get("AccNo").getAsString(); 
				String BillID = payObject.get("BillID").getAsString(); 
				String Payment_Date = payObject.get("Payment_Date").getAsString(); 
				String Amount = payObject.get("Amount").getAsString(); 
				String output = payObj.updatePayment(PayId, AccNo, BillID, Payment_Date, Amount); 
				return output; 
				
			}
			
			
	/*Delete Customer Details*/
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deletePayment(String payData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(payData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <CudId>
				String PayId = doc.select("PayId").text(); 
				String output = payObj.deletePayment(PayId); 
				return output; 
			}

			
					
}