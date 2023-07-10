<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script type="module" src="${pageContext.request.contextPath}/scripts/signup.js" charset="utf-8"></script>
</head>
<body>
    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="account" />
    </jsp:include>

    <div id="nav-spacer"></div>

    <section id="registration">
        <a id="goback-link" href="index.jsp"><i class="fa-solid fa-arrow-left"></i> Torna alla home</a>
        <h2>Registrazione</h2>
        <form id="registration-form" method="post" action="">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" pattern="^[a-zA-Z0-9_]{4,15}$" title="Inserisci solo lettere, numeri e underscore, lunghezza tra i 4 e 15 caratteri" value='${e:forHtml(param.username)}'>
            
            <label for="email">E-mail</label>
            <input type="email" name="email" id="email" value='${e:forHtml(param.signup-email)}'>
            
            <label for="password">Password</label>
            <div id="password-wrapper">
                <div id="password-field">
                    <input type="password" name="password" id="password">
    
                    <div id="see-password"><i class="fa-regular fa-eye fa-eye-slash"></i></div>
                </div>
                
                <span>La password deve contenere almeno:</span>

                <ul id="pwd-checklist">
                    <li id="pwd-length" class="pwd-check">tra gli 8 e i 20 caratteri <i class="fa-solid fa-xmark"></i></li>
                    <li id="pwd-upper" class="pwd-check">Una maiuscola <i class="fa-solid fa-xmark"></i></li>
                    <li id="pwd-lower" class="pwd-check">Una minuscola <i class="fa-solid fa-xmark"></i></li>
                    <li id="pwd-number" class="pwd-check">Un numero <i class="fa-solid fa-xmark"></i></li>
                    <li id="pwd-special" class="pwd-check">Un carattere speciale <i class="fa-solid fa-xmark"></i></li>
                </ul>
            </div>

            <label for="confirm-password">Conferma Password</label>
            <div id="confpassword-wrapper">
                <div id="confpassword-field">
                    <input type="password" name="confirm-password" id="confirm-password">
    
                    <div id="see-confpassword"><i class="fa-regular fa-eye fa-eye-slash"></i></div>
                </div>

                <span class="valid">Le password corrispondono <i class="fa-solid fa-check"></i></span>
            </div>

            <input id="submit" type="submit" value="Registrati">
        </form>
    </section>

    <div id="loading-wheel-wrapper" style="display: none;">
        <div id="loading-wheel">

        </div>
    </div>

    <section id="registration-submitted">
        
    </section>
    
    <jsp:include page="./assets/footer.jsp"></jsp:include>
</body>
</html>