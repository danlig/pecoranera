<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Register</title>
	</head>
	<body>
		<%
		String email = (String)request.getAttribute("email");
		if(email == null) email = "";
		
		String password = (String)request.getAttribute("password");
		if(password == null) password = "";
		
		
		String error = (String)request.getAttribute("error");
		if(error != null) {
		%>
		<div class="error"><%=error %></div>
		<%
		}
		
		String message = (String)request.getAttribute("message");
		if(message != null) {
		%>s
		<div class="message"><%=message %></div>
		<%
		  }
		%>
		
		<form name="checkformname" method="POST" action="RegisterController">
			Email: <input type="email" name="name" placeholder="Your name" value="<%=email %>"><br>
			Password: <input type="text" name="password" placeholder="Your surname" value="<%=password %>"><br>
			<br>
			<input type="submit">
			<input type="reset">
		</form>	
	</body>
</html>