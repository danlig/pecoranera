package dao;

import java.util.Date;
import java.util.List;

import model.Order;
import model.Event;

public class OrderDao {
	private static BasicCrudDao<Order> crud = new BasicCrudDao<>(Order.class);

	private OrderDao() {
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean doSave(Order item) {
		Event event = item.getEvent();
		
		// Controllo massimo tickets
		int availableTickets = event.getAvailableTickets();
		
		if (item.getTickets() > availableTickets) return false;
		if (item.getTickets() < 1) return false;
		
		// Salva ordine
		crud.doSave(item);
		
		// Decrementa biglietti disponibili
		event.setAvailableTickets(availableTickets - item.getTickets());
		EventDao.doSave(event);
		
		return true;
	}
	
	public static boolean doEdit(Order item, int tickets) {
		int ticketsOld = item.getTickets();
		
		aumentaBiglietti(item);

		item.setDate(new Date());
        item.setTickets(tickets);
        
        boolean success = doSave(item);
        
        if (success == false) {
        	// Restore del numero dei biglietti in caso di errore
        	item.setTickets(ticketsOld * (- 1));
        	aumentaBiglietti(item);
        }
        
        return success;
	}

	private static void aumentaBiglietti(Order order) {
		// Aumenta biglietti disponibili
		Event event = order.getEvent();
		
		event.setAvailableTickets(event.getAvailableTickets() + order.getTickets());
		EventDao.doSave(event); 
	}
	
	public static void doDeleteByKey(int id) {
		aumentaBiglietti(doRetrieveByKey(id));
		crud.doDeleteByKey(id);		
	}

	public static Order doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Order> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
}
