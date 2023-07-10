package model.keys;

import java.io.Serializable;
import java.util.Objects;

import model.Event;
import model.Artist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EventArtistKey implements Serializable {
	private static final long serialVersionUID = -1171562021421463032L;

	@Column(name = "id_event")
	private int idEvent;

	@Column(name = "id_artist")
	private int idArtist;

	public EventArtistKey(int idEvent, int idArtist) {
		super();
		this.idEvent = idEvent;
		this.idArtist = idArtist;
	}
	
	public EventArtistKey(Event event, Artist artist) {
		super();
		this.idEvent = event.getId();
		this.idArtist = artist.getId();
	}

	public EventArtistKey() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArtist, idEvent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventArtistKey other = (EventArtistKey) obj;
		return idArtist == other.idArtist && idEvent == other.idEvent;
	}
}
