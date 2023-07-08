package dao;

import java.util.List;

import model.Order;
import model.Event;

public class OrderDao {
	private static BasicCrudDao<Order> crud = new BasicCrudDao<>(Order.class);

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

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Order doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Order> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
}
