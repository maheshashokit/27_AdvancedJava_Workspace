package com.ashokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ashokit.dto.SignUp;

//To Perform the JDBC Operations
public class SignUpDao {
	
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private Statement stmt= null;
	private ResultSet rs = null;
	
	//defining the static block
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Jdbc Driver Class Loading Issue......");
		} 
	}
	
	public boolean createBrandNewSignUp(SignUp signUp) {
		boolean createdStatus = false;
		try {
			if(con != null) {
				pstmt = con.prepareStatement("insert into maheshit_enquires values(maheshit_enquires_seq.nextval,?,?,?,?,?,?)");
				//setting the data for placeholders
				pstmt.setString(1, signUp.getName());
				pstmt.setString(2, signUp.getEmailId());
				pstmt.setString(3, signUp.getContactNo());
				pstmt.setString(4, signUp.getCourseName());
				pstmt.setString(5, signUp.getName());
				pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
				//executing query
				int rowCount = pstmt.executeUpdate();
				createdStatus = rowCount > 0;
				return createdStatus;
			}
		}catch(Exception e) {
			System.out.println("Exception Occurred During creation of New Signup...");
			e.printStackTrace();
		}
		return createdStatus;
	}
	
	public List<SignUp> getAllSignUps(){
		List<SignUp> signUpList = new ArrayList<>();
		try {
			if(con != null) {
				stmt = con.createStatement();
				rs = stmt.executeQuery("select name,email_id,contact_no,course_name from maheshit_enquires");
				while(rs.next()) {
					//converting each row of ResultSet into SignUp Object
					SignUp signUp = new SignUp(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					//Adding each SignUp object into ArrayList
					signUpList.add(signUp);
				}
			}
		}catch(Exception e) {
			System.out.println("Exception Occured During All Signups information....");
			e.printStackTrace();
		}
		return signUpList;
	}

}
