$(document).ready(function(){
    let loadingWheel = $("#loading-wheel-wrapper");

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

    /*$("form").on("submit", function(e){
        e.preventDefault();
    })*/

    $("#login").on("submit", function(e){
        $.ajax({
            type: 'POST',
            url: 'LoginController',

            data:{
                email: $("#login-email").val(),
                password: $("#login-password").val()
            },

            success: function(){
                loadingWheel.fadeOut(500);
                window.location.replace(`account.jsp#panoramica`);
            },

            statusCode: {
                //ERROR DISPLAY DO
                401: function(){
                    //alert("ERRORE BRO");
                    $("#login-error").css("opacity", "1");
                    loadingWheel.fadeOut(500);
                }
            }
        });

        loadingWheel.fadeIn(500);
        loadingWheel.children().first().load("./assets/loading-wheel.jsp");
        e.preventDefault();
    });

    $("#signup").on("submit", function(e){      
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'CheckEmailController',

            data:{
                email: $("#signup-email").val(),
            },

            success: function(){
                window.location.replace(`signup.jsp?username=${$("#username").val()}&signup-email=${$("#signup-email").val()}`);
            },

            statusCode: {
                401: function(){
                    $("#signup-error").css("opacity", "1");
                },
            }
        });
 
    });

    

});