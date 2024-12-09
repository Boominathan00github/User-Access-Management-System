<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
<form action="SignUpServlet" method="post">
<label for="username">Username</label>
<input type="text" id="username" name="username" required><br>
<label for="password">Password</label>
<input type="password" id="password" name="password" required><br>
<label for="role">Role</label>
<select name="role" id="role"><option >
<option value="Employee">Employee</option>
<option value="Manager">Manager</option>
<option value="Admin">Admin</option>
</select><br>
<button type="submit">Sign Up</button>
</form>
</body>
</html>