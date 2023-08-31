package com.ashokit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementCSVFileDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Reading the CSV File Logic
		try(
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				
			FileReader fr = new FileReader("src/customers.csv");
			
			BufferedReader br = new BufferedReader(fr);
				
			PreparedStatement pstmt = con.prepareStatement("insert into ashokit_customers values(?,?,?)");
		   ){
			
			//Reading First Line
			String currentLine = br.readLine();
			
			int totalRowsInserted = 0;
			//processing the lines
			while(currentLine != null) {
				String[] customerValues = currentLine.split(",");
				
				//setting the values for PlaceHolders
				pstmt.setInt(1, Integer.valueOf(customerValues[0]));
				pstmt.setString(2, customerValues[1]);
				pstmt.setString(3, customerValues[2]);
				
				//executing the query
				int rowStatus = pstmt.executeUpdate();
				
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