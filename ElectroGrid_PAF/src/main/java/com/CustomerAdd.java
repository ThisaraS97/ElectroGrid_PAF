package com;

import java.sql.*;





public class CustomerAdd {
	
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eletrogrid_paf?useSSL=false","root",""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
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
	 output = "Inserted successfully"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while inserting"; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
	public String readItems()
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
		 		+"<th>Account No</th>" 
		 		+"<th> Name</th>"
		 		+ "<th>Mobile</th>" 
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th>"
	 		+ "</tr>"; 
	 String query = "select * from customerdetails"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String CusId = Integer.toString(rs.getInt("CusId")); 
	 String CusName = rs.getString("CusName"); 
	 String Moblie = rs.getString("Moblie"); 
	 String AccNo = rs.getString("AccNo"); 
	 // Add a row into the html table
	 output += "<tr><td>" + CusName + "</td>"; 
	 output += "<td>" + AccNo + "</td>"; 
	output += "<td>" + Moblie + "</td>";
	
	
	 // buttons
	 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class='btn btn-warning'></td>"
			 + "<td><form method='post' action='CustomerAdd.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		
			 + "<input name='CusId' type='hidden' " 
			 + " value='" + CusId + "'>" + "</form></td></tr>"; 
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

	public String deleteCustomer(String CusId) 
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
	 String query = "delete from CustomerDetails where CusId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(CusId)); 
	 
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

	
	public String updateItem(String CusId, String CusName, String AccNo, String Mobile, String Password){ 
		
		String output = ""; 
		
		try{ 
				Connection con = connect(); 
				if (con == null){
					return "Error while connecting to the database for updating.";
					} 
				// create a prepared statement
				String query = "UPDATE CustomerDetails SET CusId=?,CusName=?,AccNo=?,Mobile=? WHERE CusId=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, CusId); 
				preparedStmt.setString(2, CusName); 
				preparedStmt.setString(3, AccNo); 
				preparedStmt.setString(4, Mobile); 
				preparedStmt.setInt(5, Integer.parseInt(CusId)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Updated successfully"; 
		} 
		
		catch (Exception e){ 
			
			output = "Error while updating the item."; 
			System.err.println(e.getMessage()); 
			
		} 
		
		return output; 
} 
	
	
	
	
}
