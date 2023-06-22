package model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private int id;
	
	@OneToOne(mappedBy = "user")
	private Cart cart;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Order> orders = new HashSet<>();
	
    @ManyToMany(cascade = { CascadeType.MERGE }, fetch=FetchType.EAGER)
    @JoinTable(
        name = "user_tag", 
        joinColumns = { @JoinColumn(name = "id_user") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_tag") }
    )
    private Set<Tag> tags = new HashSet<>();
	
	@Column(name="is_admin")
	private boolean isAdmin;
	
	private String email;
	private String username;
	private String password;

	public User(Set<Tag> tags, boolean isAdmin, String email, String username, String password) {
		super();
		this.tags = tags;
		this.isAdmin = isAdmin;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", isAdmin=" + isAdmin + ", email=" + email + ", username="
				+ username + ", password=" + password + "]";
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
