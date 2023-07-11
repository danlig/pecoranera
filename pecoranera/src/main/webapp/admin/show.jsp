<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show.css">
</head>
<body>
    <jsp:include page="../assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>

    <jsp:include page="../assets/show-navbar.jsp"></jsp:include>

    <div id="spacer"></div>

    <jsp:include page="../assets/footer.jsp"></jsp:include>
</body>
</html>