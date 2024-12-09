<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create software</title>
</head>
<body bgcolor="lightblue">
	<h1>Create Software</h1>
	<form action="SoftwareServlet" method="post">
		<b><label for="name">Software Name</label></b> <input type="text"
			id="name" name="name" required><br> <b><label
			for="description">Description</label></b>
		<textarea id="description" name="description"></textarea>
		<br> <b><label for="accesslevel">AccessLevel</label></b><br>
		<input type="checkbox" name="accesslevel" value="Read">Read <input
			type="checkbox" name="accesslevel" value="Write">Write <input
			type="checkbox" name="accesslevel" value="Admin">Admin<br>
		<button type="submit">Create Software</button>
		
		<%
		String SuccessMessege = request.getParameter("success");

		if (SuccessMessege != null) {
		%>


		<%=SuccessMessege%>
		<%
		}
		%>
	</form>
<br>
	Go to--> <a href="requestAccess.jsp">requestAccess</a>
	<br><br>
	Go to--> <a href="pendingRequest.jsp">pendingRequest</a>
</body>
</html>