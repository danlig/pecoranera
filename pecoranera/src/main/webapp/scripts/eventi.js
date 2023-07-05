import {loadTags, moveToListAndSort, resetTags} from "./modules/eventTagManager.js";

$(document).ready(function(){
    
    let navBarHeight = $("nav").outerHeight();
    let searchBar = $("#searchbar");

    let sticky = searchBar.offset();;


    let filterButton = $("#filtri-btn")
    let filterBar = $("#filter-fieldset");
    filterBar.slideUp();
    
    loadTags($(window));
    moveToListAndSort("#selected-filter div", "#filter-choice div", false);
    moveToListAndSort("#filter-choice div", "#selected-filter div", true);
    resetTags($("input[type=reset]"), $("#selected-filter div"), $("#filter-choice div"))

    //toggle filterbar
    filterButton.on("click", function(){
        $(this).toggleClass("active");
        filterBar.slideToggle(700);
    });

    //prevent form submission
    $(window).submit(function(e){
        e.preventDefault();
    });

    //Reset important data on window resize
    $(window).resize(function(){
        searchBar.css("top", navBarHeight + "px");
        navBarHeight = $("nav").outerHeight();
        sticky = searchBar.offset();
    });


    //Make searchbar sticky on scroll
    $(window).on("scroll resize", function() {

        if ($(document).scrollTop() >= sticky.top - navBarHeight) {
            searchBar.css("top", navBarHeight + "px");
            $("#upcoming").css("margin-bottom", "calc(" + searchBar.outerHeight() + "px + 5vh)");
            searchBar.addClass('sticky');
        }
        else {
            searchBar.removeClass('sticky');
            $("#upcoming").css("margin-bottom", "0px");
        }
    
    });
});