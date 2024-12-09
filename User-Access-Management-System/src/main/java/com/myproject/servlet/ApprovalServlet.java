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
 * Servlet implementation class ApprovalServlet
 */
@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//requirement Attributes in Postgresql requestTable
	private String url = "jdbc:postgresql://localhost:5432/myProject";
	private String uname = "postgres";
	private String pword = "Admin";
	private String updateStatus = "UPDATE requests SET status=? WHERE user_id=? AND software_id=?";
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the action VALUES and SPILT store Sting array
		String[] action = request.getParameter("action").split(",");
		
		//ZERO Index hold the Status Check IF the Status Approve or Reject
		String status = action[0].equals("Approve") ? "Approve" : "Reject";
		
		//First & Second Index to get the User_id and Software_id
		int user_id =Integer.parseInt(action[1]);
		int software_id = Integer.parseInt(action[2]);
		
		//LOAD the Postgresql Driver Class
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		//handle the Exception and connect the Database
		try (Connection connection = DriverManager.getConnection(url, uname, pword);
				PreparedStatement preparedStatement = connection.prepareStatement(updateStatus)) {
		
			
			//role->(Manager) Update the Request Status in the requestTable
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, user_id);
			preparedStatement.setInt(3, software_id);
		
		int executeUpdate = preparedStatement.executeUpdate();
		
		//Close the Utility 
		preparedStatement.close();
		connection.close();
		
		if(executeUpdate == 1) {
			
			response.sendRedirect("pendingRequest.jsp?success=Request  "+status);
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		
	}

}
