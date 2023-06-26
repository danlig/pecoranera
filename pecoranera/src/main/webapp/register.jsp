<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dao.TagDao, model.Tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Register</title>
	</head>
	<body>
		<%@ page language="java" import="java.util.List" %>
		<%
			String message = (String)request.getAttribute("message");
			if(message != null) {
		%>
			<div style="color: green"><%= message %></div>
		<%
		  	}
			
			List<String> errors = (List<String>) request.getAttribute("errors");
			if (errors != null)
				for (String error : errors) {
		%>
			<p style="color: red"><%= error %></p>
		<%
				}
		%>
		
		<form name="checkformname" method="POST" action="RegisterController">
			Username: <input type="text" name="username" placeholder="Your name"><br>
			Email: <input type="email" name="email" placeholder="Your name"><br>
			Password: <input type="password" name="password" placeholder="Your surname"><br>
			Confirm Password: <input type="password" name="conf_password" placeholder="Your surname"><br>
			
			Tags:<br>
			<%
				List<Tag> tags = TagDao.doRetrieveAll();
				
				for (Tag tag : tags) {
			%>
				<label for = "<%= tag.getId() %>"><%= tag.getName() %></label>
				<input id = "<%= tag.getId() %>-tag" type="checkbox" name="<%= tag.getId() %>-tag" value="true">
				
			<%} %>
			<br>
			<input type="submit">
			<input type="reset">
		</form>	
	</body>
</html>