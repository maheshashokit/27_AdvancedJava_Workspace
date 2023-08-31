package com.ashokit;

import java.util.Scanner;

public class Demo {
	
	
	public static void main(String[] args) {
		try {
			//We are loading the Employee class into JVM Memory Explicitly
			Class.forName("com.ashokit.Customer");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Inside the Catch Block......");
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name");
		String name = sc.next();
		sc.close();
		
			
	}
}