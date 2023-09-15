package com.ashokit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class DateAndTimeServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		 //1.Providing instruction to browser for sending html tags
		 response.setContentType("text/html");
		 
		 //2.Getting Writer Object
		 PrintWriter pw = response.getWriter();
		 
		 //3.Preparing the Response
		 Date currentDate = new Date();
		 pw.println("<div style='text-align:center;color:blue'> Welcome To AshokIT For Servlet Programming</div>");
		 pw.println("<div style='text-align:center;color:green'>"+currentDate+"</div>");
	}

}
