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
        <jsp:param name="active_page" value="product_type" />
    </jsp:include>

    <section id="product">
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
		
	</section>
</body>	
</html>