<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <div id="form-wrapper">
        <link rel="stylesheet" href="css/login.css">
        <button class="close-login"><i class="fa-solid fa-xmark"></i></button>

        <form id="login" method="post" action="">
            <button class="close-login"><i class="fa-solid fa-xmark"></i></button>

            <div>
                <h3>Log In</h3>
                <input required type="email" name="login-email" id="login-email" class="text-input" placeholder="Email">
                <div id="password-div">
                    <input required type="password" name="login-password" id="login-password" class="text-input" placeholder="Password">
                    <span id="login-error" class="error"><i class="fa-solid fa-xmark"></i> Email o Password errata, riprova</span>
                </div>
                <input id="login-submit" type="submit" value="Log In">
            </div>

            <a id="signup-link" href="signup">Non hai un account? Registrati</a>
        </form>

        <form id="signup" method="post" action="">
            <button class="close-login"><i class="fa-solid fa-xmark"></i></button>

            <div>
                <h3>Registrazione</h3>
                
                <input required type="text" name="username" id="username" class="text-input" placeholder="Username">
  
                <div>
                    <input required type="email" name="signup-email" id="signup-email" class="text-input" placeholder="Email">
                    <span id="signup-error" class="error"><i class="fa-solid fa-xmark"></i> L'email è già associata ad un account</span>
                </div>

                <input id="signup-submit" type="submit" value="Registrati">
            </div>

            <a id="login-link" href="login">Hai già un account?</a>
        </form>

        <script src="scripts/login.js"></script>
    </div>
</body>
</html>