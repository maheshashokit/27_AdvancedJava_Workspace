package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class StudentUpdateDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		//1.Loading & Registering the Jdbc Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.Getting Connection using DriverManager service
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			
		    //Reading the Data from Keyboard
		    Scanner sc = new Scanner(System.in);
				
			//3.Creating the Statement object
			Statement st = con.createStatement();
		   ){
		
			System.out.println("Enter Contact-No To Be Updated.....");
			String contactNo = sc.next();
			
			System.out.println("Enter StudentId To be Updated....");
			int studentId = sc.nextInt();
			
			//Framing the SQL Query
			String update_query = "update ashokit_students set contact_no='"+contactNo+"' where student_id="+ studentId;
			System.out.println("Update Query ::::" + update_query);
			
			//4.Executing the SQL Query
			int rowAffected = st.executeUpdate(update_query);
			
			//5.Processing the RowAffected
			if(rowAffected != 0) {
				System.out.println(rowAffected + " Student Record Updated....");
			}else {
				System.out.println("No Student Present with following Student ID :::" + studentId);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}