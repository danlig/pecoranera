package dao;

import java.util.List;

import model.Artist;

public class ArtistDao {
	private static BasicCrudDao<Artist> crud = new BasicCrudDao<>(Artist.class);
	
	public static void doSave(Artist item) {
		crud.doSave(item);
	}
	
	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}
	
    public static Artist doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
    }
    
    public static List<Artist> doRetrieveAll() {
		return crud.doRetrieveAll();
    }
}
