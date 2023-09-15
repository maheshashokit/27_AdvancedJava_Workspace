package com.ashokit.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class WelcomeServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		//1.Providing the instruction To Browser for sending what kind of data html,xml,pdf,Excel etc.,
		response.setContentType("text/html");
		
		//2.Getting the Writer Object
		PrintWriter pw = response.getWriter();
		
		//3.Generating the wishes messages
		pw.println("<div style='text-align:center;color:blue'> Welcome To AshokIT For Servlet Programming</div>");
	}
}
