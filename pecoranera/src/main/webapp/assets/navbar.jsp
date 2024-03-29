<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="it">
    <head>
    	<title>Navbar</title>
        <script src="https://kit.fontawesome.com/0447c444c7.js" integrity="sha384-NXqxMSPXxh/QHJjTiZE20Ff9wAAqXItyo64QBwdeGVkl2y83+PEOLcMWCDu6B5lC" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
        <script>
            var isLoggedIn = ("${sessionScope.isAdmin == null || empty sessionScope.isAdmin}" == "false");
        </script>
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/scripts/navbar.js"></script>
    </head>
    <body> 
        <nav class="${param.active_page == 'home'  ? 'navbar-home' : ''}">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="">
                <div>
                    <span id="logo-name">PECORANERA</span><br>
                    <span id="logo-text">Jazz Art Bistrot</span>
                </div>
            </div>

            <button id="nav-button">
                <span></span>
                <span></span>
                <span></span>
            </button>

            <div id="sidebar" class="closed">
                <div id="outside-close" class="closed"></div>

                <ul>
                    <li><a href="${pageContext.request.contextPath}/index.jsp" class="nav-links ${param.active_page == 'home'  ? 'active_link' : ''}">HOME</a></li>
                    <li><a href="${pageContext.request.contextPath}/${sessionScope.isAdmin == true ?'admin/show.jsp' : 'eventi.jsp'}" class="nav-links ${param.active_page == 'eventi'  ? 'active_link' : ''}">EVENTI</a></li>
                    <li><a href="${pageContext.request.contextPath}/${sessionScope.isAdmin == true ? 'admin/product-type/list' : 'menu.jsp'}" class="nav-links ${param.active_page == 'menu'  ? 'active_link' : ''}">MENU</a></li>
                    <li><a href="${pageContext.request.contextPath}/${sessionScope.isAdmin == true ? 'admin/ordini.jsp' : 'info.jsp'}" class="nav-links ${param.active_page == 'info'  ? 'active_link' : ''}"><span>${sessionScope.isAdmin == true ? 'ORDINI' : 'INFO'}</span></a></li>
                    <li id="nav-icons">
                        <a href="${sessionScope.isAdmin == true ? '../account.jsp' : 'account.jsp'}" class="nav-links ${param.active_page == 'account'  ? 'active_link' : ''}">
                            <c:choose>
                                <c:when test= "${sessionScope.isAdmin == null || empty sessionScope.isAdmin || sessionScope.isAdmin == false}">
                                    <i class="fa-regular fa-circle-user"></i>
                                </c:when>
                                
                                <c:otherwise>
                                    <i class="fa-solid fa-user-lock"></i>
                                </c:otherwise>
                            </c:choose>
                        </a>

                        <c:if test= "${sessionScope.isAdmin != true}">
                            <a href="carrello.jsp" class="nav-links ${param.active_page == 'carrello'  ? 'active_link' : ''}"><i class="fa-solid fa-cart-shopping"></i></a>
                        </c:if>

                    </li>   
                </ul>
            </div>
        </nav>

        <div id='login-wrapper' style='display: none;'></div>
    </body>
</html>