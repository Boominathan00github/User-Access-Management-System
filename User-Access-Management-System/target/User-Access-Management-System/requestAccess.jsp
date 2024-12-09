<%@ page import="java.sql.*"  %>
    <%@ page import="java.util.ArrayList,java.util.List" %>
    <%@ page import="com.myproject.dao.Software" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Access</title>
<style type="text/css">
.color{
color: red;
}

</style>
</head>
<body bgcolor="pink">

<%! private String softwareListsql = "SELECT id,software_name FROM softwares";

private String url = "jdbc:postgresql://localhost:5432/myProject";
private String uname ="postgres";
private String pword = "Admin";

static List<Software> softwares = new ArrayList<Software>();

%>
<%

try{ Class.forName("org.postgresql.Driver");}catch(ClassNotFoundException e){System.err.println(e.getMessage());}
try(Connection connection = DriverManager.getConnection(url, uname, pword);
		ResultSet rs = connection.createStatement().executeQuery(softwareListsql);){

	
	while(rs.next()) {
		System.out.println("------>"+rs.getInt(1));
		System.out.println("------>"+rs.getString(2));
		softwares.add(new Software(rs.getInt(1),
				rs.getString(2),
				null,null));
	}
}catch(SQLException e){e.getMessage();
	}

 %>
 
<h1>Request Access</h1>

<form action="Requestservlet" method="post">
<label for="softwareId">Softwares</label>
<select id="softwareId" name="softwareid">
<option value="">---Select---</option> 

<c:forEach var="software" items="${softwares}">
<option value="${software.softWareId}">${software.softwareName}</option>
</c:forEach>

</select><br>
<br>
<label for="accesstype">AccessType</label>
<select name="accesstype">
<option value="read">Read</option>
<option value="write">Write</option>
<option value="admin">Admin</option>
</select><br>
<label for="reason">Reason for Request</label>
<textarea name="reason" required></textarea><br>

<button type="submit">Submit Request</button><br>
<% String Messege = request.getParameter("success"); %>
<%if(Messege != null) {%>
<div class="messege"></div>
<%=Messege %>
<%} %>
</form>

</body>
</html>