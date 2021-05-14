package com;
import  java.sql.*;

public class customerpay {

	//creating a connetion to the database
	
	private Connection connect() 
	 { 
		 Connection con = null; 
		 try
		 { 
			 Class.forName("com.mysql.cj.jdbc.Driver"); 
			 con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "abc123"); 
			 
		 } 
		 catch (Exception e) 
		 { 
			 e.printStackTrace(); 
		 } 
		 return con; 
	 } 

	
	//read customer payment details
	public String viewCustomerpay()
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
	 output = "<table border='2'><tr><th>Card ID</th><th>Card Number</th><th>Name on Card</th>" +
			 "<th>Expiration date</th>" +
			 "<th>Cvv</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
	 
	 
	 String query = "select * from customer_payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 
	 while (rs.next()) 
	 { 
		 String cardId = Integer.toString(rs.getInt("Card_Id"));
		 String cardNo = rs.getString("Card_No");
		 String nameonCard = rs.getString("Name_on_card");
		 String expDate = rs.getString("Exp_date");
		 String cvv = rs.getString("Cvv");
	 
		// Add into the html table
				 output += "<tr><td>" + cardId  + "</td>";
				 output += "<td>" + cardNo  + "</td>";
				 output += "<td>" + nameonCard + "</td>";
				 output += "<td>" + expDate + "</td>";
				 output += "<td>" + cvv  + "</td>";
	
	//buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-cardid='" + cardId + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-cardid='" + cardId + "'></td></tr>"; 
	
	
	} 
	 
		con.close(); 
		// Complete the html table
		output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
		output = "Error while reading the customer payment details."; 
		System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	
	//insert customer details method
	public String insertCustomerpay(String cardNo, String nameonCard, String expDate, String cvv) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for inserting."; 
			 } 
			 // create a prepared statement
			 String query = " insert into customer_payment (`Card_Id`,`Card_No`,`Name_on_card`,`Exp_date`,`Cvv`)"
					 + " values (?, ?, ?, ?, ?)";
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setString(2, cardNo); 
					 preparedStmt.setString(3, nameonCard); 
					 preparedStmt.setString(4, expDate);
					 preparedStmt.setString(5, cvv); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 String newCustomer = viewCustomerpay(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newCustomer + "\"}"; 
					 } 
					 catch (Exception e) 
					 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while inserting customer payment details.\"}"; 
					 System.err.println(e.getMessage()); 
					 } 
					 return output; 
					 } 
	
	
	//customer payment update method
	public String updateCustomerpay(String ID, String cardNo, String nameonCard, String expDate, String cvv) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE customer_payment SET Card_No=?,Name_on_card=?,Exp_date=?,Cvv=?  WHERE Card_Id=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, cardNo); 
			 preparedStmt.setString(2, nameonCard); 
			 preparedStmt.setString(3, expDate); 
			 preparedStmt.setString(4, cvv); 
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newCustomer = viewCustomerpay();  
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newCustomer + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the customer payment details.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
	
	
	//delete customer payment details method
	public String deleteCustomerpay(String Card_Id) 
	 { 
	 String output = ""; 
	 try
	 { 
		Connection con = connect(); 
	 if (con == null) 
	 { 
		 return "Error while connecting  to the database for deleting."; 
	 } 
	 
	 // create a prepared statement
	 String query = "delete from customer_payment where Card_Id=?";  
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(Card_Id)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newCustomer = viewCustomerpay();  
	 output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";  
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer payment detail.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	//end of the code
}
