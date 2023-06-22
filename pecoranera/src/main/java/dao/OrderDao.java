package dao;

import java.util.List;

import model.Order;

public class OrderDao {
	private static BasicCrudDao<Order> crud = new BasicCrudDao<>(Order.class);

	public static void doSave(Order item) {
		crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Order doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Order> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
}
