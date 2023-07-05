<section id="panoramica" class="account-subpage">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <h2 class="page-header"><c:out value="${sessionScope.username}"/></h2>

    <div>
        <h3>I tuoi ordini</h3>

        <div class="order-preview"></div>

        <div class="order-preview"></div>
    </div>

    <div>
        <h3>I tuoi generi preferiti</h3>

    </div>
</section>