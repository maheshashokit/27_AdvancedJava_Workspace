package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserLoginDemo {

	public static void main(String[] args) throws ClassNotFoundException {

		// 1.Loading & Registering the Jdbc Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2.Getting Connection using DriverManager service
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");

				// Reading the Data from Keyboard
				Scanner sc = new Scanner(System.in);

				// 3.Creating the Statement object
				Statement st = con.createStatement();) {

			System.out.println("Enter Username To Validate.");
			String username = sc.next();

			System.out.println("Enter Password To Validate.");
			String password = sc.next();

			// Framing the SQL Query
			String select_query = "select count(*) from ashokit_logins where login_id='" + username
					+ "' and login_pwd='" + password + "'";
			System.out.println("Select Query ::::" + select_query);

			// 4.Executing the SQL Query
			ResultSet rs = st.executeQuery(select_query);

			// 5.Process the ResultSet Object
			if (rs.next()) {
				int countValue = rs.getInt(1);

				if (countValue != 0) {
					System.out.println("Login Success.......");
				} else {
					System.out.println("Login Failure.......");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}