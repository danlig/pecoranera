<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/event_details.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script type="module" src="${pageContext.request.contextPath}/scripts/event_details.js"></script>
</head>
<body>
    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>

    <section id='<%= request.getParameter("id")%>' class="event">   
        <div id="mobile-title">
            <h2 class="event-header"></h2>
            <h3 class="event-date"></h3>
        </div>
        
        <div>
            <div id="left-div" class="columns">
                <img src="" alt="">

                <div id="artist-list">

                </div> 
            </div>

            <div id="center-div" class="columns">
                <h2 class="event-header desktop"></h2>
                <h3 class="event-date desktop"></h3>

                <div id="tag-list"></div>

                <div>
                    <p id="event-description"> </p>
                </div>                  
            </div>  

            <div id="right-div" class="columns">
                <div id="ticket-info">
                    <p id="ticket-price"></p>

                    <span style="display:none;" class="out-of-tickets">BIGLIETTI TERMINATI</span>
                    
                    <div id="ticket-availability">  
                        <label for="ticket-number">Quantità: </label>
                        <select name="ticket-number" id="ticket-number">

                        </select>
                    </div>
                </div>

                <p id="available-tickets">Biglietti disponibili: <span></span></p>

                <button id="add-to-cart" type="submit">Aggiungi al carrello</button>
            </div>

        </div>
        
    </section>

    <div id="wheel-wrapper" style="display:none;">
        <div></div>
    </div>

    <jsp:include page="./assets/footer.jsp"></jsp:include>
</body>
</html>