package seed;

import model.User;
import model.Cart;
import dao.CartDao;
import dao.UserDao;

public class SeedDatabase {

	public static void main(String[] args) {
		User admin = new User();
		
		admin.setAdmin(true);
		
		admin.setId(1);
		admin.setUsername("admin");
		admin.setEmail("admin@pecoranera.it");
		admin.setPassword("password");
	
		UserDao.doSave(admin);

		Cart cart = new Cart();
		cart.setUser(UserDao.doRetrieveByEmail("admin@pecoranera.it"));
		CartDao.doSave(cart);
	}

}
