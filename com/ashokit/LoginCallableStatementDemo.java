package com.ashokit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class LoginCallableStatementDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			
			CallableStatement cstmt = con.prepareCall("{call validate_login(?,?,?)}");
				
			Scanner sc = new Scanner(System.in);
		   ){
			
			System.out.println("Enter Username....");
			String username = sc.next();
			
			System.out.println("Enter Password.....");
			String password = sc.next();
			
			//registering the out parameter
			cstmt.registerOutParameter(3, Types.VARCHAR);
			
			//setting input values
			cstmt.setString(1, username);
			cstmt.setString(2, password);
			
			//executing the stored procedure
			boolean flag = cstmt.execute();
			
			//checking flag
			if(!flag) {
				System.out.println("Procedure Executed Successfully.....");
				//collecting the output variable value.....
				String login_status = cstmt.getString(3);
				System.out.println("Login Status :::: " + login_status);
			}else {
				System.out.println("Procedure Error While Executing.....");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
