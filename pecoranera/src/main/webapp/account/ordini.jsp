<section id="ordini" class="account-subpage">
    <%@ page contentType="text/html; charset=UTF-8" %>
    <script type="module" src="${pageContext.request.contextPath}/scripts/ordini.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordini.css">

    <div id="order-list">
        <div id="${ticket.id}" class="order">
            <img src="" alt="">
    
            <div class="order-details">
                <h3 class="order-header">{ticket.name}</h3>
                <h4 class="order-date">{new Date(Date.parse(ticket.date)).toLocaleDateString("it-IT", {day:"2-digit", month:"2-digit", year:"numeric"})}</h4>
            </div>
    
            <div class="ticket-data">
                <span> {ticket.tickets} </span>
                
                <span class="price">€ <span>{(parseFloat(ticket.price) * ticket.tickets).toFixed(2)}</span></span>
    
                <a href="event_details.jsp?id=${ticket.id}"></a>
                <button value="${ticket.id}" class="remove-order">Cancella Ordine</button>
            </div>
    
        </div>
    </div>

    
</section>