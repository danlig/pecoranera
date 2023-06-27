$(document).ready(function(){

    $(".close-login").on("click", function(e){
        e.preventDefault();

        $("#login-wrapper").toggle();
    });

    $("#signup-link").click(function(e){
        e.preventDefault();

        $("#form-wrapper").addClass("register");
    });

    $("#login-link").click(function(e){
        e.preventDefault();

        $("#form-wrapper").removeClass("register");
    });
});