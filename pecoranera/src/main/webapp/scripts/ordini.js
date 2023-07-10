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
                
                            <button value="${order.id}" class="remove-order">Cancella Ordine</button>
                        </div>
                    </div>

                    <img class="ticket-qr" src="https://chart.googleapis.com/chart?cht=qr&chs=450x450&chl=${user}+${order.event.id}+${order.event.date}" alt="">
    
                </div>`;
    }

    let loadUserOrder = async function(offsetPage){
        return $.ajax({
            url: "OrderRetrieveController",

            

            success: (orders) => {
                if(offsetPage == 0){
                    $("#order-list").children(".order").remove();
                }

                if(orders.length == 0){
                    $("#load-more").hide();
                } else {
                    $.each(orders, function(key, order) {
                        $("#order-list").append(orderToHTML(order));
                    });
                }
                
            }
        });
    }

    $("#order-list").on("click", ".remove-order", function(){
        let id = $(this).attr("value");

        $.ajax({
            url: "OrderDeleteController",

            

            data:{
                idOrder: id
            },

            success: function(){
                $(`#${id}`).remove();
            },

            error: () =>{
                alert("Errore con l'eliminazione dell'ordine");
            }
        });
    });

    loadUserOrder(0);


});