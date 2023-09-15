package com.ashokit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.Informing to browser for sending HTML content
		response.setContentType("text/html");
		
		//2.Getting Writer Object for Preparing Response
		PrintWriter pw = response.getWriter();
		
		//3.Reading the form data by using request object
		Enumeration<String> reqParamNames = request.getParameterNames();
		
		//4.Processing the Enumeration object based on requestParamName will get reqParamValue
		pw.println("<div style='text-align:center;color:red;border:2px solid blue;'>");
		while(reqParamNames.hasMoreElements()) {
			
			//Getting the Request parameter Name
			String currentRequestParameterName = reqParamNames.nextElement();
			
			//Getting the Request Parameter Value
			String currentRequestParameterValue = request.getParameter(currentRequestParameterName);
			pw.println("<span style='color:green;'>"+currentRequestParameterName+"</span><span>-----></span><span style='color:red;'>"+currentRequestParameterValue+"</span><hr/>");
			
		}
		pw.println("</div>");
		//creating HomePage Hyperlink to navigate signup.html
		pw.println("<div style='text-align:center;'><a href='signup.html'> | Home Page| </a></div>");
	}
}