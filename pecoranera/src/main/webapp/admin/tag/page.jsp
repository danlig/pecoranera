<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Tag"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tags</title>
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
		<p class="${ message.key}-color">${message.value}</p>
	</c:forEach>

	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
		</tr>
		
		<tr>
			<form method="POST" action="${pageContext.request.contextPath}/admin/tag/add">
				<td>
					#
				</td>
				<td>
					<input type="text" name="name" placeholder="Name of tag">
				</td>
				<td colspan="2" style="text-align: center">
					<input type="submit" value="Crea">
				</td>
			</form>
		</tr>
		
		<c:forEach var="tag" items="${tags}">
			<tr>
				<form method="POST" action="${pageContext.request.contextPath}/admin/tag/edit?id_tag=${tag.id}">
					<td> 
						<c:out value="${ tag.id }"/>
					</td>
					<td>
						<input type="text" name="name" value="${tag.name}">
					</td>	
					<td>
						<input type="submit" value="Modifica">
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/tag/delete?id_tag=${tag.id}">Cancella</a>
					</td>
				</form>
			<tr>
		</c:forEach>
	</table>
</body>
</html>