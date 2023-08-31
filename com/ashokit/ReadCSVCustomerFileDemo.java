package com.ashokit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ReadCSVCustomerFileDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Reading the CSV File Logic
		try(
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				
			FileReader fr = new FileReader("src/customers.csv");
			
			BufferedReader br = new BufferedReader(fr);
				
			Statement stmt = con.createStatement();
		   ){
			
			//Reading First Line
			String currentLine = br.readLine();
			
			int totalRowsInserted = 0;
			//processing the lines
			while(currentLine != null) {
				String[] customerValues = currentLine.split(",");
				
				String insert_query="insert into ashokit_customers values("+Integer.valueOf(customerValues[0])+",'"+customerValues[1]+"','"+customerValues[2]+"')";
				System.out.println(insert_query);
				//executing the query
				int rowStatus = stmt.executeUpdate(insert_query);
				
				totalRowsInserted+= rowStatus;
				
				//moving to nextLine to read
				currentLine = br.readLine();
			}
			System.out.println("Total Rows Inserted......" + totalRowsInserted);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}