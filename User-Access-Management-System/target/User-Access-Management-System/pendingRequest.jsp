<%@ page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.myproject.dao.Software,com.myproject.dao.Users,com.myproject.dao.Requests" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%! private String requestSql = "SELECT user_id,software_id,access_type,reason FROM request"; 
private String userSql = "SELECT id,username FROM users";
private String softwareSql = "SELECT id,software_name FROM software";

private String url = "jdbc:postgresql://localhost:5432/myProject";
private String uname ="postgres";
private String pword = "Admin"; 

List<Users> users = new ArrayList<Users>();
List<Software> softwares = new ArrayList<Software>();
List<Requests> requests = new ArrayList<Requests>();

%>

<%
try{ Class.forName("org.postgresql.Driver");}catch(ClassNotFoundException e){
	
	System.err.print("Cannot find PostgreSQL JDBC driver : " + e.getMessage());
	
}
try(Connection connection = DriverManager.getConnection(url,uname,pword);
		ResultSet requestsRs = connection.createStatement().executeQuery(requestSql);
		ResultSet usersRs = connection.createStatement().executeQuery(userSql);
		ResultSet softwaresRs = connection.createStatement().executeQuery(softwareSql)){
	
	while(requestsRs.next()){
		
		requests.add(new Requests(requestsRs.getInt(1), 
				requestsRs.getInt(2), 
				requestsRs.getString(3), requestsRs.getString(4), null));
		
	}
	
	while( usersRs.next()){
		
		users.add(new Users(usersRs.getInt(1), 
				usersRs.getString(2), null, null));
		
	}
	
	while(softwaresRs.next()){
		
		softwares.add(new Software(softwaresRs.getInt(1), 
				softwaresRs.getString(2),
				null, null));
	}
}catch(SQLException e){
	
	System.out.print("-------> SqlException Occers"+ e);
	
}

%>

	<table border="">
		<tr>
		<th>Employee_Name</th>
		<th>Software_Name</th>
		<th>Access_Type</th>
		<th>Reason for Request</th>
		</tr>
	
		<c:forEach var="request" items="${requests}">
		
		
		<c:forEach var="user" items="${users}">
		<c:if test="${request.userId == user.userId}">
		<tr align="center">
		<td>${user.userName}</td>
		</c:if></c:forEach>
		
		<c:forEach var="software" items="${softwares}">
		<c:if test="${request.softwareId == software.softWareId}">
		<td>${software.softwareName}</td>
		</c:if>
		</c:forEach>
	
		
		<td>${request.accessType}</td>
		<td>${request.reason}</td>
		
	<td><form action="ApprovalServlet" method="post">
	
	<button type="submit"  name="action" value="Approve,${request.userId},${request.softwareId}">Approve</button>
	<button type="submit" name="action" value="Reject,${request.userId},${request.softwareId}">Reject</button>
	
	
	</form>
	</td>
	
		</c:forEach>
			
		</table>
					<%
					String Message = request.getParameter("success");

					if (Message != null) {
					%>

					<div class="Messege"></div>

					<%=Message%>

					<%}%><br>
				
	
</body>
</html>