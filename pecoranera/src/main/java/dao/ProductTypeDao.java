package dao;

import java.util.List;
import java.util.stream.Collectors;

import model.Product;
import model.ProductType;

public class ProductTypeDao {
	private static BasicCrudDao<ProductType> crud = new BasicCrudDao<>(ProductType.class);

	public static void doSave(ProductType item) {
		crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static ProductType doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<ProductType> doRetrieveAll() {
		return crud.doRetrieveAll();
	}

	public static List<Product> doRetrieveAllProducts(ProductType item) {
		return ProductDao.doRetrieveAll().stream().filter(p -> p.getType().getId() == item.getId())
				.collect(Collectors.toList());
	}
}
