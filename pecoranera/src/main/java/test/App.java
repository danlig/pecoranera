package test;


import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import dao.ArtistDao;
import dao.CartDao;
import dao.EventDao;
import dao.OrderDao;
import dao.ProductTypeDao;
import dao.ProductDao;
import dao.TagDao;
import dao.UserDao;
import model.Artist;
import model.User;
import model.Cart;
import model.Event;
import model.ProductType;
import model.Product;
import model.Tag;
import model.Order;

public class App {

    public static void test() {
    	// -----------------------------
    	// TEST ARTIST
    	///*Test save 
    	Artist a = new Artist("dklasklnld", "desc1");
    	ArtistDao.doSave(a); //*/
    	
    	/* Test delete
    	ArtistDao.doDeleteByKey(8); //*/
    	
    	/* Test retrievebykey
    	System.out.println(ArtistDao.doRetrieveByKey(7).getDescription()); //*/
    	
    	/* Test retrieveall
    	for (Artist a : ArtistDao.doRetrieveAll()) {
    		System.out.println(a.getDescription());
    	} //*/
    	
    	// -----------------------------
    	// TEST PRODUCTTYPE
    	/*Test save 
    	ProductType pt = new ProductType("secondi");
    	ProductTypeDao.doSave(pt); //*/
    	
    	/* Test delete
    	ProductTypeDao.doDeleteByKey(3); //*/
    	
    	/* Test retrievebykey
    	ProductType pt = ProductTypeDao.doRetrieveByKey(3);
    	System.out.println(pt.toString());
    	for (Product p : pt.getProducts()) {
    		System.out.println(p.toString());
    	}
    	//*/
    	
    	/* Test retrieveall
    	for (ProductType a : ProductTypeDao.doRetrieveAll()) {
    		System.out.println(a.toString());
    	} //*/
    	
    	// -----------------------------
    	// TEST PRODUCT
    	/*Test save 
    	ProductType pt = new ProductType("bo");
    	pt.setId(2);
    	
    	Product p = new Product(pt, "Salsicciona", "buona", 10);
    	ProductDao.doSave(p);
    	//*/
    	
    	/* Test delete
    	ProductDao.doDeleteByKey(1); //*/
    	
    	/* Test retrievebykey
    	System.out.println(ProductDao.doRetrieveByKey(3).toString()); //*/
    	
    	/* Test retrieveall
    	for (Product a : ProductDao.doRetrieveAll()) {
    		System.out.println(a.toString());
    		System.out.println(a.getType().toString());
    	} //*/
    	
    	// -----------------------------
    	// TEST TAG
    	/*Test save 
    	Tag a = new Tag("tag1");
    	TagDao.doSave(a); //*/
    	
    	// -----------------------------
    	// TEST EVENT
    	/*Test save     	
    	Set<Tag> tags = new HashSet<>();
    	
    	Tag t = new Tag("ajskdlasd");
       	Tag t1 = new Tag("jasdjla");

    	tags.add(t);
    	tags.add(t1);
    	
    	Event e = new Event(tags, 10, 100, new Date(), "PROVOLA", "skadlk", 60, new Date());
    	EventDao.doSave(e); //*/
    	
    	/* Test retrievebykey
    	Event e = EventDao.doRetrieveByKey(16);
    	System.out.println(e.toString());
    	for (Tag p : e.getTags()) {
    		System.out.println(p.toString());
    	}
    	//*/
    	
    	/* Test delete
    	EventDao.doDeleteByKey(12); //*/
    	
    	/* Test add artist
    	Artist a = ArtistDao.doRetrieveByKey(10);
    	
    	EventDao.addArtist(e, a, "batteria");; 
    	
    	//*/
    	
    	// -----------------------------
    	// TEST USER
    	/*Test save 
    	
    	Set<Tag> tags = new HashSet<>();
    	
    	Tag t = new Tag("tag4user22");
       	Tag t1 = new Tag("tag4user23");

    	tags.add(t);
    	tags.add(t1);
    	
    	UserDao.doSave(new User(tags, true, "email3", "user2", "password")); //*/
    	
    	/* Test retrievebykey   
    	User e = UserDao.doRetrieveByKey(5);
    	
    	System.out.println(e.toString());
    	for (Tag p : e.getTags()) {
    		System.out.println(p.toString());
    	}
    	
    	for (Order o : e.getOrders()) {
    		System.out.println(o.toString());
    	}
    	//*/
    	
    	/* Test delete
    	UserDao.doDeleteByKey(9); //*/
    	
    	//*/
    	
    	// -----------------------------
    	// TEST ORDER
    	/*Test save 
    	
    	User u = UserDao.doRetrieveByKey(2);
    	Event e = EventDao.doRetrieveByKey(11);
    	
    	Order order = new Order(10, 60, new Date(), u, e);
    	OrderDao.doSave(order);
    	
    	//*/
    	
    	/* Test retrievebykey
    	Order o = OrderDao.doRetrieveByKey(4);
    	System.out.println(o.toString());
    	
    	System.out.println(o.getEvent().toString());
    	System.out.println(o.getUser().toString());;
    	//*/
    	
    	/* Test delete
    	OrderDao.doDeleteByKey(3); //*/	
    	
    	// -----------------------------
    	// TEST CART
    	/*Test save
    	User u = new User(new HashSet<>(), false, "emaisld", "dklajsk", "djaksljdlk");
    	Cart a = new Cart(u);
    	CartDao.doSave(a); //*/
    	
    	/* Test delete
    	CartDao.doDeleteByKey(2); //*/
    	
    	/* Test retrievebykey
    	Cart c = CartDao.doRetrieveByKey(1);
    	System.out.println(c.toString());
    	System.out.println(c.getUser().toString());*/
    	
    	/* Test add cart event
    	Event e = EventDao.doRetrieveByKey(11);
    	CartDao.addEvent(CartDao.doRetrieveByKey(3), e, 1);
    	//*/
    }
}
