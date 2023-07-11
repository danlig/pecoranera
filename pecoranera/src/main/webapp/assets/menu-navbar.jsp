<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>

<div id="show-navbar">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/show-navbar.css">
    <script>var context = "${pageContext.request.contextPath}";</script>

    <div id="product-types-link">
        <a href="${pageContext.request.contextPath}/admin/product-type/list" class="category-link ${param.active_page == 'product_type'  ? 'active' : ''}">ProductType</a>
        <a href="${pageContext.request.contextPath}/admin/product/list" class="category-link ${param.active_page == 'product'  ? 'active' : ''}">Product</a>
    </div>
</div>