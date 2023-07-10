import { waitForElement } from "./modules/asyncFunctions.js";

$(document).ready(function(){

    let eventID = $("section").first().attr("id");
    const wheel = $("#wheel-wrapper>div");
    let cartQuantity = 0;

    let loadEvent = async function(){
        //getting quantity in cart of this event to avoid overflow
        $.ajax({
            url: "CartRetrieveController",

            dataType: "json",

            success: (data) => {
                $.each(data, function(key, val){
                    if(val.id.idEvent == eventID){
                        cartQuantity = parseInt(val.tickets);
                        return;
                    }
                })
            },

            error: () => {
                alert("Errore nella quantità dei biglietti");
            }
        }).done(

            //getting event data
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
    
                        $("#available-tickets>span").text(data.availableTickets - cartQuantity);
    
                        if(data.availableTickets - cartQuantity < 1){
                            $("#available-tickets>span").addClass("out-of-tickets");
                            $("#ticket-availability").hide();
                            $("#ticket-info>span").show();
                        } else {
                            for(let i = 1; i <= data.availableTickets - cartQuantity; i++){
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
            })
        );


        
        
    }

    $("#add-to-cart").on("click", async function(){
        let ticketNumber = parseInt($("#ticket-number").val());
        wheel.parent().show();
        
        wheel.load("./assets/loading-wheel.jsp");

        waitForElement(".lds-roller").then(() =>{
            $.ajax({
                url: "CartAddController",
    
                data: {
                    tickets: ticketNumber,
                    event: eventID
                },
        
                success: () => {
                    wheel.children().remove();
                    wheel.append('<i class="fa-solid fa-check fa-bounce" style="color:lawngreen"></i>')
                    let options = $("#ticket-number").children("option");
                    let max = options.last().attr("value");

                    $("#available-tickets>span").text(max-ticketNumber);

                    if(max == ticketNumber){
                        $("#right-div").remove();
                    } else {
                        $.each(options, function(key, option){
                            if(parseInt($(option).attr("value")) > max - ticketNumber){
                                $(option).remove();
                            }
                        })
                    }

                    

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
