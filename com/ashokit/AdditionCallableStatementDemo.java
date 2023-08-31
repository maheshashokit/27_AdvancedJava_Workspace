package com.ashokit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class AdditionCallableStatementDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			
			CallableStatement cstmt = con.prepareCall("{call addition_two_numbers(?,?,?)}");
				
			Scanner sc = new Scanner(System.in);
		   ){
			
			System.out.println("Enter First Value.....");
			int firstValue = sc.nextInt();
			
			System.out.println("Enter Second Value....");
			int secondValue = sc.nextInt();
			
			//registering the out parameter
			cstmt.registerOutParameter(3, Types.INTEGER);
			
			//setting input values
			cstmt.setInt(1, firstValue);
			cstmt.setInt(2, secondValue);
			
			//executing the stored procedure
			boolean flag = cstmt.execute();
			
			//checking flag
			if(!flag) {
				System.out.println("Procedure Executed Successfully.....");
				//collecting the output variable value.....
				int sumOfNumbers = cstmt.getInt(3);
				System.out.println("Addition Of Two Numbers::::" + sumOfNumbers);
			}else {
				System.out.println("Procedure Error While Executing.....");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
