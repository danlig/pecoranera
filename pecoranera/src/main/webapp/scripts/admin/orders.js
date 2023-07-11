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
    }
    
    let loadUserOrder = async function(offsetPage){
        return $.ajax({
            url: "order/list",   
    
            success: (orders) => {
                $.each(orders, function(key, order) {
                    $("#orders>div").append(orderToHTML(order));
                });
                
            }
        });
    }
    
    loadUserOrder();
})