import {showPassword, showPasswordSafety, checkRegex} from "./modules/passwordFunctionalities.js";

$(document).ready(function(){
    let arePasswordMatching = false;

    let loadingWheel = $("#loading-wheel-wrapper");

    //check if password and confirm password match
    let checkPasswordMatch = function() {
        let confPass = $("#confpassword-wrapper>span"),
            checkMark = $("#confpassword-wrapper>span>i");

        if($("#password").val() != $("#confirm-password").val()){
            confPass.removeClass("valid");
            checkMark.addClass("fa-xmark");
            checkMark.removeClass("fa-check");

            confPass.html(confPass.html().replace("Le password corrispondono ", "Le password non corrispondono "))
            return false;
        } else {
            confPass.addClass("valid");
            checkMark.removeClass("fa-xmark");
            checkMark.addClass("fa-check");

            confPass.html(confPass.html().replace("Le password non corrispondono ", "Le password corrispondono "))
            return true;
        }
    }

    //TO DO: AJAX CALL TO SIGNUP
    //After registration success/failure
    $("#registration-form").on("submit", async function(e){

        if(checkRegex()){
            if(arePasswordMatching){
                //Try registration

                $.ajax({
                    type: 'POST',
                    url: 'RegisterController',
        
                    data:{
                        username: $("#username").val(),
                        email: $("#email").val(),
                        password: $("#password").val(),
                        conf_password: $("#confirm-password").val()
                    },
        
                    success: function(){
                        alert("REGISTRATO");
                        loadingWheel.fadeOut(500);
                        window.location.replace(`account.jsp#panoramica`);
                    },
        
                    statusCode: {
                        401: function(request, error){
                            loadingWheel.fadeOut(500);
                            alert(JSON.stringify(error));
                            $("#signup-error").css("opacity", "1");
                            e.preventDefault();
                        },
                    }
                });


                loadingWheel.fadeIn(500);
                loadingWheel.children().first().load("./assets/loading-wheel.jsp");
            }

            else {
                console.log("Le password non corrispondono");
            }
        } else {
            console.log("La password scelta non è molto sicura!");
        }

        e.preventDefault();
    });

    

    //bind listeners to both password fields
    showPassword($("#see-password"));
    showPassword($("#see-confpassword"));


    //Check password validity while typing
    $("#password").on("input", function(){
        showPasswordSafety($(this));

        arePasswordMatching = checkPasswordMatch();
    });

    $("#password").on("keydown", function(e){
        if (e.keyCode == 32) {
            return false;
        }
    });

    $("#confirm-password").on("keydown", function(e){
        if (e.keyCode == 32) {
            return false;
        }
    })

    $("#confirm-password").on("input", function(){
        arePasswordMatching = checkPasswordMatch();
    });

    
});
