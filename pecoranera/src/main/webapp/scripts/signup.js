$(document).ready(function(){

    let pwdLength = /^.{10,35}$/,
        pwdUpper = /[A-Z]+/,
        pwdLower = /[a-z]+/,
        pwdNumber = /[0-9]+/,
        pwdSpecial = /[!@#$%^&()'[\]"?+-/*={}.,;:_]+/;

    let checkLength = false,
        checkUpper = false,
        checkLower = false,
        checkNumber = false,
        checkSpecial = false,
        arePasswordMatching = false;

    let loadingWheel = $("#loading-wheel-wrapper");

    //TO DO: AJAX CALL TO SIGNUP
    //After registration success/failure
    $("#registration-form").on("submit", async function(e){

        if(checkLength && checkLower && checkUpper && checkNumber && checkSpecial){
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
                        401: function(message){
                            loadingWheel.fadeOut(500);
                            alert(JSON.stringify(message));
                            $("#signup-error").css("opacity", "1");
                            e.preventDefault();
                        },
                    }
                });


                loadingWheel.fadeIn(500);
                loadingWheel.children().first().load("./assets/loading-wheel.html");
            }

            else {
                console.log("Le password non corrispondono");
            }
        } else {
            console.log("La password scelta non è molto sicura!");
        }

        e.preventDefault();
    });

    //Click on eye(s) to show passwords
    let showPassword = function(source) {
        
        $(source).on('click', function() {
            let passField = $(this).siblings().first();

            $(this).children().first().toggleClass("fa-eye");

            if(passField.prop("type") !== "password"){
                passField.prop("type", "password");
            } else {
                passField.prop("type", "text");
            }
        });
    }

    //Check passsword validity
    let checkPassword = function(elem, isValid) {
        let checkMark = $(elem).children().first();

        if(isValid){
            $(elem).addClass("valid");
            checkMark.removeClass("fa-xmark");
            checkMark.addClass("fa-check");

            return true;
        } else {
            $(elem).removeClass("valid");
            checkMark.addClass("fa-xmark");
            checkMark.removeClass("fa-check");

            return false;
        }
    }

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

    //bind listeners to both password fields
    showPassword($("#see-password"));
    showPassword($("#see-confpassword"));


    //Check password validity while typing
    $("#password").on("input", function(){
        let pwd = $(this).val();

        if(pwdLength.test(pwd)){
            checkLength = checkPassword($("#pwd-length"), true);
        } else {
            checkLength = checkPassword($("#pwd-length"), false);
        }

        if(pwdUpper.test(pwd)){
            checkUpper = checkPassword($("#pwd-upper"), true);
        } else {
            checkUpper = checkPassword($("#pwd-upper"), false);
        }

        if(pwdLower.test(pwd)){
            checkLower = checkPassword($("#pwd-lower"), true);
        } else {
            checkLower = checkPassword($("#pwd-lower"), false);
        }

        if(pwdNumber.test(pwd)){
            checkNumber = checkPassword($("#pwd-number"), true);
        } else {
            checkNumber = checkPassword($("#pwd-number"), false);
        }

        if(pwdSpecial.test(pwd)){
            checkSpecial = checkPassword($("#pwd-special"), true);
        } else {
            checkSpecial = checkPassword($("#pwd-special"), false);
        }

        arePasswordMatching = checkPasswordMatch();
    });

    $("#confirm-password").on("input", function(){
        arePasswordMatching = checkPasswordMatch();
    });


});