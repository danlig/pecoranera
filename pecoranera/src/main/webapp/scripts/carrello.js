import { waitForElement } from "./modules/asyncFunctions.js";

$(document).ready(function(){

    let refreshCost = function(){
        let total = 0;

        $(".event").each(function(){
            let quantity = parseInt($(this).find(".quantity").first().val());
            let price = parseFloat($(this).find(".price>span").text());

            total += quantity * price;
        });

        $("#price-total>span").text(total.toFixed(2));
    }

    let tickeToToHTML = function(ticket, quantity){
        let options = "";

        for(let i = 1; i <= ticket.availableTickets; i++){
            options = options.concat(`<option value='${i}' ${i == quantity ? "selected" : ""}>${i}</option>`);
        }

        return `<div id="${ticket.id}" class="event">
                    <img src="" alt="">

                    <div class="event-details">
                        <h3 class="event-header">${ticket.name}</h3>
                        <h4 class="event-date">${new Date(Date.parse(ticket.date)).toLocaleDateString("it-IT", {day:"2-digit", month:"2-digit", year:"numeric"})}</h4>

                        <div class="tag-list">
                            ${ticket.tags.map(tag => (`<span class="event-tag">${tag.name}</span>`) ).join("")}
                        </div>
                    </div>

                    <div class="ticket-data">
                        <div>
                            <label for="quantity">Quantità: </label>
                            <select name="quantity" class="quantity" data-event="${ticket.id}">
                                ${options}
                            </select>
                        </div>
                        
                        <span class="price">€ <span>${parseFloat(ticket.price).toFixed(2)}</span></span>

                        <button value="${ticket.id}" class="remove-event">Rimuovi</button>
                    </div>

                </div>`;
    }

    $(window).on("load", async function(){
        return $.ajax({
            url: "CartRetrieveController",

            success: async (cartEvent) => {
                await $.each(cartEvent, function(key, cart){

                    return $.ajax({
                        url: "SingleEventController",

                        

                        dataType:"json",

                        data:{
                            id: cart.id.idEvent
                        },

                        success: (event) => {
                            $("#cart-list").append(tickeToToHTML(event, cart.tickets));
                        },

                        error: () => {
                            alert("Errore nel caricamento di un evento");
                        }
                    }).done(() => {
                        refreshCost();
                    });
                });
            },

            error: () => {
                alert("Errore nel caricamento del carrello");
            }
        });
        
    });

    //remove event on click
    $("#cart-list").on("click", ".remove-event", function(){
        const eventID = $(this).attr("value");

        $.ajax({
            url: "CartDeleteController",

            

            data:{
                event: eventID
            },

            statusCode:{
                200: () =>{   
                    $(this).parent().parent().remove();

                    refreshCost();
                },

                500: () => {alert("errore eliminazione prodotto")}
            }
        })
    });

    //Change total and call backend cart on quantity change
    $("#cart-list").on("change", ".quantity", function(){
        const eventID = $(this).attr("data-event");

        $.ajax({
            url: "CartAddController",

            

            data: {
                tickets: parseInt($(this).val()),
                event: eventID,
                edit: true
            },
    
            success: () => {
               refreshCost();
            },

            error: () => {
               alert("errore modifica quantità ");
            }  
        })
    });

    //empty all cart
    $("#empty-cart").on("click", function(){
        $(".event").each(function(){
            $.ajax({
                url: "CartDeleteController",

                
    
                data:{
                    event: $(this).attr("id")
                },
    
                statusCode:{
                    200: () =>{   
                        $(this).remove();
    
                        refreshCost();
                    },
    
                    500: () => {alert("errore eliminazione prodotto")}
                }
            })
        })
    });

    $("#buy-button").on("click", function(){
            $.ajax({
                url: "OrderAddController",

                success: () => {
                    $("#shopping-acknowledgement").show();

                    $(".event").remove();

                    setTimeout(function () {
                        $("#shopping-acknowledgement").fadeOut(750);
                    }, 4000);

                    refreshCost();
                },

                error: (xhr, status, error) => {
                    alert("Errore con l'acquisto " +xhr.responseText );
                }
            });
    });

});