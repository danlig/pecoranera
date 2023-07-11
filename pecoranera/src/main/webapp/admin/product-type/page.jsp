<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
	<head>	
		<meta charset="UTF-8">
		<title>Product type</title>
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
		<caption>.</caption>
		
		<tr>
			<th>Id</th>
			<th>Nome</th>
		</tr>

		<tr>
			<form method="POST" action="${pageContext.request.contextPath}/admin/product-type/add">
				<td>
					#
				</td>
				<td>
					<input type="text" name="name" placeholder="Name of product type">
				</td>
				<td colspan="2" style="text-align: center">
					<input type="submit" value="Crea">
				</td>
			</form>
		</tr>

		<c:forEach var="product_type" items="${product_types}">
			<tr>
				<form method="POST" action="${pageContext.request.contextPath}/admin/product-type/edit?id=${product_type.id}">
					<td> 
						<c:out value="${ product_type.id }"/>
					</td>
					<td>
						<input type="text" name="name" value="${product_type.name}">
					</td>	
					<td>
						<input type="submit" value="Modifica">
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/product-type/delete?id=${product_type.id}">Cancella</a>
					</td>
				</form>
			<tr>
		</c:forEach>
	</table>
	</body>	
</html>