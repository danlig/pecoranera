<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADMIN PAGE</title>
</head>
<body>
	<H1>YOU ARE A ADMIN</H1>
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/admin/artist/list">CRUD Artisti</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/admin/tag/list">CRUD Tag</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/admin/product-type/list">CRUD Product Type</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/admin/event/list">CRUD Event</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/common/LogoutController">Esci</a>	
		</li>
	</ul>
</body>
</html>