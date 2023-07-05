<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/scripts/home.js"></script>
</head>
<body>
    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="home" />
    </jsp:include>
    
    <section id="start-section">
        <p>PECORA NERA &#200; CHI &#200; FUORI DAL GREGGE</p>
    </section>

    <div class="spacer"></div>

    <a href="#" id="scrolldown-btn">
        <span></span>
        <span></span>
    </a>

    <section id="chisiamo">
        <div class="side-text">
            <h1>CHI SIAMO?</h1>
            
            <p>
                Pecoranera nasce con l&#39;idea di portare il Jazz nel territorio locale<br><br>
                Nasce con l&#39;idea di far portare la buona musica alle persone <br><br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                Etiam id feugiat ipsum, sit amet lacinia ex. 
                Vestibulum mollis erat in augue eleifend suscipit. 
                Nulla tellus lacus, luctus vitae finibus id, venenatis in massa. 
            </p>
        </div>

        <div class="side-images">
            <img src="${pageContext.request.contextPath}/images/batteria2.jpg" alt="">
            <img src="${pageContext.request.contextPath}/images/locale1.jpg" alt="">
            <img src="${pageContext.request.contextPath}/images/locale2.jpg" alt="">
        </div>
    </section>

    <section id="eventi">
        <div class="side-text">
            <h1>EVENTI</h1>

            <h2>Vivi l&#39;esperienza jazz al Pecoranera! <br><br></h2>

            <p>Eventi di musica dal vivo ogni settimana<br><br>
            
                Artisti da ogni parte del mondo vengono ad esibirsi al Pecoranera<br><br>
            
                Cosa aspetti?<br><br>

                <a href="eventi.jsp">Scopri i nostri eventi</a>
            </p>
        </div>
    </section>

    <section id="menu">
        <h1>MENU</h1>
        
        <div id="menu-choice">
            <a id="food" href="menu.jsp#birre">
                Hai fame? Prova il nostro menu!
            </a>

            <a id="drink" href="menu.jsp#panini">
                Hai sete? Sperimenta i nostri drink!
            </a>
        </div>
    </section>
    

    <jsp:include page="./assets/footer.jsp"></jsp:include>
</body>
</html>