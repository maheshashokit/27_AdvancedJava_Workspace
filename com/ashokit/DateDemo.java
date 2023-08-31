package com.ashokit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
	
	public static void main(String[] args) {
		
		Date currentDate = new Date();
		System.out.println(currentDate);
		
		//Wanted to display the date with following format dd-MONTHNAME-YYYY
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
		System.out.println("Current Date Formatted:::" +sdf.format(currentDate));
	}

}
