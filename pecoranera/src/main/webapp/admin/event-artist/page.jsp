<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-eventi.css">
</head>
<body>
    <jsp:include page="../../assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>

    <jsp:include page="../../assets/show-navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>
    
    
    <section id="event-artist">
		<table>
				<tr>
					<th>Artist</th>
					<th>Role</th>
				</tr>
				<tr>
					<form method="POST" action="${pageContext.request.contextPath}/admin/event-artist/add?event=${event.id}">
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
							<a href="${pageContext.request.contextPath}/admin/event-artist/delete?event=${event.id}&artist=${eventArtist.artist.id}">Elimina</a>
						</td>
					</tr>
				</c:forEach>
				
				
			</table>
		</section>
		
    	<jsp:include page="../../assets/footer.jsp"></jsp:include>
	</body>
</html>