package model;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inquiry {
	
	public String insertInquiry(String accNo, String name, String contact, String email, String det) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into inquiries(`inquiryID`,`accountNum`,`Name`,`contactNum`,`email`,`inquiryDet`)"
					+ " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, accNo); 
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, contact);
			preparedStmt.setString(5, email); 
			preparedStmt.setString(6, det); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readInquiries();
			
			System.out.println(newItems);

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String readInquiries() 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table class='table table-dark table-hover'><tr><th>Account No</th><th>Name</th>" +
					"<th>Contact No</th>" + 
					"<th>email</th>" +
					"<th>Inquiry Details</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from inquiries"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String inquiryID = Integer.toString(rs.getInt("inquiryID")); 
				String accountNum = rs.getString("accountNum"); 
				String Name = rs.getString("Name"); 
				String contactNum = Integer.toString(rs.getInt("contactNum")); 
				String email = rs.getString("email");
				String inquiryDet = rs.getString("inquiryDet");
				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidInquiryIDUpdate' id='hidInquiryIDUpdate' value='"+inquiryID+"'>" + accountNum + "</td>"; 
				output += "<td>" + Name + "</td>"; 
				output += "<td>" + contactNum + "</td>"; 
				output += "<td>" + email + "</td>";
				output += "<td>" + inquiryDet + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-outline-warning' data-inquiryid='"+inquiryID+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-outline-danger' data-inquiryid='"+inquiryID+"'></td></tr>"; 
			} 
			con1.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the inquiries."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String updateInquiry(String id, String accNo, String name, String contact, String email, String det) 

	{ 
		System.out.println("came here as well");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE inquiries SET accountNum=?,Name=?,contactNum=?,email=?,inquiryDet=? WHERE inquiryID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, accNo); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, contact); 
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, det);
			preparedStmt.setInt(6, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readInquiries();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String deleteInquiry(String inquiryID) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from inquiries where inquiryID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, inquiryID); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readInquiries();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}
