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
        <jsp:param name="active_page" value="menu" />
    </jsp:include>

    <jsp:include page="../../assets/menu-navbar.jsp">
        <jsp:param name="active_page" value="product" />
    </jsp:include>

    <section id="product">
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
		</section>
	</body>
</html>