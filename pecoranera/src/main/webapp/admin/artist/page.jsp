<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dao.ArtistDao, model.Artist, java.util.List, java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Artists</title>
		<style type="text/css">
			table, th, td {
				border: 1px solid black; 
				border-collapse: collapse;
			}
			th, td {
				padding: 5px 10px;
			}
			
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
		List<Artist> artists = ArtistDao.doRetrieveAll();
	%>
	<a href="<%=request.getContextPath()%>/admin/artist/add.jsp">Aggiungi un Artista</a>
	<br><br>
	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Description</th>
		</tr>
		<% for (Artist artist : artists) { 
			request.setAttribute("artist", artist);
			%>
			<tr>
				<td> <%-- <%= artist.getId() %> --%> ${ artist.id } </td>
				<td><%= artist.getName() %></td>
				<td><%= artist.getDescription() %></td>
				<td>
					<a href="<%=request.getContextPath()%>/admin/artist/edit.jsp?id_artist=<%= artist.getId()%>">Modifica</a>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/admin//crud-artist/RemoveController?id_artist=<%= artist.getId()%>">Cancella</a>
				</td>
			<tr>
		<%} 
		%>
	</table>
</body>
</html>