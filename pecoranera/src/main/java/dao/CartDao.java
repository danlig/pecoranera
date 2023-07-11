package dao;

import java.util.List;
import java.util.Set;

import model.Cart;
import model.CartEvent;
import model.Event;
import model.keys.CartEventKey;

public class CartDao {
	private static BasicCrudDao<Cart> crud = new BasicCrudDao<>(Cart.class);

	private CartDao() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void doSave(Cart item) {
		crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Cart doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Cart> doRetrieveAll() {
		return crud.doRetrieveAll();
	}

	public static void addEvent(Cart cart, Event event, int tickets, boolean edit) {
		BasicCrudDao<CartEvent> crudCE = new BasicCrudDao<>(CartEvent.class);		
		crudCE.doSave(getCartEvent(cart, event, tickets, edit));
	}
	
	public static CartEvent getCartEvent(Cart cart, Event event, int tickets, boolean edit) {
		// Inizializza id della entry
		CartEventKey id = new CartEventKey(event.getId(), cart.getId());		
		
		Set<CartEvent> ces = cart.getCartEvents();
		CartEvent cartEvent = new CartEvent();
		
		// Se già esiste un CartEvent con lo stesso id, modificalo
		for (CartEvent ce : ces) {
			if (ce.getId().equals(id)) {
				
				// Somma tickets con quelli esistenti?
				if (!edit)
					tickets += ce.getTickets();
				
				
				cartEvent = ce;
				break;
			}
		}

		cartEvent.setEvent(event);
		cartEvent.setCart(cart);
		cartEvent.setTickets(tickets);
		cartEvent.setId(id);
		
		return cartEvent;
	}
	
	public static void removeEvent(Cart cart, Event event) {
		BasicCrudDao<CartEvent> crudCE = new BasicCrudDao<>(CartEvent.class);
		
		CartEvent cart_event = new CartEvent();
		cart_event.setId(new CartEventKey(event.getId(), cart.getId()));	
		
		crudCE.doDelete(cart_event);
	}
}
