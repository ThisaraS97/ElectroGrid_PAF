package com; 
import java.sql.*; 

public class customer 
{ //A common method to connect to the DB
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
		public String insertCustomer(String cusname, String accno, String mobile, String password)
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
		 String query = " insert into CustomerDetails (`CusId`,`CusName`,`AccNo`,`Moblie`,`Password`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, cusname); 
		 preparedStmt.setString(3, accno); 
		 preparedStmt.setString(4, mobile); 
		 preparedStmt.setString(5, password); 
		  //execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readCusDetail(); 
			output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 
		}  
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Customer Details.\"}"; 
			System.err.println(e.getMessage()); 
		}
		return output; 
		
		}
		
		
		/*Read Customer Details*/
			public String readCusDetail()
				
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
				 output ="<table border=\"1\" class=\"table\">"
				 		+ "<tr>"
						 		+"<th>CusId</th>"
					 		+"<th>Name</th>" 
					 		+"<th> Account No</th>"
					 		+ "<th>Mobile</th>" 
					 		+ "<th>Password</th>" 
					 		+ "<th>Update</th>"
					 		+ "<th>Remove</th>"
				 		+ "</tr>"; 
				 String query = "select * from customerdetails"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
				 String CusId = rs.getString("CusId"); 
				 String CusName = rs.getString("CusName"); 
				 String AccNo = rs.getString("AccNo"); 
				 String Moblie = rs.getString("Moblie"); 
				 String Password = rs.getString("Password"); 

				
				 // Add a row into the html table
				 output += "<tr><td>" + CusId + "</td>"; 
				 output +=     "<td>" + CusName + "</td>"; 
				 output +=     "<td>" + AccNo + "</td>"; 
				 output +=     "<td>" + Moblie + "</td>";
				 output +=     "<td>" + Password + "</td>";

				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-Cusid='" + CusId + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-Cusid='" + CusId + "'></td></tr>"; 
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
			public String updateCustomer(String CusId, String CusName, String AccNo, String Moblie, String Password){ 
				
				String output = ""; 
				
				try{ 
						Connection con = connect(); 
						if (con == null){
							return "Error while connecting to the database for updating.";
							} 
						// create a prepared statement
						String query = "UPDATE customerdetails SET CusName=?,AccNo=?,Moblie=?,Password=? WHERE CusId=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setString(1, CusName); 
						preparedStmt.setString(2, AccNo); 
						preparedStmt.setString(3, Moblie); 
						preparedStmt.setString(4, Password); 
						preparedStmt.setInt(5, Integer.parseInt(CusId)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newItems = readCusDetail(); 
						output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 				} 
				
			
			
			catch (Exception e){ 
				
				output = "{\"status\":\"error\",\"data\":\"Error while updating the item.\"}"; 

				System.err.println(e.getMessage()); 
				
			} 
			
			return output; 
	} 
			/*Delete Customer Details*/
			public String deleteCustomer(String CusId){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from CustomerDetails where CusId=?";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setString(1,CusId); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newItems = readCusDetail(); 
						 output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the Customer.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
} 
