<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-eventi.css">
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="../../assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>

    <jsp:include page="../../assets/show-navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>

    <section id="eventi">
        <table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Date</th>
				<th>Max Tickets</th>
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
                        <textarea name="description" placeholder="Insert Description" rows="4"></textarea>
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
						<input type="file" name="photo">
					</td>
					
					<td></td>
					
					<td>
						<select name="tags" multiple>
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
					<form method="POST" action="${pageContext.request.contextPath}/admin/event/edit?id=${event.id}" enctype="multipart/form-data">
						<td>
							<c:out value="${event.id}" />
						</td>
						
						<td>
							<input type="text" name="name" value="${event.name}">
						</td>
						
						<td>
                            <textarea name="description" placeholder="Insert Description" rows="4" value="${event.description}"></textarea>
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
							<input type="file" name="photo">
						</td>
						
						<td>
							<a href="${pageContext.request.contextPath}/admin/event-artist/list?id_event=${event.id}">artisti</a>
						</td>	
						
						<td>
							<select name="tags" multiple>
								<c:forEach var="tag" items="${tags}">
									<option value="${tag.id}">${tag.name}</option>
								</c:forEach>
							</select>
						</td>

						<td>
							<input type="submit" value="Modifica">
						</td>
						
						<td>
							<a class="delete" href="${pageContext.request.contextPath}/admin/event/delete?id=${event.id}">Elimina</a>
						</td>
					</form>
				</tr>
			</c:forEach>
		</table>
    </section>

    <jsp:include page="../../assets/footer.jsp"></jsp:include>
</body>
</html>