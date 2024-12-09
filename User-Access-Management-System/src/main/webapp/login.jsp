<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body bgcolor="pink">
<h1>Login</h1>
<form action="LoginServlet" method="post"> 
<label for="username">Username</label>
<input type="text" id="username" name="username" required><br>
<label for="password">Password</label>
<input type="password" id="password" name="password"  required><br>
<button type="submit">Login</button>

<% String errorMessage = request.getParameter("error");

if(errorMessage != null){%>
	
	<div class="error-Messege"></div>
	
	<%=errorMessage %>
	
<%}%><br>


</form><br>
<form action="signup.jsp" method="get">
New User
<button type="submit">signup</button>
</form>
</body>
</html>