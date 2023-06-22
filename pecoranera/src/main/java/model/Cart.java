package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cart")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	private User user;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private Set<CartEvent> cartEvents = new HashSet<>();

	public Cart(User user) {
		super();
		this.user = user;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartEvent> getCartEvents() {
		return cartEvents;
	}

	public void setCartEvents(Set<CartEvent> cartEvents) {
		this.cartEvents = cartEvents;
	}

	public List<Event> getEvents() {
		return cartEvents.stream().map(ce -> ce.getEvent()).collect(Collectors.toList());
	}
}
