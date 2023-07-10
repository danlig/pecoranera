<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product</title>
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
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th>Tipo di Prodotto</th>
			</tr>
			
			<tr>
				<form method="POST" action="${pageContext.request.contextPath}/admin/product/add">
					<td>
						#
					</td>
					
					<td>
						<input type="text" name="name" placeholder="Name of Product">
					</td>
					
					<td>
						<input type="text" name="description" placeholder="Name of Product">
					</td>
					
					<td>
						<input type="text" name="price" placeholder="Name of Product">	
					</td>
					
					<td>
						<select name="type">
							<c:forEach var="product_type" items="${product_types}">
								<option value="${product_type.id}">
									<c:out value="${product_type.name}"/>
								</option>						
							</c:forEach>
						</select>
					</td>
					
					<td colspan="2">
					 	<input type="submit" value="Crea">
					</td>
				</form>
			</tr>
			
			<c:forEach var="product" items="${products}">
				<tr>
					
					<form method="POST" action="${pageContext.request.contextPath}/admin/product/edit?id=${product.id}">
						<td>
							<c:out value="${product.id}"/>
						</td>
						
						<td>
							<input type="text" name="name" value="${product.name}">
						</td>
						
						<td>
							<input type="text" name="description" value="${product.description}">
						</td>
						
						<td>
							<input type="text" name="price" value="${product.price}">	
						</td>
						
						<td>
							<select name="type">
								<c:forEach var="product_type" items="${product_types}">
									<c:if test="${product_type.name == product.type.name}">
										<option value="${product_type.id}" selected>
											<c:out value="${product_type.name}"/>
										</option>										
									</c:if>
									<option value="${product_type.id}">
										<c:out value="${product_type.name}"/>
									</option>						
								</c:forEach>
							</select>
						</td>
						
						<td>
						 	<input type="submit" value="Modifica">
						</td>
										
						<td>
							<a href="${pageContext.request.contextPath}/admin/product/delete?id=${product.id}">Cancella</a>
						</td>
						
					</form>
				</tr>
			</c:forEach>		
		</table>
	</body>
</html>