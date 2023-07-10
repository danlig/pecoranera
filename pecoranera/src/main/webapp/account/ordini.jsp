<section id="ordini" class="account-subpage">
    <div id="order-list">
        <div id="${ticket.id}" class="order">
            <img src="" alt="">
    
            <div class="order-details">
                <h3 class="order-header">${ticket.name}</h3>
                <h4 class="order-date">${new Date(Date.parse(ticket.date)).toLocaleDateString("it-IT", {day:"2-digit", month:"2-digit", year:"numeric"})}</h4>
    
                <div class="tag-list">
                    ${ticket.tags.map(tag => (`<span class="order-tag">${tag.name}</span>`) ).join("")}
                </div>
            </div>
    
            <div class="ticket-data">
                <div>
                    <label for="quantity">Quantità: </label>
                    <select name="quantity" class="quantity" data-order="${ticket.id}">
                        ${options}
                    </select>
                </div>
                
                <span class="price">€ <span>${parseFloat(ticket.price).toFixed(2)}</span></span>
    
                <button value="${ticket.id}" class="remove-order">Rimuovi</button>
            </div>
    
        </div>
    </div>
</section>