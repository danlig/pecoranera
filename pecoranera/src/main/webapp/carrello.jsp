<!DOCTYPE html>
<html lang="it">
<head>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pecoranera Jazz Art Bistrot</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrello.css">
        <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
        <script type="module" src="${pageContext.request.contextPath}/scripts/carrello.js"></script>
    </head>
</head>
<body>

    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="carrello" />
    </jsp:include>

    <c:if test="${sessionScope.isAdmin == null || empty sessionScope.isAdmin}">
       <c:redirect url="./index.jsp"/>
    </c:if>

    <section id="cart-list">
        <div class="event">
            <img src="" alt="">

            <div class="event-details">
                <h3 class="event-header"></h3>
                <h4 class="event-date"></h4>

                <div class="tag-list">

                </div>
            </div>

        </div>
    </section>

    <section id="confirm-cart">
        <div id="price-total">
            Totale provvisorio: € <span></span>
        </div>
    </section>


    <jsp:include page="./assets/footer.jsp"></jsp:include>
    
</body>
</html>