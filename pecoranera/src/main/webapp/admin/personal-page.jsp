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
			<a href="artist/page.jsp">CRUD Artisti</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/common/LogoutController">Esci</a>	
		</li>
	</ul>
</body>
</html>