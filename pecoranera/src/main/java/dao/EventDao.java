package dao;

import java.util.List;

import model.Artist;
import model.Event;
import model.EventArtist;

public class EventDao {
	private static BasicCrudDao<Event> crud = new BasicCrudDao<>(Event.class);

	public static void doSave(Event item) {
		crud.doSave(item);
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
		crudEA.doSave(new EventArtist(event, artist, role));
	}
}
