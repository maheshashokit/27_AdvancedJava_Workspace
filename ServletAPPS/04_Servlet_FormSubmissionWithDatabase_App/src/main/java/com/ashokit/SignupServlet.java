package com.ashokit;

import java.io.IOException;
import java.io.PrintWriter;

import com.ashokit.dao.SignUpDao;
import com.ashokit.dto.SignUp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.Informing to browser for sending HTML content
			response.setContentType("text/html");

			// 2.Getting Writer Object for Preparing Response
			PrintWriter pw = response.getWriter();

			// creating the ServletConfig object
			ServletConfig config = getServletConfig();
			// Gathering the init Parameters configured in web.xml
			System.out.println("Jdbc Driver Class::::" + config.getInitParameter("JdbcDriverClass"));
			System.out.println("Jdbc URL::::" + config.getInitParameter("JdbcUrl"));
			System.out.println("Jdbc Username::::" + config.getInitParameter("username"));
			System.out.println("Jdbc Password::::" + config.getInitParameter("password"));

			// creating the ServletContext Object
			ServletContext context = getServletContext();
			// Gathering the context Parameters configured in web.xml
			System.out.println("Application Name::::" + context.getInitParameter("ApplicationName"));
			System.out.println("Address::::" + context.getInitParameter("Address"));

			// 3.Reading the form data by using request object
			String name = request.getParameter("firstName");
			String emailId = request.getParameter("emailId");
			String contactNo = request.getParameter("contactNo");
			String course = request.getParameter("course");

			int c = 50/0;
			
			// 4.creating Signup Object to hold the form data
			SignUp signup = new SignUp(name, emailId, contactNo, course);

			// 5.Creating the Object for SignupDao class
			SignUpDao signUpDao = new SignUpDao();

			// 6.Passing signup object into SigupDao Class method
			boolean createdStatus = signUpDao.createBrandNewSignUp(signup);

			// 7.checking the status
			if (createdStatus) {
				pw.println("<div style='text-align:center;'>Enquiry Sent To AshokIT Admin Team will Contact You Soon.....</div>");
				
				// including another servlet response
				RequestDispatcher rd = request.getRequestDispatcher("displayEnquires");
				rd.include(request, response);
			}
			// creating HomePage Hyperlink to navigate signup.html
			pw.println("<div style='text-align:center;'><a href='signup.html'> | Home Page| </a></div>");

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("errorPage.html");
			rd.forward(request, response);
		}
	}
}