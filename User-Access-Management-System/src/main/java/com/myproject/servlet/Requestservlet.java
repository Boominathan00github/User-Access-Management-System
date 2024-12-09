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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Requestservlet
 */
@WebServlet("/Requestservlet")
public class Requestservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//requirement Attributes in Postgresql requestTable
	private String url = "jdbc:postgresql://localhost:5432/myProject";
	private String uname = "postgres";
	private String pword = "Admin";
	private String insertRequestTable = "INSERT INTO requests (user_id,software_id,access_type,reason,status) VALUES (?,?,?,?,?)";
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getting RequestTable information in requestAccess.jsp page
		int softwareid =Integer.parseInt(request.getParameter("softwareid"));
		String accessType = request.getParameter("accesstype");
		String reason = request.getParameter("reason");
		
		//get Session data if role->'Employee' (Setting if(role->Employee) in LoginServlet)
		HttpSession session = request.getSession();
		int userid = (int)session.getAttribute("userid");

	
		//Load Postgresql Drier Class
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		//Handle the SQLException
		//Store the Request in the RequestsTable with a status Pending->(default)
		try (Connection connection = DriverManager.getConnection(url, uname, pword);
				PreparedStatement preparedStatement = connection.prepareStatement(insertRequestTable)) {
		
			//Setting up Values corresponding col name
			preparedStatement.setInt(1, userid);
			preparedStatement.setInt(2, softwareid);
			preparedStatement.setString(3, accessType);
			preparedStatement.setString(4, reason);
			preparedStatement.setString(5, "pending");
			
			//get Storing Result
			int executeUpdate = preparedStatement.executeUpdate();
			
			//Close the Utility
			preparedStatement.close();
			connection.close();
			
			if(executeUpdate > 0) {
				
				response.sendRedirect("requestAccess.jsp?success=request successfully Submitted");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("requestAccess.jsp?success=Request not submitted");
			e.printStackTrace();
		}
	}

}
