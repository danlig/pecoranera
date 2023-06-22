package model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import model.keys.EventArtistKey;

import jakarta.persistence.EmbeddedId;

@Entity
@Table(name="event_artist")
public class EventArtist {
	@EmbeddedId
	private EventArtistKey id;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id_event")
	private Event event;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id_artist")
	private Artist artist;
	
	private String role;

	public EventArtist(Event event, Artist artist, String role) {
		super();
		this.id = new EventArtistKey(event.getId(), artist.getId());
		this.event = event;
		this.artist = artist;
		this.role = role;
	}

	public EventArtist() {
		super();
	}

	public EventArtistKey getId() {
		return id;
	}

	public void setId(EventArtistKey id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
}