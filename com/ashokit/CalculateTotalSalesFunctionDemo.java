package com.ashokit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class CalculateTotalSalesFunctionDemo {

public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			
			CallableStatement cstmt = con.prepareCall("{? = call calculate_total_price(?,?)}");
				
			Scanner sc = new Scanner(System.in);
		   ){
			
			System.out.println("Enter Unit Price Value.....");
			int unitPrice = sc.nextInt();
			
			System.out.println("Enter Quantity Value....");
			int quantity = sc.nextInt();
			
			//registering the out parameter
			cstmt.registerOutParameter(1, Types.INTEGER);
			
			//setting input values
			cstmt.setInt(2, unitPrice);
			cstmt.setInt(3, quantity);
			
			//executing the stored Function
			boolean flag = cstmt.execute();
			
			//checking flag
			if(!flag) {
				System.out.println("Function Executed Successfully.....");
				//collecting the output variable value.....
				int totalSales = cstmt.getInt(1);
				System.out.println("Total Sales::::" + totalSales);
			}else {
				System.out.println("Function Error While Executing.....");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
