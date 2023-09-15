package com.ashokit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.Giving information to browser to be render the Html tags.
		response.setContentType("text/html");
		
		//2.Getting Writer object from Response object to prepare the Response
		PrintWriter pw = response.getWriter();
		
		//3.Preparing the Response data
		pw.println("<div style='text-align:center;color:red;'>Welcome To AshokIT For WebApplication Development......</div>");
		
		//4.Reading the Request parameters
		pw.println("<div style='text-align:center;color:red;border:5px solid blue;'>");
		pw.println("<span>1.Reading Request Parameters using Request Parameter Name....</span>");
		String usernameParamValue = request.getParameter("username");
		String passwordParamValue = request.getParameter("password");
		String[] hobbiesParamValue = request.getParameterValues("hobbies");
		pw.println("<div> Username::::"+usernameParamValue+"</div>");
		pw.println("<div> Password::::"+passwordParamValue+"</div>");
		pw.println("<div> Hobbies ::::"+Arrays.toString(hobbiesParamValue)+"</div>");
		pw.println("</div><br/></br/>");
		
		pw.println("<div style='text-align:center;color:red;border:5px solid red;'>");
		pw.println("<span>2.Reading Request Parameters using Request Parameter Map Method....</span>");
		Map<String, String[]> parameterMap = request.getParameterMap();
		//processing the map object i.e.,Set<Entry> entrySet(),Set<String> keySet()
		parameterMap.forEach((eachkey,eachValue) ->{
			pw.println("<div>"+eachkey+"---->"+ Arrays.toString(eachValue)+"</div>");
		});
		pw.println("</div><br/><br/>");
		
		pw.println("<div style='text-align:center;color:red;border:5px solid green;'>");
		pw.println("<span>3.Reading Request Parameters using Enumeration....</span>");
		Enumeration<String> requestParamNames = request.getParameterNames();
		while(requestParamNames.hasMoreElements()) {
			String requestParamName = requestParamNames.nextElement();
			//String requestParamValue = request.getParameter(requestParamName);//single value
			String[] requestParamValue = request.getParameterValues(requestParamName);//Multiple value
			pw.println("<div>"+requestParamName+"---->"+ Arrays.toString(requestParamValue)+"</div>");
		}
		pw.println("</div><br/><br/>");
	}
}
