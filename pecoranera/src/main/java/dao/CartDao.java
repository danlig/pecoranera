package dao;

import java.util.List;

import model.Cart;
import model.CartEvent;
import model.Event;

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

	public static void addEvent(Cart cart, Event event, int tickets) {
		BasicCrudDao<CartEvent> crudCE = new BasicCrudDao<>(CartEvent.class);
		
		CartEvent cart_event = new CartEvent();
		cart_event.setEvent(event);
		cart_event.setCart(cart);
		cart_event.setTickets(tickets);
		
		crudCE.doSave(cart_event);
	}
}
