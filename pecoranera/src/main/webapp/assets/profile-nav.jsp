<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile-nav.css">
    <script src="${pageContext.request.contextPath}/scripts/profile-nav.js"></script>
</head>
<body>
    <div id="profile-nav">
        <a href="#panoramica" class="account-link ${param.profile_page == 'panoramica'  ? 'active_page' : ''}">Panoramica</a>
        <a href="#ordini" class="account-link ${param.profile_page == 'ordini'  ? 'active_page' : ''}">Ordini</a>
        <a href="#preferenze" class="account-link ${param.profile_page == 'preferenze'  ? 'active_page' : ''}">Preferenze</a>
        <a href="#dati" class="account-link ${param.profile_page == 'dati'  ? 'active_page' : ''}">Dati Personali</a>
        <a href="logout" class="account-link">Esci</a>
    </div>
</body>
</html>