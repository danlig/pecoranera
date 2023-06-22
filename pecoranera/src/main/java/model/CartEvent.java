package model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import model.keys.CartEventKey;

@Entity
@Table(name = "cart_event")
public class CartEvent {
	@EmbeddedId
	private CartEventKey id;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id_event")
	private Event event;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id_cart")
	private Cart cart;

	private int tickets;

	public CartEvent(Event event, Cart cart, int tickets) {
		super();
		this.id = new CartEventKey(event.getId(), cart.getId());
		this.event = event;
		this.cart = cart;
		this.tickets = tickets;
	}

	public CartEvent() {
		super();
	}

	public CartEventKey getId() {
		return id;
	}

	public void setId(CartEventKey id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
}
