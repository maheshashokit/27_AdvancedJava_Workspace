package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

	 public static void main(String[] args) throws Exception{
		
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/27_advancedjava","root","root");
		 
		 if(con != null) {
			 System.out.println("MySQL Database Connected.....");
		 }else {
			 System.out.println("MySQL Database Error......");
		 }
	}
}
