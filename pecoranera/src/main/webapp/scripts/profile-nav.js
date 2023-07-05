$(document).ready(function(){
    let firstPage = window.location.hash.substring(1) || "panoramica";

    let loadPage = function(page){
        $(".active_page").each(function(){
            $(this).removeClass("active_page");
        });

        $(`a[href='#${page}']`).addClass("active_page");

        $(".account-subpage").each(function(){
            $(this).remove();
        });

        $("#display-data").load(`account/${page}.jsp`);
    }

    loadPage(firstPage);

    $("a[href='logout']").on("click", function(e){
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'LogoutController',

            success: function(){
                window.location.replace(`index.jsp`);
            },

            statusCode: {
                401: function(message){
                    alert(JSON.stringify(message));
                },
            }
        });
    });

    $(".account-link").on("click", function(e){
        e.preventDefault();

        loadPage($(this).attr("href").replace("#", ""));
    });
});