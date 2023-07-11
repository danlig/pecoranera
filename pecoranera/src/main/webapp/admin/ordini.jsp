<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pecoranera Jazz Art Bistrot</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-orders.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordini.css">
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
        <script type="module" src="${pageContext.request.contextPath}/scripts/admin/orders.js"></script>
    </head>
<body>
    <jsp:include page="../assets/navbar.jsp">
        <jsp:param name="active_page" value="info" />
    </jsp:include>

    <section id="orders">
        <form id="filters" action="orders">
            <div>
                <label for="startDate">Data inizio: </label>
                <input type="date" name="startDate" id="startDate">
            </div>

            <div>
                <label for="startDate">Data fine: </label>
                <input type="date" name="endDate" id="endDate">
            </div>

            <div>
                <label for="idUser">ID utente: </label>
                <input type="number" name="idUser" id="idUser">
            </div>

            <input type="submit" value="Cerca">
        </form>

        <div>

        </div>

    </section>

    <jsp:include page="../assets/footer.jsp"></jsp:include>
    
</body>
</html>