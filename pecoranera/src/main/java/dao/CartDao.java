package dao;

import java.util.List;

import model.Cart;
import model.CartEvent;
import model.Event;
import model.keys.CartEventKey;

public class CartDao {
	private static BasicCrudDao<Cart> crud = new BasicCrudDao<>(Cart.class);

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
		
		// Inizializza id della entry
		CartEventKey id = new CartEventKey(event.getId(), cart.getId());
			
		// Somma tickets con quelli esistenti?
		if (!edit) {
			for (CartEvent ce : cart.getCartEvents()) {
				if (ce.getId().equals(id)) {
					tickets += ce.getTickets();
					break;
				}
			}
		}

		CartEvent cart_event = new CartEvent();
		cart_event.setEvent(event);
		cart_event.setCart(cart);
		cart_event.setTickets(tickets);
		cart_event.setId(id);
		
		crudCE.doSave(cart_event);
	}
	
	public static void removeEvent(Cart cart, Event event) {
		BasicCrudDao<CartEvent> crudCE = new BasicCrudDao<>(CartEvent.class);
		
		CartEvent cart_event = new CartEvent();
		cart_event.setId(new CartEventKey(event.getId(), cart.getId()));	
		
		crudCE.doDelete(cart_event);
	}
}
