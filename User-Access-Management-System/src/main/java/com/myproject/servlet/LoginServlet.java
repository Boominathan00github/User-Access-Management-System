package com.myproject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//requirement Attributes in Postgresql in User_Table
	private String url = "jdbc:postgresql://localhost:5432/myProject";
	private String uname = "postgres";
	private String pword = "Admin";
	private String usersql = "SELECT id,username,password,role FROM users WHERE username=? AND password=?";
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//get username & password in login.jsp
		String username = request.getParameter("username");
		String password = request.getParameter("password");


		//check if user enter credentials is not valid -> response goto the login.jsp Otherwise Fail the Condition
		if (username.equals("") || username == null || password == null || password.equals("")) {


			response.sendRedirect("login.jsp?error=Invalid credentials");

		}
		
		
		//LOAD the Postgresql Driver Class 
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		//Connect to DataBase Get the particular User information with help of sql
		try (Connection connection = DriverManager.getConnection(url, uname, pword);
				PreparedStatement preparedStatement = connection.prepareStatement(usersql)) {

			//get the user name,password 
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet usersql = preparedStatement.executeQuery();
			

			//validates credentials against the User table -.the validation true request goto next step Otherwise
			//goto Else -> request goto login.jsp
		 if (usersql.next()) {

				String role = usersql.getString("role");
				int userid = usersql.getInt("id");
				

				//Setting User Data into the Session
				//Manage the User Session
				HttpSession session = request.getSession();
				session.setAttribute("role", role);
				session.setAttribute("username", username);
			
				//User Role -> Employee the User Redirect to requestAccess.jsp 
				if (role.equals("Employee")) {
					
					//if role->Employee userid Attribute need the RequestServlet
					session.setAttribute("userid", userid);
					response.sendRedirect("requestAccess.jsp");
					
					
				//User Role -. Manager the User Redirect to pendingRequest.jsp
				} else if (role.equals("Manager")) {
					
					response.sendRedirect("pendingRequest.jsp");
		

				//User Role -> Admin the User Redirect to createSoftware and full Access the Application 
				} else if (role.equals("Admin")) {

					response.sendRedirect("createSoftware.jsp");

					//Credentials is false the User Redirect login.jsp with message ->error=Invalid credentials
				} else {

					response.sendRedirect("login.jsp?error=Invalid credentials");

				}
				
			}else {
				
				response.sendRedirect("login.jsp?error=Invalid credentials");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
