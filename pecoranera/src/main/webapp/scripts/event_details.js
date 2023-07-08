$(document).ready(function(){

    let eventID = $("section").first().attr("id");

    let loadEvent = async function(){
        $.ajax({
            url: "SingleEventController",

            dataType: "json",

            data:{
                id: eventID
            },

            success: function(data){
                let eventDate = new Date(Date.parse(data.date));

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
                            $("#ticket-number").append(`<option value='${i}'>${i}</option>`)
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

    $("#add-to-cart").on("click", function(){
        $.ajax({
            url: "CartAddController",

            dataType: "json",

            success: function(){
                
            }
        });
    });

    /*let refreshEvent = async function(){
        $.ajax({
            url: "SingleEventController",

            dataType: "json",

            data:{
                id: eventID
            },

            success: function(data){

                $("#available-tickets>span").text(data.availableTickets);

                if(data.availableTickets < 1){
                    $("#available-tickets>span").addClass("out-of-tickets");
                    $("#ticket-availability").hide();
                    $("#ticket-info>span").show();
                } else {
                    $("#available-tickets>span").removeClass("out-of-tickets");
                    $("#ticket-availability").show();
                    $("#ticket-info>span").hide();

                    $("#ticket-number").children().remove();

                    for(let i = 1; i <= data.availableTickets; i++){
                        $("#ticket-number").append(`<option value='${i}'>${i}</option>`)
                    }
                }
            }
        });
    }*/

    loadEvent();
});
