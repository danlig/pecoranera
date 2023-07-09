import {showPassword, showPasswordSafety, checkRegex} from "./signup.js";

$(document).ready(function(){

    showPassword($("#see-old-password"));
    showPassword($("#see-new-password"));

    $("#new-password").on("input", function(){
        showPasswordSafety($(this));
    });

    $("#modify-user").on("click", function(){
        $.ajax({
            url: "UserEditController",

            data:{
                username: $("#user").val()
            },

            success: () => {
                alert("Nome utente aggiornato!");
            },

            error: (xhr, ajaxOptions, thrownError) => {
                switch(xhr.responseText){
                    case "username":
                        alert("Nuovo nome invalido");
                        break;
                    
                    default:
                        alert("Errore di modifica");
                        break;
                }
            }
        });
    });

    $("#modify-email").on("click", function(){
        $.ajax({
            url: "UserEditController",

            data:{
                email: $("#email").val()
            },

            success: () => {
                alert("Email aggiornata!");
            },

            error: (xhr, ajaxOptions, thrownError) => {
                switch(xhr.responseText){
                    case "username":
                        alert("Nuova email invalida");
                        break;
                    
                    default:
                        alert("Errore di modifica");
                        break;
                }
            }
        });
    });

    $("#modify-password").on("click", function(){
        if(checkRegex()){
            $.ajax({
                url: "UserEditController",

                data:{
                    password: $("#old-password").val(),
                    newPassword: $("#new-passoword").val()
                },

                success: () => {
                    alert("Password aggiornata!");
                },

                error: (xhr, ajaxOptions, thrownError) => {
                    switch(xhr.responseText){
                        case "newPassword":
                            alert("Nuova password invalida");
                            break;
                        
                        case "password":
                            alert("Password vecchia errata");
                            break;
                        
                        default:
                            alert("Errore di modifica");
                            break;
                    }
                }
            });
        }
    });
});