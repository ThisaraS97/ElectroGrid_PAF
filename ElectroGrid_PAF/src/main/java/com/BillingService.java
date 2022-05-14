package com;
import model.Bill;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



	@Path("/Bill") 
	public class BillingService{ 
		
		Bill billObj = new Bill(); 
				
		/*Read Customer Details*/
				@GET
				@Path("/") 
				@Produces(MediaType.TEXT_HTML) 
				public String readBillDetail() 
				 { 
					return billObj.readBillDetail(); 
				}
				
		/*Insert Customer Details*/
				@POST
				@Path("/") 
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
				@Produces(MediaType.TEXT_PLAIN) 
				public String insertBill(@FormParam("AccNo") String AccNo, 
										     @FormParam("TotalUnit") String TotalUnit,
										     @FormParam("TotalAmount") String TotalAmount, 
										     @FormParam("Month") String Month ){ 
					
					String output = billObj.insertBill(AccNo, TotalUnit, TotalAmount, Month); 
					return output; 
					
				}
				
		/*Update Customer Details*/
				@PUT
				@Path("/") 
				@Consumes(MediaType.APPLICATION_JSON) 
				@Produces(MediaType.TEXT_PLAIN) 
				public String updateBill(String billData) 
				{ 
					//Convert the input string to a JSON object 
					JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject(); 
					//Read the values from the JSON object
					String BillId = billObject.get("BillId").getAsString(); 
					String AccNo = billObject.get("AccNo").getAsString(); 
					String TotalUnit = billObject.get("TotalUnit").getAsString(); 
					String TotalAmount = billObject.get("TotalAmount").getAsString(); 
					String Month = billObject.get("Month").getAsString(); 
					String output = billObj.updateBill(BillId, AccNo, TotalUnit, TotalAmount, Month); 
					return output; 
					
				}
				
				
		/*Delete Customer Details*/
				@DELETE
				@Path("/") 
				@Consumes(MediaType.APPLICATION_XML) 
				@Produces(MediaType.TEXT_PLAIN) 
				public String deleteBill(String billData) 
				{ 
					//Convert the input string to an XML document
					Document doc = Jsoup.parse(billData, "", Parser.xmlParser()); 
				 
					//Read the value from the element <CudId>
					String BillId = doc.select("BillId").text(); 
					String output = billObj.deleteBill(BillId); 
					return output; 
				}

				
						
	}