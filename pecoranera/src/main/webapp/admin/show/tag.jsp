<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-tag.css">
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script type="module" src="${pageContext.request.contextPath}/scripts/admin/tag.js"></script>
</head>
<body>
    <jsp:include page="../../assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>

    <jsp:include page="../../assets/show-navbar.jsp">
        <jsp:param name="active_page" value="tag" />
    </jsp:include>

    <section id="tag">
        <table>
        <caption>Tabella tag</caption>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
            
            <tr>
                <form id="add-form" method="POST" action="${pageContext.request.contextPath}/admin/tag/add"></tr>
                    <td>
                        #
                    </td>
                    <td>
                        <input type="text" name="name" placeholder="Name of tag" form="add-form" required>
                    </td>
                    <td colspan="2">
                        <input type="submit" value="Crea">
                    </td>
            </tr>
        </table>
    </section>

    <jsp:include page="../../assets/footer.jsp"></jsp:include>
</body>
</html>