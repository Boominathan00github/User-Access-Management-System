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
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//requirement NewUser Attributes in Postgresql userTable
	private String url = "jdbc:postgresql://localhost:5432/myProject";
	private String uname ="postgres";
	private String pword = "Admin";
	private String sql = "INSERT INTO users (username,password,role) VALUES (?,?,?)";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting Username,password,role from the Signup.jsp 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
	
		//Load the Postgresql Driver Class
		try {
			Class.forName("org.postgresql.Driver");
			
			//Connect the Database and set User Enter Fields in user_table
			try( Connection con = DriverManager.getConnection(url, uname, pword);
				PreparedStatement PreparedStatement = con.prepareStatement(sql);) {
				
				PreparedStatement.setString(1, username);
				PreparedStatement.setString(2, password);
				PreparedStatement.setString(3, role);
				 
				int executeUpdate = PreparedStatement.executeUpdate();
				
          System.out.println("-------> Sucessfully Update " +executeUpdate);
   
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("login.jsp");
	}

}
