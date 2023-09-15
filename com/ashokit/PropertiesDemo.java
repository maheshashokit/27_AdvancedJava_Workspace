package com.ashokit;

import java.io.FileReader;
import java.util.Properties;

public class PropertiesDemo {
	
	public static void main(String[] args) {
		
		//First way working with Properties object
		
		//creating object for Properties class
		Properties props = new Properties();
		
		//add entries into props object
		props.setProperty("Username", "Mahesh");
		props.setProperty("Password", "Mahesh@123");
		props.setProperty("EmailId", "mahesh.ashokit@gmail.com");
		props.setProperty("ContactNo", "1234556");
		
		//printing the properties object
		System.out.println(props);
		
		//Getting the value for the Property
		String emailIdValue = props.getProperty("EmailId-1");
		System.out.println("Email ID :::::" + emailIdValue);
		
		String userNameValue = props.getProperty("Username-1", "AshokIT");
		System.out.println("UserName:::::" + userNameValue);
		
		
		//second way for working with Properties
		try(FileReader fr = new FileReader("src/Database.properties");){
			
			//Creating new Properties object for storing DB Configuration
			Properties dbProps = new Properties();
			
			//loading the properties file information into dbprops object
			dbProps.load(fr);
			
			//printing Properties
			System.out.println(dbProps);
			
			//Reading the properties information
			System.out.println(dbProps.getProperty("OracleJdbcDriverClass"));
			System.out.println(dbProps.getProperty("OracleJdbcURL"));
			System.out.println(dbProps.getProperty("ActiveDatabase"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
