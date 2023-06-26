<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<%@ page language="java" import="java.util.List" %>
		<%			
			List<String> errors = (List<String>) request.getAttribute("errors");
			if (errors != null)
				for (String error : errors) {
		%>
			<p style="color: red"><%= error %></p>
		<%
				}
		%>
		
		<form name="checkformname" method="POST" action="LoginController">
			Email: <input type="email" name="email" placeholder="Your email"><br>
			Password: <input type="password" name="password" placeholder="Your password"><br>
			<br>
			<input type="submit">
			<input type="reset">
		</form>	
	</body>
</html>