import {tagsToHtml} from "./modules/eventTagManager.js";

$(document).ready(function(){
    let orderToHTML= function(order){
        return `<div id="${order.id}" class="order">
                    <a class="event-link" href="event_details.jsp?id=${order.event.id}">
                        <img class="event-img" src="" alt="">
                    </a>
            
                    <div>
                        <div class="order-details">
                            <h3 class="order-header">${order.event.name}</h3>
                            <h4 class="event-date">Data evento: ${new Date(Date.parse(order.event.date)).toLocaleDateString("it-IT", {day:"2-digit", month:"2-digit", year:"numeric"})}</h4>
                            <p class="order-date">Data ordine: ${new Date(Date.parse(order.date)).toLocaleDateString("it-IT", {day:"2-digit", month:"2-digit", year:"numeric"})}</p>
                        </div>
                
                        <div class="ticket-data">
                            <span>Numero biglietti: ${order.tickets} </span>
                            
                            <span class="price">€ <span>${(parseFloat(order.price) * parseInt(order.tickets)).toFixed(2)}</span></span>
                
                        </div>
                    </div>

                    <img class="ticket-qr" src="https://chart.googleapis.com/chart?cht=qr&chs=450x450&chl=${user}+${order.event.id}+${order.event.date}" alt="">
    
                </div>`;
    };
    
    //caricamento tag e ultimi ordini
    let loadPanoramica = () =>{
        $.ajax({
            url: "LastOrdersController",

            dataType: "json",

            success: (orders) =>{

                if(orders.length == 0){
                    $("#last-orders>div").append(`<p id='no-order'>
                                                Non hai nessun ordine, 
                                                per acquistare un biglietto 
                                                <a href='eventi.jsp'>clicca qui</a>
                                            </p>`);
                } else {

                    $.each(orders, function(key, order) {
                        $("#last-orders>div").append(orderToHTML(order));
                    });
                }
            },

            error: () =>{
                alert("Errore caricamento ordini");
            }
        });

        $.ajax({
            url: 'TagUserController',

            success: function(data) {
                let userLikes = [];

                
                $.each(data, function(key, val) {
                    let el = {"key": val.id, "value": val.name};

                    $("#likes>div").append(tagsToHtml(el));
                });
        
            },

            error: () =>{
                alert("Errore caricamento tag");
            }
        });
        
    };

    loadPanoramica();
});