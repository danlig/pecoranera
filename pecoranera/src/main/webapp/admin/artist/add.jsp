<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add Artist</title>
		<style>
			.successfully-color {
				color: green;
			}
			
			.error-color {
				color: red;
			}
		</style>
	</head>
	<body>
		<%
			Map<String, String> messages = (Map<String, String>) request.getAttribute("messages");
			if (messages != null)
				for (Map.Entry<String, String> message : messages.entrySet()) {
		%>
			<p class="<%= message.getKey() %>-color"><%= message.getValue() %></p>
		<%
			}
		%>
		<form method="POST" action="<%=request.getContextPath()%>/admin/artist/AddController">
			Name: <input type="text" name="name" placeholder="name"><br>
			Description: <input type="text" name="description" placeholder="description"><br>
			<input type="submit">
			<input type="reset">
		</form>	
	</body>
</html>