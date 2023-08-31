package com.ashokit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class CallableCursorDemo {

	public static void main(String[] args) throws ClassNotFoundException{

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");

			CallableStatement cstmt = con.prepareCall("{call Get_All_Customers(?)}");

		    ) {
			// registering the out parameter
			cstmt.registerOutParameter(1,OracleTypes.CURSOR);

			// executing the stored procedure
			boolean flag = cstmt.execute();

			// checking flag
			if (!flag) {
				System.out.println("Procedure Executed Successfully.....");
				// collecting the output variable value.....
				ResultSet rs  = (ResultSet)cstmt.getObject(1);
				
				//process the ResultSet Objecty
				while(rs.next()) {
				   System.out.println(rs.getString(1)+"===="+ rs.getString(2)+"===="+rs.getString(3)+"===="+ rs.getString(4));
				}
				
			} else {
				System.out.println("Procedure Error While Executing.....");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
