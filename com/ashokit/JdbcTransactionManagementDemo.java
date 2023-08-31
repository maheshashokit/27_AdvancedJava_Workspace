package com.ashokit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTransactionManagementDemo {
	
	public static void main(String[] args) throws Exception {
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			Statement st = con.createStatement();
				
		    Scanner sc = new Scanner(System.in);
		   ){
			
			System.out.println("Enter Source Account AccountNo For Transfer Amt...");
			String sourceAccountNo = sc.next();
			System.out.println("Enter Destination Account AccountNo For Transfer Amt...");
			String destinationAccountNo = sc.next();
			System.out.println("Enter Amount To be Transferred.....");
			int amount = sc.nextInt();
			
			//Disabling the AutoCommit mode
			con.setAutoCommit(false);
			
			//Adding our required query to batch
			st.addBatch("update ashokit_accounts set account_balance = account_balance - "+amount+" where account_id='"+sourceAccountNo+"'");
			st.addBatch("update ashokit_accounts set account_balance = account_balance + "+amount+" where account_id='"+destinationAccountNo+"'");
		
			//Executing queries as batch
			int[] rowsUpdateCount = st.executeBatch();
			
			//processing the rows Array
			boolean transactionStatus = true;
			for(int i = 0  ; i <rowsUpdateCount.length ; i++) {
				if(rowsUpdateCount[i] == 0) { //Indicates the failure 
					transactionStatus = false; 
					break;
				}
			}
			
			//checking the transaction status flag
			if(transactionStatus) {
				con.commit();
				System.out.println("Amount Transferred Successfully......");
			}else {
				con.rollback();
				System.out.println("Amount Transferred Error Due to Technical issue.........");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}