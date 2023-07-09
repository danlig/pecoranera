import { waitForElement } from "./modules/asyncFunctions.js";

$(document).ready(function(){

    let eventID = $("section").first().attr("id");
    const wheel = $("#wheel-wrapper>div");

    let loadEvent = async function(){
        $.ajax({
            url: "SingleEventController",

            dataType: "json",

            data:{
                id: eventID
            },

            success: function(data){
                let eventDate = new Date(Date.parse(data.date.replace("?", " ")));

                $(".event-header").each(function() {
                    $(this).text(data.name);
                });

                $(".event-date").each(function() {
                    $(this).text(eventDate.toLocaleDateString("it-IT", {day:"2-digit", month:"2-digit", year:"numeric"}));
                });

                if(eventDate < new Date()){
                    $("#right-div").remove();

                } else {
                    $("#ticket-price").text("€ " + data.price.toFixed(2));

                    $("#available-tickets>span").text(data.availableTickets);

                    if(data.availableTickets < 1){
                        $("#available-tickets>span").addClass("out-of-tickets");
                        $("#ticket-availability").hide();
                        $("#ticket-info>span").show();
                    } else {
                        for(let i = 1; i <= data.availableTickets; i++){
                            $("#ticket-number").append(`<option value='${i}' ${i == 1 ? "selected" : ""}>${i}</option>`)
                        }
                    }
                }

                data.tags.forEach(tag => {
                    $("#tag-list").append(`<span id="${tag.id}" class="filter-tag">${tag.name}</span>`)
                });

                data.eventArtists.forEach(eArtist => {
                    $("#artist-list").append(`<span id="${eArtist.artist.id}" class="artist">${eArtist.artist.name} (${eArtist.role})</span>`)
                });

                $("#event-description").text(data.description);
            }
        });
    }

    $("#add-to-cart").on("click", async function(){
        wheel.parent().show();
        
        wheel.load("./assets/loading-wheel.jsp");

        waitForElement(".lds-roller").then(() =>{
            $.ajax({
                url: "CartAddController",
    
                data: {
                    tickets: parseInt($("#ticket-number").val()),
                    event: eventID
                },
        
                success: () => {
                    wheel.children().remove();
                    wheel.append('<i class="fa-solid fa-check fa-bounce" style="color:lawngreen"></i>')
                },
    
                error: () => {
                    wheel.children().remove();
                    wheel.append('<i class="fa-solid fa-xmark fa-bounce" style="color:crimson"></i>')
                }
            })
        });

        setTimeout(function () {
            wheel.parent().fadeOut(750);
        }, 3000);
        
        
    });

    loadEvent();
});
