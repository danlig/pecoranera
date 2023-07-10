$(document).ready(function(){
    let firstPage = window.location.hash.substring(1) || "panoramica";
    let previousPage = null;
    let pages = [];

    let loadPage = function(page){
        $(".active_page").each(function(){
            $(this).removeClass("active_page");
        });

        $(`a[href='#${page}']`).addClass("active_page");

        if(previousPage != null){
            pages[previousPage] = $(`#${previousPage}`).detach();
        }

        if(pages.hasOwnProperty(page)){
            //$(`#${page}`).show();
            $("#display-data").append(pages[page]);
        } else {
            $("#display-data").load(`account/${page}.jsp`);
        }

        previousPage = page;
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
        let href = $(this).attr("href");
        e.preventDefault();

        window.location.hash = href;
        loadPage(href.replace("#", ""));

        if(href == "#panoramica"){
            location.reload();
        }
    });
});