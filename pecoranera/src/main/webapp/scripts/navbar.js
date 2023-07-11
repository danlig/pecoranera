$(document).ready(function() {
    let navbarBtn = $("#nav-button"),
        sidebar = $("#sidebar"),
        outsideClose = $("#outside-close");

    navbarBtn.add(outsideClose).on("click", function() {
        navbarBtn.toggleClass('open');

        sidebar.toggleClass('closed');

        outsideClose.toggleClass('closed');
    })
    
    $(window).scroll(function() {
        if($(".nav-links").first().hasClass("active_link")){
            if ($(document).scrollTop() > 1) {
                $('nav').removeClass('navbar-home');
            }
            else {
                $('nav').addClass('navbar-home');
            }
        }
        
    });

    $(".nav-links[href='account.jsp']").on("click", function(e){
        e.preventDefault();

        if(!isLoggedIn){
            $("#login-wrapper").toggle();

            if(!$("#form-wrapper").length){
                $("#login-wrapper").load('assets/login.jsp');
            }

            navbarBtn.removeClass('open');

            sidebar.addClass('closed');

            outsideClose.addClass('closed')
        }
        
        else{
            $(location).attr('href', "account.jsp");
        }
    });

});

