package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class StudentInsertWithScannerDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		//1.Loading & Registering the Oracle Jdbc Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Defining the try-with-resource
		try(
	   	  //2.Getting the Connection object using DriverManager Service
		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		){
		    if(con != null) {
		    	System.out.println("Oracle Database is Connected.......");
		    	try(
	    			//3.Creating the statement object
			    	Statement st = con.createStatement();
		    		
		    		//Creating the scanner for Reading data
		    		Scanner sc = new Scanner(System.in);
		    	){
		    		
		    		System.out.println("Enter Student ID To be Insert.....");
		    		int studentId = sc.nextInt();
		    		System.out.println("Enter Student Name to be Insert.....");
		    		String studentName = sc.next();
		    		System.out.println("Enter Student Location to be Insert.....");
		    		String studentLocation = sc.next();
		    		System.out.println("Enter Student ContactNo to be Insert.....");
		    		String contactNo = sc.next();
		    		
		    		//constructing the query dynamically based on values
		    		//String insertQuery= "insert into ashokit_students values(04,'Suresh','Hyderabad','1122334455')";
		    		String insertQuery = "insert into ashokit_students values("+studentId+",'"+studentName+"','"+studentLocation+"','"+contactNo+"')";
		    		System.out.println(insertQuery);
		    		
		    	    //4.Executing the InsertQuery using executeUpdate() from Statement interface
		    		int rowAffected = st.executeUpdate(insertQuery);
		    		
		    		//5.Processing RowAffected Count
		    		if(rowAffected != 0) {
		    			System.out.println("Row Inserted In Database Please Verify......");
		    		}
		    	}
		    }else {
		    	System.out.println("Database Connection Error.......");
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
