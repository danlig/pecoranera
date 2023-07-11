package dao;

import java.util.List;

import model.Product;

public class ProductDao {
	private static BasicCrudDao<Product> crud = new BasicCrudDao<>(Product.class);

	private ProductDao() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void doSave(Product item) {
		crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Product doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Product> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
}