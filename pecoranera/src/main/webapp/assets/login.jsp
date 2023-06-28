<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <div id="form-wrapper">
        <link rel="stylesheet" href="css/login.css">
        <button class="close-login"><i class="fa-solid fa-xmark"></i></button>

        <form id="login" action="">
            <button class="close-login"><i class="fa-solid fa-xmark"></i></button>

            <div>
                <h3>Log In</h3>
                <input required type="email" name="login-email" id="login-email" class="text-input" placeholder="Email">
                <div id="password-div">
                    <input required type="password" name="login-password" id="login-password" class="text-input" placeholder="Password">
                    <span id="error"><i class="fa-solid fa-xmark"></i> Email o Password errata, riprova</span>
                </div>
                <input type="submit" value="Log In">
            </div>

            <a id="signup-link" href="signup">Non hai un account? Registrati</a>
        </form>

        <form id="signup" method="post" action="signup.jsp">
            <button class="close-login"><i class="fa-solid fa-xmark"></i></button>

            <div>
                <h3>Registrazione</h3>
                <div id="name-fields">
                    <input required type="text" name="name" id="name" class="text-input" placeholder="Nome">
                    <input required type="text" name="surname" id="surname" class="text-input" placeholder="Cognome">
                </div>
                <input required type="email" name="signup-email" id="signup-email" class="text-input" placeholder="Email">
                <input type="submit" value="Registrati">
            </div>

            <a id="login-link" href="login">Hai già un account?</a>
        </form>

        <script src="scripts/login.js"></script>
    </div>
</body>
</html>