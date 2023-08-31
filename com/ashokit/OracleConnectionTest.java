package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OracleConnectionTest {

	public static void main(String[] args) {
	
		try {
			
			//1. Loading Driver Class & Registering Driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2.Getting the connection object from DriverManager Service
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");

			if (con != null) {
				System.out.println("Connection(I) Implementation Class::::" + con.getClass().getName());
				System.out.println("Oracle Database Connected.....");
				
				//3.Creating Statement object for executing the SQL Queries
				Statement stmt = con.createStatement();
				System.out.println("Statement(I) Implmenetation Class::::" + stmt.getClass().getName());
			} else {
				System.out.println("Oracle Database Connection Error....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
