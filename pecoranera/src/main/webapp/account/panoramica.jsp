<section id="panoramica" class="account-subpage">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page contentType="text/html; charset=UTF-8" %>

    <script type="module" src="${pageContext.request.contextPath}/scripts/panoramica.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/panoramica.css">
    <h2 id="page-header">Account <c:if test= "${sessionScope.isAdmin == true}">ADMIN</c:if>: <span><c:out value="${sessionScope.username}"/></span></h2>

    <div id="last-orders">
        <h3 class="subpage-header">Ultimi ordini</h3>

        <div>

        </div>
    </div>

    <div id="likes">
        <h3 class="subpage-header">I tuoi generi preferiti</h3>

        <div>

        </div>
    </div>
</section>