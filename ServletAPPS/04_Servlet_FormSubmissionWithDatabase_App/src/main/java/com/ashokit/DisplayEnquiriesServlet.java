package com.ashokit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ashokit.dao.SignUpDao;
import com.ashokit.dto.SignUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DisplayEnquiriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		SignUpDao signUpDao = new SignUpDao();
		List<SignUp> allSignUps = signUpDao.getAllSignUps();
		pw.println("<table align='center' border='2'>");
		pw.println("<thead>");
		pw.println("<tr><th>Name</th><th>EmailID</th><th>ContactNo</th><th>CourseName</th></tr>");
		pw.println("</thead>");
		allSignUps.forEach(eachSignUp -> {
		 pw.println("<tr><td>"+eachSignUp.getName()+"</td><td>"+eachSignUp.getEmailId()+"</td><td>"+eachSignUp.getContactNo()+"</td><td>"+eachSignUp.getCourseName()+"</td></tr>");
		});
		pw.println("</table>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
