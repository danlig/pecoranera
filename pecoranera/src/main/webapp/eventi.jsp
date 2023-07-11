<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eventi.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script type="module" src="${pageContext.request.contextPath}/scripts/eventi.js"></script>
</head>
<body>

    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>
    
    <section id="upcoming">
        <h1>Prossimi Eventi</h1>

        <div>
            
        </div>    

    </section>

    <div id="searchbar">
            <div id="upper-search">

                <button id="filtri-btn" class="searchbar-btn">Filtri</button>
    
                <div id="search-input">
                    <input type="text" name="name-search" id="name-search" placeholder="Cerca qui gli eventi" size="40">
                    <button type="submit" id="search-button"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            

            <fieldset id="filter-fieldset">
            <legend>Filtri</legend>
            
                <div>
                    <div id="date-fields">
                        <h3>Intervallo date</h3>

                        <div>
                            <label for="data-inizio">Inizio:</label>
                            <input type="date" name="data-inizio" id="data-inizio">
                        </div>

                        <div>
                            <label for="data-fine">Fine:</label>
                            <input type="date" name="data-fine" id="data-fine">
                        </div>
                    </div>

                    <div id="filters">
                        <div id="selected-filter">
                            <h3>Generi selezionati</h3>
                            
                            <div></div>
                        </div>
        
                        <div id="filter-choice">
                            <h3>Seleziona genere</h3>
                            
                            <div></div>
                        </div>
                    </div>
                </div>

                <div id="form-buttons">
                    <input id="submit-filters" type="submit" value="Applica Filtri" class="searchbar-btn">
                    <input type="reset" class="searchbar-btn">
                </div>
            </fieldset>

    </div>

    <section id="all-events">
        
        
    </section>

    <div id="loader-wrapper">
        <button id="loader-button">Carica altri eventi</button>
    </div>

    <jsp:include page="./assets/footer.jsp"></jsp:include>
    
</body>
</html>