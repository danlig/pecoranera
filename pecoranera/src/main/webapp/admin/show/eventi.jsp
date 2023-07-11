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
    <script type="module" src="${pageContext.request.contextPath}/scripts/admin/eventi.js"></script>
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
				
				<form id="add-form" method="POST" action="${pageContext.request.contextPath}/admin/event/add" enctype="multipart/form-data">
					<td>
						#
					</td>
					
					<td>
						<input type="text" name="name" placeholder="Insert Name"  form="add-form" required>
					</td>
					
					<td>
                        <textarea name="description" placeholder="Insert Description" rows="4" form="add-form" required></textarea>
					</td>
					
					<td>
						<input type="number" step="0.1" name="price" placeholder="Insert Price" form="add-form" required>
					</td>
					
					<td>
						<input type="date" name="date" form="add-form" required>
						<input type="hidden" name="cancellation" form="add-form" value="2024-02-02">
					</td>
					
					
					<td>
						<input type="number" name="maxTickets" form="add-form" placeholder="Insert Max Tickets" required>
					</td>
					
					<td>
						<input type="file" name="photo" form="add-form" required>
					</td>
					
					<td></td>
					
					<td>
						<select form="add-form" name="tags" multiple>
                            //FOREACH
								<option value="${tag.id}">${tag.name}</option>
						</select>
					</td>
					
					<td colspan="2">
						<input form="add-form" type="submit" value="Crea">
					</td>
				</form>
				
			</tr>
			
		</table>
    </section>

    <jsp:include page="../../assets/footer.jsp"></jsp:include>
</body>
</html>