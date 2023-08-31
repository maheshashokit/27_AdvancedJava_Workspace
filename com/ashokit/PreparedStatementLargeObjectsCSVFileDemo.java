package com.ashokit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class PreparedStatementLargeObjectsCSVFileDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		File imageFile = new File("D:\\test.pdf");
		File commentFile = new File("D:\\Clob_Text.txt");
		
		//Reading the CSV File Logic
		try(
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				
			FileReader fr = new FileReader("src/customers2.csv");
			
			BufferedReader br = new BufferedReader(fr);
				
			PreparedStatement pstmt = con.prepareStatement("insert into ashokit_customers values(?,?,?,?,?,?,?,?,?)");
		   ){
			
			//Reading First Line
			String currentLine = br.readLine();
			
			int totalRowsInserted = 0;
			
			//Creating the SimpleDateFormat object and below date format supplying from csv file
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//processing the lines
			while(currentLine != null) {
				String[] customerValues = currentLine.split(",");
				
				//setting the values for PlaceHolders
				pstmt.setInt(1, Integer.valueOf(customerValues[0]));
				pstmt.setString(2, customerValues[1]);
				pstmt.setString(3, customerValues[2]);
				pstmt.setInt(4, Integer.valueOf(customerValues[3]));//billamount
				
				//Getting the date value as string
				String dateValueFromFile = customerValues[4];
				//converting the string value into java.util.Date
				java.util.Date purchasedDate = sdf.parse(dateValueFromFile);
				//converting the java.util.date into java.sql.Date
				java.sql.Date dbPurchasedDate = new java.sql.Date(purchasedDate.getTime());
				//setting java.sql.Date value into db
				pstmt.setDate(5, dbPurchasedDate);
				
				//storing created TimeStamp value
				//new java.util.Date().getTime() represent the current date as milliseconds
				pstmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
				
				//storing the CreatedBy value
				pstmt.setString(7, customerValues[1]);
				
				//setting value for Blob column
				//Pointing the images in our local drive
				FileInputStream imageStream = new FileInputStream(imageFile);
				FileReader commentStream = new FileReader(commentFile);
				
				pstmt.setBinaryStream(8,imageStream,imageFile.length());
				pstmt.setCharacterStream(9, commentStream,commentFile.length());
				
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