<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Event-${id_event} Artists</title>
		<style type="text/css">
			table, th, td {
				border: 1px solid black; 
				border-collapse: collapse;
			}
			th, td {
				padding: 5px 10px;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
				<th>Artist</th>
				<th>Role</th>
			</tr>
			<tr>
				<form method="POST" action="${pageContext.request.contextPath}/admin/event-artist/add?event=${id_event}">
					<td>
						<select name="artist">
							<c:forEach var="artist" items="${artists}">
								<option value="${artist.id}">${artist.name}</option>
							</c:forEach>
						</select>
					</td>
					
					<td>
						<input type="text" name="role" placeholder="Insert Role">
					</td>
					
					<td>
						<input type="submit" value="Collega">
					</td>
				</form>
			</tr>
			
			<c:forEach var="eventArtist" items="${eventArtists}">
				<tr>
					<td>
						<c:out value="${eventArtist.artist.name }"></c:out>
					</td>
					<td>
						<c:out value="${eventArtist.role}"></c:out>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/event-artist/delete?id_event=${id_event}&id_artist=${eventArtist.artist.id}">Elimina</a>
					</td>
				</tr>
			</c:forEach>
			
			
		</table>
	</body>
</html>