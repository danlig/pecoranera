package model.keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CartEventKey implements Serializable {
	private static final long serialVersionUID = -3889855546291339742L;
	
	@Column(name="id_event")
	private int idEvent;
	
	@Column(name="id_cart")
	private int idCart;
	
	public CartEventKey(int idEvent, int idCart) {
		super();
		this.idEvent = idEvent;
		this.idCart = idCart;
	}

	public CartEventKey() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCart, idEvent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartEventKey other = (CartEventKey) obj;
		return idCart == other.idCart && idEvent == other.idEvent;
	}
}
