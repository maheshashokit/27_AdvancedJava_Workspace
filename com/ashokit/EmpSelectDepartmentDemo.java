package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmpSelectDepartmentDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		//1.Loading & Registering the Jdbc Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.Getting Connection using DriverManager service
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			
		    //Reading the Data from Keyboard
		    Scanner sc = new Scanner(System.in);
				
			//3.Creating the Statement object
			Statement st = con.createStatement();
		   ){
		
			System.out.println("Enter Department Name To Retrieve Associated Employees...");
			String departmentName = sc.next();
			
			//Framing the SQL Query
			String select_query = "select e.empno,e.ename,e.sal,d.dname from emp e inner join dept d on e.deptno= d.deptno  where d.dname='"+departmentName.toUpperCase()+"'";
			System.out.println("Select Query ::::" + select_query);
			
			//4.Executing the SQL Query
			ResultSet rs = st.executeQuery(select_query);
			
			//5.Process the ResultSet Object
			while(rs.next()) {
				System.out.println(rs.getString(1)+"----"+ rs.getString(2)+"----"+rs.getString(3)+"----"+rs.getString(4));
				//System.out.println(rs.getString("empno")+"----"+ rs.getString("ename")+"----"+rs.getString("job")+"----"+rs.getString("sal"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}