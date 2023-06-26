<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dao.ArtistDao, model.Artist, java.util.Map"%>
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
			
			int id_artist = Integer.parseInt(request.getParameter("id_artist"));
			Artist artist = ArtistDao.doRetrieveByKey(id_artist);
		%>
		<form method="POST" action="<%=request.getContextPath()%>/admin//crud-artist/EditController?id_artist=<%= id_artist %>">
			Name: <input type="text" name="name" placeholder="<%= artist.getName() %>"><br>
			Description: <input type="text" name="description" placeholder="<%= artist.getDescription() %>"><br>
			<input type="submit">
			<input type="reset">
		</form>	
	</body>
</html>