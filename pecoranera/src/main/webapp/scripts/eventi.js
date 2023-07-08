import {loadTags, moveToListAndSort, resetTags, selectedTags} from "./modules/eventTagManager.js";

$(document).ready(function(){
    
    let navBarHeight = $("nav").outerHeight();
    let searchBar = $("#searchbar");

    let sticky = searchBar.offset();;

    let filterButton = $("#filtri-btn")
    let filterBar = $("#filter-fieldset");
    let pageNumber = 0;

    filterBar.slideUp();

    let resetSearchbarData = function(){
        searchBar.css("top", navBarHeight + "px");
        navBarHeight = $("nav").outerHeight();
        sticky = searchBar.offset();
    }

    
    let eventToHTML = function(event){        

        return `<a class="small-event" href="event_details.jsp?id=${event.id}">
                    <h2>${event.name}</h2>

                    <div class="tags">
                        ${event.tags.map(tag => (`<span class="event-tag">${tag.name}</span>`) ).join("")}
                    </div>

                    <div class="event-date">${new Date(Date.parse(event.date.replace("?", " "))).toLocaleDateString("it-IT", {weekday:"long",month:"short", day:"numeric"})}</div>
        </a>`;
    }

    let upcomingEventHTML = function(event){
        return `<a class="upcoming-event" href="event_details.jsp?id=${event.id}">
                    <img src="./images/lellopetrarca.jpg" alt="">

                    <div>
                            <h2>${event.name}</h2>

                            <div class="tags">
                                ${event.tags.map(tag => (`<span class="event-tag">${tag.name}</span>`) ).join("")}
                            </div>
                    
                            <div class="event-date">${new Date(Date.parse(event.date.replace("?", " "))).toLocaleDateString("it-IT", {weekday:"long",month:"short", day:"numeric"})}</div>
                    </div>
                </a>`;
    }

    let loadUpcoming = async function(){
        await $.ajax({
            url: "UpcomingEventsController",

            dataType: "json",

            success: function(data){
                $.each(data, function(key, val){
                    $("#upcoming>div").append(upcomingEventHTML(val));
                })
            },

            error: function(){
                alert("Errore nel caricamento degli eventi evidenziati");
            }
        });

        resetSearchbarData();
    }

    let loadEvents = async function(offsetPage){
        await $.ajax({
            url: "EventRetrieveController",

            data: {
                startDate: $("#data-inizio").val() || new Date().toLocaleDateString("en-CA", {year:"numeric",month:"2-digit", day:"2-digit"}).replace("/", "-"),
                endDate: $("#data-fine").val() || "2200-12-31",
                name: $("#name-search").val(),
                tags: JSON.stringify(selectedTags),
                offset: offsetPage,
                pageSize: 1
            },

            dataType: "json",

            success: function(data){
                if(offsetPage == 0){
                    $("#all-events").children(".small-event").remove();
                }
                
                if(data.length == 0){
                    $("#loader-wrapper").hide();
                } else {
                    $.each(data, function(key, val){
                        $("#all-events").append(eventToHTML(val));
                    })
                }
            },

            error: function(){
                alert("Erroe caricamento eventi");
                $("loader-wrapper").hide();
            }
        });
    
        return;
    }


    loadTags($(window), "#filter-choice div");
    moveToListAndSort("#selected-filter div", "#filter-choice div", false);
    moveToListAndSort("#filter-choice div", "#selected-filter div", true);
    resetTags($("input[type=reset]"), $("#selected-filter div"), $("#filter-choice div"))
    loadEvents(0);
    loadUpcoming();

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
        resetSearchbarData();
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

    $("#submit-filters").on("click", function(){
        pageNumber = 0;
        $("#loader-wrapper").show();
        loadEvents(pageNumber);        
    })

    $("#search-button").on("click", function(){
        pageNumber = 0;
        $("#loader-wrapper").show();
        loadEvents(pageNumber);        
    });

    $("#loader-button").on("click", function(){
        pageNumber++;
        loadEvents(pageNumber);
    })
});