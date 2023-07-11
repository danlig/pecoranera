<%@ page contentType="text/html; charset=UTF-8" %>

<div id="show-navbar">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show-navbar.css">
    <script>var context = "${pageContext.request.contextPath}";</script>

    <div id="product-types-link">
        <a href="${pageContext.request.contextPath}/admin/event/list" class="category-link ${param.active_page == 'eventi'  ? 'active' : ''}">Eventi</a>
        <a href="${pageContext.request.contextPath}/admin/show/tag.jsp" class="category-link ${param.active_page == 'tag'  ? 'active' : ''}">Tag</a>
        <a href="${pageContext.request.contextPath}/admin/show/artisti.jsp" class="category-link ${param.active_page == 'artist'  ? 'active' : ''}">Artisti</a>
    </div>
</div>