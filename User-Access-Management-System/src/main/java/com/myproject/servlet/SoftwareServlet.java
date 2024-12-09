package com.myproject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SoftwareServlet
 */
@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//requirement Attributes in Postgresql in Software_Table
	private String url = "jdbc:postgresql://localhost:5432/myProject";
	private String uname = "postgres";
	private String pword = "Admin";
	private String sql = "INSERT INTO softwares (software_name,description,access_level) VALUES (?,?,?)";
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting about the NewSoftware and software Details in createSoftware.jsp
		String softwarName = request.getParameter("name");
		String description = request.getParameter("description");
		String accessLevels = String.join(",",request.getParameterValues("accesslevel"));
		
//		System.out.println("--------->"+softwarName);
//		System.out.println("--------->"+discreption);
//		System.out.println("--------->"+accessLevels);
//		
		//LOAD the Postgresql Driver Class  
		try {
			
			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		//Connect the Database and store Software Details into software_tabe
		try (Connection connection = DriverManager.getConnection(url, uname, pword);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		
		
		preparedStatement.setString(1, softwarName);
		preparedStatement.setString(2, description);
		preparedStatement.setString(3, accessLevels);
		
		int executeUpdate = preparedStatement.executeUpdate();
		
		//close the Connection 
		preparedStatement.close();
		connection.close();
		
		if(executeUpdate == 1) {
			
			response.sendRedirect("createSoftware.jsp?success=Software create Successfully !");
			
		}else {
			
			response.sendRedirect("createSoftware.jsp?success=Error Occur while Create software !");
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
