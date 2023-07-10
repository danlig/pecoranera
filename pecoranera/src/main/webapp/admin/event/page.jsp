<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Event</title>
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
		<c:forEach var="message" items="${messages}">
			<p class="${message.key}">${message.value}</p>
		</c:forEach>
		
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Date</th>
				<th>Max Tickets</th>
				<th>Available Tickets</th>
				<th>Photo</th>
				<th>Artist</th>
				<th>Tag</th>
			</tr>
			
			<tr>
				
				<form method="POST" action="${pageContext.request.contextPath}/admin/event/add" enctype="multipart/form-data">
					<td>
						#
					</td>
					
					<td>
						<input type="text" name="name" placeholder="Insert Name">
					</td>
					
					<td>
						<input type="text" name="description" placeholder="Insert Description">
					</td>
					
					<td>
						<input type="text" name="price" placeholder="Insert Price">
					</td>
					
					<td>
						<input type="date" name="date">
						<input type="hidden" name="cancellation" value="2024-02-02">
					</td>
					
					
					<td>
						<input type="number" name="maxTickets" placeholder="Insert Max Tickets">
					</td>
					
					
					<td>
						<input type="number" name="availableTickets">
					</td>
					
					<td>
						<input type="file" name="photo">
					</td>
					
					<td></td>
					
					<td>
						<select style="height: 30px" name="tags" multiple>
							<c:forEach var="tag" items="${tags}">
								<option value="${tag.id}">${tag.name}</option>
							</c:forEach>
						</select>
					</td>
					
					<td colspan="2">
						<input type="submit" value="Crea">
					</td>
				</form>
				
			</tr>
			
			<c:forEach var="event" items="${events}">
				<tr>
					<form method="POST" action="${pageContext.request.contextPath}/admin/event/edit?id_event=${event.id}" enctype="multipart/form-data">
						<td>
							<c:out value="${event.id}" />
						</td>
						
						<td>
							<input type="text" name="name" value="${event.name}">
						</td>
						
						<td>
							<input type="text" name="description" value="${event.description}">
						</td>
						
						<td>
							<input type="text" name="price" value="${event.price}">
						</td>
						
						<td>
							<input type="date" name="date" value="${event.date}">
						</td>
						
						<td>
							<input type="number" name="maxTickets" value="${event.maxTickets}">
						</td>
						
						<td>
							<input type="number" name="availableTickets" value="${event.availableTickets}">
						</td>
						
						<td>
							<input type="file" name="photo">
						</td>
						
						<td>
							<a href="${pageContext.request.contextPath}/admin/event-artist/list?id_event=${event.id}">artisti</a>
						</td>	
						
						<td>
							<select style="height: 30px" name="tags" multiple>
								<c:forEach var="tag" items="${tags}">
									<option value="${tag.id}">${tag.name}</option>
								</c:forEach>
							</select>
						</td>

						<td>
							<input type="submit" value="Modifica">
						</td>
						
						<td>
							<a href="${pageContext.request.contextPath}/admin/event/delete?id_event=${event.id}">Elimina</a>
						</td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>