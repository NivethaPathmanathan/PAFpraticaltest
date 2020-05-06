package com;

import java.sql.*;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;


public class App {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare","root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String addApp(String user_id, String app_no, String doctor_name, String payment)
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
			String query = " insert into app(`user_id`,`app_no`,`doctor_name`,`payment`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, user_id);
			preparedStmt.setString(2, app_no);			
			preparedStmt.setString(3, doctor_name);
			preparedStmt.setDouble(4, Double.parseDouble(payment));
			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Appointment add successfully";
		}
		catch (Exception e)
		{
			 output = "Error while inserting";
			 System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteApp(String app_no)
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
			String query = "delete from app where app_no='"+app_no+"'";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Appointment deleted";
		}
		catch (Exception e)
		{
			 output = "Error while delete";
			 System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	public String readApp()
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
			output = "<table border=\"1\"><tr><th>User ID</th>"
					+"<th>Appointment ID</th><th>Doctor name</th>"
					+ "<th>Payment Amount</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from app";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String user_id = rs.getString("user_id");
				String app_no = rs.getString("app_no");
				String doctor_name = rs.getString("doctor_name");
				String payment = Double.toString(rs.getDouble("payment"));
				// Add into the html table
				output += "<tr><td>" + user_id + "</td>";
				output += "<td>" + app_no + "</td>"; 
				output += "<td>" + doctor_name + "</td>";
				output += "<td>" + payment + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" "
						+ " type=\"button\" value=\"Update\"></td>"
						+ "<td><input name=\btnRemove\" "
						+ " type=\"button\" value=\"Delete\"></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
