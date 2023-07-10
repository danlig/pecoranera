<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pecoranera Jazz Art Bistrot</title>
        <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/info.css">
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
        <script type="module" src="${pageContext.request.contextPath}/scripts/info.js"></script>
    </head>
</head>
<body>
    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="info" />
    </jsp:include>

    <section id="pecoranera">
        <div class="side-text">
            <h2>COS'&#200; PECORANERA?</h2>
            <p>
                Pecora nera è chi è fuori dal gregge, oltre le masse, è chi non segue le mode, <br>
                chi non si conforma, chi non segue strade già battute, <br>
                chi non si allinea, chi non si piega e non si omologa, chi vive ardendo. <br>
                Questo spazio è un omaggio a tutte le pecore nere che attraversano il mondo in sordina lasciando tracce di fuoco e di sogni <br>
                agli artisti di ogni tempo, ai visionari, ai romanzieri, ai poeti, ai folli.
            </p>
        </div>

        <div id="slideshow">
            <img src="${pageContext.request.contextPath}/images/locale1.jpg" alt="" id="img1">
            <img src="${pageContext.request.contextPath}/images/locale2.jpg" alt="" id="img2">
            <img src="${pageContext.request.contextPath}/images/batteria2.jpg" alt="" id="img3">
        </div>
        
    </section>

    <section id="aldo-fucile">
        <img src="${pageContext.request.contextPath}/images/aldo_fucile.png" alt="">

        <div class="side-text">
            <h2>Aldo Fucile</h2>

            <p>
                "Affascinato sin da piccolo dagli strumenti a percussione ne 
                intuisco ben presto le infinite modalità espressive non solo di 
                natura ritmica ma anche melodica ed armonica. <br>
                Ho capito che l'esprimersi attraverso le percussioni è un'arte 
                primordiale; ho lentamente sviluppato la profonda consapevolezza 
                che l'origine dei suoni e del ritmo si trovano nella natura stessa 
                delle cose. <br>
                Il mio obiettivo è quello di riuscire a tradurre in suoni e 
                ritmo reali, servendomi di questa modalità di ascolto profondo, 
                le sonorità e il ritmo che incessantemente fluiscono nel tempo e che 
                forse costituiscono l'essenza della vita stessa e della natura."
            </p>
        </div>
    </section>
    
    <section id="contacts">
        <div class="side-text">
            <h3>CONTATTACI</h3>

            <a href="tel:+39347-084-0640">347 084 0640 <i class="fa-solid fa-phone"></i></a>
            <a href="tel:+39340-964-1940">340 964 1940 <i class="fa-solid fa-phone"></i></a>
            <a href="mailto:pecoranera7475@gmail.com" id="email">pecoranera@gmail.com <i class="fa-regular fa-envelope"></i></a>
        </div>

        <div class="side-text">
            <h3>I NOSTRI SOCIAL</h3>

            <a href="https://www.instagram.com/pecoranerajazz/"><i class="fa-brands fa-instagram"></i> @pecoranerajazz</a>          
            <a href="https://www.facebook.com/pecoranerajazz"><i class="fa-brands fa-square-facebook"></i> pecoranerajazz</a>
        </div>
    </section>

    <section id="location">
        <h2>DOVE SIAMO?</h2>

        <a href="https://goo.gl/maps/WJ8izt2dSWUPRVdp8?coh=178573&entry=tt"><i class="fa-solid fa-location-dot"></i> Via Francesco Vito, 17 - Pignataro Maggiore CE</a>
    </section>

    <jsp:include page="./assets/footer.jsp"></jsp:include>
</body>
</html>