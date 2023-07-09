<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

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


    <section id="cart-list">
        
    </section>

    <section id="confirm-cart">
        <div id="price-total">
            Totale: €<span>0.00</span>
        </div>

        <c:choose>
         
         <c:when test= "${sessionScope.isAdmin == null || empty sessionScope.isAdmin}">
            <div id="call-to-log">Registrati o Accedi per procedere agli acquisti</div>
         </c:when>
         
         <c:otherwise>
            <div id="cart-buttons">
                <button id="empty-cart">Svuota Carrello</button>

                <button id="buy-button">Acquista</button>
            </div>
         </c:otherwise>

      </c:choose>

    </section>

    <div id="shopping-acknowledgement" style="display: none;">
        <div>
            <i class="fa-solid fa-cart-shopping fa-beat"></i>
            <span>Acquisto avvenuto con successo!</span>
        </div>
    </div>


    <jsp:include page="./assets/footer.jsp"></jsp:include>
    
</body>
</html>