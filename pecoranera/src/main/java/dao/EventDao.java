package dao;

import java.util.List;

import model.Artist;
import model.Event;
import model.EventArtist;
import model.keys.EventArtistKey;

public class EventDao {
	private static BasicCrudDao<Event> crud = new BasicCrudDao<>(Event.class);

	public static Event doSave(Event item) {
		return crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Event doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Event> doRetrieveAll() {
		return crud.doRetrieveAll();
	}

	public static void addArtist(Event event, Artist artist, String role) {
		BasicCrudDao<EventArtist> crudEA = new BasicCrudDao<>(EventArtist.class);
		
		EventArtist event_artist = new EventArtist();
		event_artist.setId(new EventArtistKey(event, artist));
		event_artist.setEvent(event);
		event_artist.setArtist(artist);
		event_artist.setRole(role);
		
		crudEA.doSave(event_artist);
	}
}
