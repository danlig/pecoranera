<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dao.ArtistDao, model.Artist, java.util.List, java.util.Map"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
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
	<%--<%
		Map<String, String> messages = (Map<String, String>) request.getAttribute("messages");
		if (messages != null)
			for (Map.Entry<String, String> message : messages.entrySet()) {
	
		<p class="<%= message.getKey() %>-color"><%= message.getValue() %></p>
	<%
		}
	%> --%>
	<table>
	<caption>Tabella di inserimento artista</caption>
	
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Description</th>
		</tr>
		<tr>
			<form method="post" action="${pageContext.request.contextPath}/admin/artist/add">
				<td>#</td>
				<td>
					<input name="name" type="text" placeholder="Insert Name">
				</td>
				<td>
					<input name="description" type="text" placeholder="Insert Description">
				</td>
				<td colspan="2">
					<input type="submit"value="Crea">
				</td>
			</form>
		</tr>
		<c:forEach var="artist" items="${artists}">
			<tr>
				<form method="POST" action="${pageContext.request.contextPath}/admin/artist/edit?id=${artist.id}">
					<td>
						<c:out value="${artist.id}"/>
					</td>
					<td>
						<input name="name" type="text" value="${artist.name }">
					</td>
					<td>
						<input name="description" type="text" value="${artist.description }">
					</td>
					<td>
						<input type="submit" value="Modifica">
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/artist/delete?id=${artist.id}">Cancella</a>
					</td>
				</form>
			</tr>
		</c:forEach>
	</table>
</body>
</html>