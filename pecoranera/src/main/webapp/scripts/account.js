$(document).ready(function(){
    let subPage = window.location.hash.substring(1) || "panoramica";
    subPage = "#" + subPage;

    function loadSubPage(elem) {
        elem.addClass("active");
        elem.siblings().removeClass("active");

        $(elem.attr("href")).css("display", "block");
        $(elem.attr("href")).siblings().css("display", "none");
    }

    loadSubPage($(`a[href='${subPage}']`));

    $(".account-link").on("click", function(){
        loadSubPage($(this));
    });

});