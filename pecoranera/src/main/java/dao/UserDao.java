package dao;

import java.util.List;

import model.User;

public class UserDao {
	private static BasicCrudDao<User> crud = new BasicCrudDao<>(User.class);

	public static User doSave(User item) {
		return crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static User doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<User> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
	
	public static User doRetrieveByEmail(String email) {
		return crud.findItemByField("email", email);
	}
}
