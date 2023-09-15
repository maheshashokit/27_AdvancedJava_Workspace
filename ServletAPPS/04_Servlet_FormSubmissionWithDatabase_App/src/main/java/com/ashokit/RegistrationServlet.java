package com.ashokit;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	}

}
