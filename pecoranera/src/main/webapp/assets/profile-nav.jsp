<!DOCTYPE html>
<html lang="it">
<head>
	<title>Profile navbar</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile-nav.css">
    <script src="${pageContext.request.contextPath}/scripts/profile-nav.js"></script>
</head>
<body>
    <div id="profile-nav">
        <a href="#panoramica" class="account-link"><span>Panoramica</span><i class="fa-solid fa-panorama"></i></a>
        <a href="#ordini" class="account-link"><span>Ordini</span><i class="fa-solid fa-ticket"></i></a>
        <a href="#preferenze" class="account-link"><span>Preferenze</span><i class="fa-solid fa-thumbs-up"></i></a>
        <a href="#dati" class="account-link"><span>Dati Personali</span><i class="fa-solid fa-circle-info"></i></a>
        <a href="logout" class="account-link"><span>Esci</span><i class="fa-solid fa-right-from-bracket"></i></a>
    </div>
</body>
</html>