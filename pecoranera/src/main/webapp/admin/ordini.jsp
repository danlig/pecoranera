<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pecoranera Jazz Art Bistrot</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-orders.css">
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
        <script type="module" src="${pageContext.request.contextPath}/scripts/admin/orders.js"></script>
    </head>
<body>
    <section id="orders">
        <form id="filters" action="orders">
            <input type="date" name="startDate" id="startDate">
            <input type="date" name="endDate" id="endDate">

            <input type="text" name="idUser" id="idUser">

            <input type="submit" value="Cerca">
        </form>

        

    </section>
    
</body>
</html>