package utils;

import model.Artist;
import model.Cart;
import model.Event;
import model.Product;
import model.Tag;
import model.User;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import model.ProductType;

import com.github.javafaker.Faker;

import dao.ArtistDao;
import dao.CartDao;
import dao.EventDao;
import dao.ProductDao;
import dao.ProductTypeDao;
import dao.TagDao;
import dao.UserDao;
	
public class SeedDatabase {
	private static final Faker faker = new Faker();
	
	private static final int artist_number = 10;
	private static final String[] product_type_names = {"Birre", "Panini", "Special"};
	private static final int product_number = 5;
	private static final String[] tag_names = {"Blues", "Funk", "Jazz Classic", "Swing", "Soul", "Bebop"};
	private static final int event_number = 10;
	private static final int event_artist_number = 3;
	private static SecureRandom random = new SecureRandom();
	
	private SeedDatabase() {
		throw new IllegalStateException("Utility class");
	}
	
	private static double generateRandomPrice(double min, double max) {
		DecimalFormat df2 = new DecimalFormat("0.0");
	    double rand = random.nextDouble();
	    return Double.parseDouble(df2.format(min + (rand * (max - min))));
	}
	
	private static void seedTag() {
		for (String tag_name : tag_names) {
			Tag tag = new Tag();
			tag.setName(tag_name);
			TagDao.doSave(tag);
		}
	}
	
	private static void seedArtist() {
		for (int i = 0; i < artist_number; i++) {
			Artist artist = new Artist();
			artist.setName(faker.name().fullName());
			artist.setDescription(faker.lorem().sentence(10, 6));
			ArtistDao.doSave(artist);
		}
	}
	
	
	
	private static void seedProduct() {
		ProductType beer_type = new ProductType();
		beer_type.setName(product_type_names[0]);
		beer_type = ProductTypeDao.doSave(beer_type);
		
		for (int i = 0; i < product_number; i++) {
			Product product = new Product();
			product.setName(faker.beer().name());
			product.setDescription(
					"Luppolo: " + faker.beer().hop() + 
					" - Malto: " + faker.beer().malt() + 
					" - Style: " + faker.beer().style() + 
					" - Gradazione (%): " + faker.number().numberBetween(3, 15)
					);
			product.setPrice(generateRandomPrice(3.00, 6.00));
			product.setType(beer_type);
			ProductDao.doSave(product);
		}

		ProductType burger_type = new ProductType();
		burger_type.setName(product_type_names[1]);
		burger_type = ProductTypeDao.doSave(burger_type);
	
		for (int i = 0; i < product_number; i++) {
			Product product = new Product();
			product.setName(faker.music().instrument() + " Burger");
			product.setDescription(faker.lorem().sentence(10, 6));			
			product.setPrice(generateRandomPrice(7.00, 12.00));
			product.setType(burger_type);
			ProductDao.doSave(product);
		}
		
		ProductType special_type = new ProductType();
		special_type.setName(product_type_names[2]);
		special_type = ProductTypeDao.doSave(special_type);
	
		for (int i = 0; i < product_number; i++) {
			Product product = new Product();
			product.setName(faker.food().dish());
			product.setDescription(faker.lorem().sentence(10, 6));			
			product.setPrice(generateRandomPrice(2.00, 20.00));
			product.setType(special_type);
			ProductDao.doSave(product);
		}
	}
	
	private static void seedEvent() {
		for (int i = 0 ; i < event_number; i++) {
			Event event = new Event();
			event.setName(faker.zelda().character() + " " + faker.color().name());
			event.setDescription(faker.lorem().sentence(10, 100));
			if (i < 1) 
				event.setDate(faker.date().past(90, TimeUnit.DAYS));
			else
				event.setDate(faker.date().future(90, TimeUnit.DAYS));
			event.setPrice(generateRandomPrice(2.00, 20.00));
			event.setCancellation(null);
			
			List<Tag> tags = TagDao.doRetrieveAll();
			Collections.shuffle(tags);
			
			event.setTags(new HashSet<Tag>(tags.stream().limit(faker.number().numberBetween(1, 3)).toList()));
					
			int max_tickets = faker.number().numberBetween(50, 100);
			event.setMaxTickets(max_tickets);
			event.setAvailableTickets(max_tickets);
			
			event = EventDao.doSave(event);
			
			List<Artist> artists = ArtistDao.doRetrieveAll();
			Collections.shuffle(artists);
			
			for (Artist artist : artists.stream().limit(faker.number().numberBetween(1, event_artist_number)).toList()) {
				EventDao.addArtist(event, artist, faker.music().instrument());
			}
		}
	}
	
	private static void seedUser() {
		/**
		 * Username: admin
		 * Email: admin@pecoranera.it
		 * Password: Password@10
		 */
		User admin = new User();
		admin.setEmail("admin@pecoranera.it");
		admin.setUsername("admin");
		admin.setPassword(LoginUtils.toHash("Password@10"));
		admin.setAdmin(true);
		UserDao.doSave(admin);
		
		/**
		 * Username: RickSanchez
		 * Email: ricksancez@gmail.com
		 * Password: Morty_Rick_102
		 */
		User user = new User();
		user.setEmail("ricksancez@gmail.com");
		user.setUsername("RickSanchez");
		user.setPassword(LoginUtils.toHash("Morty_Rick_102"));
		user.setAdmin(false);
		
		UserDao.doSave(user);
		user = UserDao.doRetrieveByEmail("ricksancez@gmail.com");
		
		// Crea carrello
		Cart cart = new Cart();
		cart.setUser(user);
		CartDao.doSave(cart);
		
	}
	
	public static void seed() {
		seedArtist();
		seedUser();
		seedProduct();
		seedTag();
		seedEvent();
	}
}
