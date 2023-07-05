package model;

import java.util.Date;
import java.util.Set;

import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "event")
public class Event {
	public static final String UPLOAD_DIR = "event";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_event")
	private int id;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "event_tag", joinColumns = { @JoinColumn(name = "id_event") }, inverseJoinColumns = {
			@JoinColumn(name = "id_tag") })
	private Set<Tag> tags = new HashSet<>();

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private Set<EventArtist> eventArtists = new HashSet<>();

	@Column(name = "available_tickets")
	private int availableTickets;

	@Column(name = "max_tickets")
	private int maxTickets;

	private Date date;
	private String name;
	private String description;
	private double price;
	private Date cancellation;

	public Event() {
		super();
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", availableTickets=" + availableTickets + ", maxTickets=" + maxTickets + ", date="
				+ date + ", name=" + name + ", description=" + description + ", price=" + price + ", cancellation="
				+ cancellation + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	public int getMaxTickets() {
		return maxTickets;
	}

	public void setMaxTickets(int maxTickets) {
		this.maxTickets = maxTickets;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCancellation() {
		return cancellation;
	}

	public void setCancellation(Date cancellation) {
		this.cancellation = cancellation;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<EventArtist> getEventArtists() {
		return eventArtists;
	}

	public void setEventArtists(Set<EventArtist> eventArtists) {
		this.eventArtists = eventArtists;
	}
}
