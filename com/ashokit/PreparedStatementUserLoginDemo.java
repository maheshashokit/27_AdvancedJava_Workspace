package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreparedStatementUserLoginDemo {

	public static void main(String[] args) throws ClassNotFoundException {

		// 1.Loading & Registering the Jdbc Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2.Getting Connection using DriverManager service
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");

			  // Reading the Data from Keyboard
			 Scanner sc = new Scanner(System.in);

			// 3.Creating the Statement object
			PreparedStatement st = con.prepareStatement("select count(*) from ashokit_logins where login_id=? and login_pwd=?");) {

			System.out.println("Enter Username To Validate.");
			String username = sc.next();
			System.out.println("Enter Password To Validate.");
			String password = sc.next();
			
			//setting the Username & password values
			st.setString(1,username);
			st.setString(2, password);

			// 4.Executing the SQL Query
			ResultSet rs = st.executeQuery();

			// 5.Process the ResultSet Object
			if (rs.next()) {
				int countValue = rs.getInt(1);

				if (countValue != 0) {
					System.out.println("Login Success.......");
				} else {
					System.out.println("Login Failure.......");
				}
			}
			
			System.out.println("Connection Interface Impl Classs...." + con.getClass().getName());
			System.out.println("PreparedStatement Interface Impl Classs...." + st.getClass().getName());
			System.out.println("ResultSet Interface Impl Classs...." + rs.getClass().getName());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}