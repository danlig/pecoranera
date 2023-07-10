<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>


<c:if test="${sessionScope.isAdmin == null || empty sessionScope.isAdmin}">
       <c:redirect url="./index.jsp"/>
</c:if>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/scripts/account.js"></script>
    <script>var user ="${sessionScope.user}";</script>
</head>
<body>
    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="account" />
    </jsp:include>

    <section id="profile-section">
        <jsp:include page="./assets/profile-nav.jsp">
            <jsp:param name="profile_page" value="panoramica" />
        </jsp:include>

        <div id="display-data">

        </div>
    </section>

    <jsp:include page="./assets/footer.jsp"></jsp:include>
</body>
</html>