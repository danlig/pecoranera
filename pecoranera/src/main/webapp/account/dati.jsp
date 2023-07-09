<section id="dati" class="account-subpage">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dati.css">
    <script type="module" src="${pageContext.request.contextPath}/scripts/dati.js"></script>


    <h3>Modifica dati personali</h3>

    <div>
        <label for="user">Nome utente</label>
        <input type="text" name="user" id="user" value="${sessionScope.username}">
        <button id="modify-user">Modifica</button>
    </div>

    <div>
        <label for="email">Email</label>
        <input type="email" name="email" id="email" value="${sessionScope.email}">
        <button id="modify-email">Modifica</button>
    </div>

    <div>
        
        <div id="old-password-field">
            <label for="old-password">Password Precedente</label>
            <input type="password" name="old-password" id="old-password">

            <div id="see-old-password"><i class="fa-regular fa-eye fa-eye-slash"></i></div>
        </div>

        <div id="new-password-field">
            <label for="new-password">Nuova Password</label>
            <input type="password" name="new-password" id="new-password">

            <div id="see-new-password"><i class="fa-regular fa-eye fa-eye-slash"></i></div>
        </div>

        <span>La nuova password deve contenere almeno:</span>

        <ul id="pwd-checklist">
            <li id="pwd-length" class="pwd-check">tra gli 8 e i 20 caratteri <i class="fa-solid fa-xmark"></i></li>
            <li id="pwd-upper" class="pwd-check">Una maiuscola <i class="fa-solid fa-xmark"></i></li>
            <li id="pwd-lower" class="pwd-check">Una minuscola <i class="fa-solid fa-xmark"></i></li>
            <li id="pwd-number" class="pwd-check">Un numero <i class="fa-solid fa-xmark"></i></li>
            <li id="pwd-special" class="pwd-check">Un carattere speciale <i class="fa-solid fa-xmark"></i></li>
        </ul>

        <button id="modify-password">Modifica Password</button>
    </div>
</section>