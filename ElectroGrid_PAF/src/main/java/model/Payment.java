package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	 //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eletrogrid_paf?useSSL=false","root",""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		/*Insert Customer*/
		public String insertPayment(String AccNo, String BillID, String Payment_Date, String Amount)
		{ 
		 String output = ""; 
		try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database"; 
		 } 
		 // create a prepared statement
		 String query = " insert into PaymentDetails (`PayId`,`AccNo`,`BillID`,`Payment_Date`,`Amount`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, AccNo); 
		 preparedStmt.setString(3, BillID); 
		 preparedStmt.setString(4, Payment_Date); 
		 preparedStmt.setString(5, Amount); 
		  //execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while inserting"; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}
		
		
		/*Read Customer Details*/
			public String readPayDetail()
				
			{ 
				 String output = ""; 
				try
				 { 
				 Connection con = connect(); 
				 if (con == null) 
				 { 
				 return "Error while connecting to the database for reading."; 
				 } 
				 // Prepare the html table to be displayed
				 output ="<table border='3'>"
				 		+ "<tr>"
					 		+"<th>AccNo</th>" 
					 		+"<th> BillID</th>"
					 		+ "<th>Payment_Date</th>" 
					 		+ "<th>Amount</th>" 
					 		+ "<th>Update</th>"
					 		+ "<th>Remove</th>"
				 		+ "</tr>"; 
				 String query = "select * from PaymentDetails"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
				 String PayId = Integer.toString(rs.getInt("PayId")); 
				 String AccNo = rs.getString("AccNo"); 
				 String BillID = rs.getString("BillID"); 
				 String Payment_Date = rs.getString("Payment_Date"); 
				 String Amount = rs.getString("Amount"); 

				
				 // Add a row into the html table
				 output += "<tr><td>" + AccNo + "</td>"; 
				 output += "<td>" + BillID + "</td>"; 
				output += "<td>" + Payment_Date + "</td>";
				output += "<td>" + Amount + "</td>";

				
				
				 // buttons
				 output += "<td><input name='btnUpdate' " 
						 + " type='button' value='Update' class='btn btn-warning'></td>"
						 + "<td><form method='post' action='CustomerAdd.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					
						 + "<input name='PayId' type='hidden' " 
						 + " value='" + PayId + "'>" + "</form></td></tr>"; 
				 } 
				 con.close(); 
				 // Complete the html table
				 output += "</table>"; 
				 } 
				catch (Exception e) 
				 { 
				 output = "Error while reading the Account Details."; 
				 System.err.println(e.getMessage()); 
				 } 
				return output; 
				}
			
			/*Update Customer Details*/
			public String updatePayment(String PayId, String AccNo, String BillID, String Payment_Date, String Amount){ 
				
				String output = ""; 
				
				try{ 
						Connection con = connect(); 
						if (con == null){
							return "Error while connecting to the database for updating.";
							} 
						// create a prepared statement
						String query = "UPDATE PaymentDetails SET AccNo=?,BillID=?,Payment_Date=?,Amount=? WHERE PayId=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setString(1, AccNo); 
						preparedStmt.setString(2, BillID); 
						preparedStmt.setString(3, Payment_Date); 
						preparedStmt.setString(4, Amount); 
						preparedStmt.setInt(5, Integer.parseInt(PayId)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Updated successfully"; 
				} 
				
				catch (Exception e){ 
					
					output = "Error while Updating Customer Details"; 
					System.err.println(e.getMessage()); 
					
				} 
				
				return output; 
		} 
			
			/*Delete Customer Details*/
			public String deletePayment(String PayId) 
			{ 
				 String output = ""; 
				try
				 { 
				 Connection con = connect(); 
				 if (con == null) 
				 { 
				 return "Error while connecting to the database for deleting."; 
				 } 
				 // create a prepared statement
				 String query = "delete from PaymentDetails where PayId=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setString(1, PayId); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Deleted successfully"; 
				 } 
				catch (Exception e) 
				 { 
				 output = "Error while deleting the Account Details."; 
				 System.err.println(e.getMessage()); 
				 } 
				return output; 
				}


			
}
