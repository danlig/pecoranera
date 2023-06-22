package dao;

import java.util.List;

import model.Tag;

public class TagDao {
	private static BasicCrudDao<Tag> crud = new BasicCrudDao<>(Tag.class);

	public static void doSave(Tag item) {
		crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Tag doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Tag> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
}
