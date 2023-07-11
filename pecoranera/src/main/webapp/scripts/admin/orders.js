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
    
                </div>`;
    }
    
    let loadOrders = async function(){
        return $.ajax({
            url: "order/list",   

            dataType: "json",

            success: (orders) => {
                if(orders.length == 0){
                    $("#orders>div").append("<p>Nessun ordine da visualizzare</p>");
                }else {
                    $("#orders>div").children().remove();

                    $.each(orders, function(key, order) {
                        $("#orders>div").append(orderToHTML(order));
                    });
                }
                
            }
        });
    }

    let reloadOrders = async function(){
        return $.ajax({
            url: "order/list",   

            dataType: "json",

            data:{
                startDate: $("#startDate").val() || new Date().toLocaleDateString("en-CA", {year:"numeric",month:"2-digit", day:"2-digit"}).replace("/", "-"),
                endDate: $("#endDate").val() || "2200-12-31",
                idUser: $("#idUser").val() || null
            },

            success: (orders) => {
                $("#orders>div").children().remove();
                if(orders.length == 0){
                    $("#orders>div").append("<p>Nessun ordine da visualizzare</p>");
                }else {

                    $.each(orders, function(key, order) {
                        $("#orders>div").append(orderToHTML(order));
                    });
                }
                
            }
        });
    }

    $("#filters").on("submit", function(e){
        e.preventDefault();

        reloadOrders();
    });
    
    loadOrders();
})