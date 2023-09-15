package com.ashokit;

import java.sql.Connection;

import com.mysql.cj.jdbc.MysqlDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DataSourceDemo {
	
	public static void main(String[] args) throws Exception {

		// Creating object of OracleDataSource class
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		ods.setUser("system");
		ods.setPassword("manager");

		// Getting the Connection object
		Connection con = ods.getConnection();
		if (con != null) {
			System.out.println("Oracle Connected......");
		}

		MysqlDataSource mds = new MysqlDataSource();
		mds.setURL("jdbc:mysql://localhost:3306/27_advancedjava");
		mds.setUser("root");
		mds.setPassword("root");

		Connection mySqlCon = mds.getConnection();
		if (mySqlCon != null) {
			System.out.println("MySQL Connected.....");
		}
	}
}