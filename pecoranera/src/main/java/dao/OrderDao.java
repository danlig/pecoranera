package dao;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaPredicate;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Order;
import model.User;
import utils.HibernateUtils;
import model.Event;

public class OrderDao {
	private static BasicCrudDao<Order> crud = new BasicCrudDao<>(Order.class);

	private OrderDao() {
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean doSave(Order item) {
		Event event = item.getEvent();
		
		// Controllo massimo tickets
		int availableTickets = event.getAvailableTickets();
		
		if (item.getTickets() > availableTickets) return false;
		if (item.getTickets() < 1) return false;
		
		// Salva ordine
		crud.doSave(item);
		
		// Decrementa biglietti disponibili
		event.setAvailableTickets(availableTickets - item.getTickets());
		EventDao.doSave(event);
		
		return true;
	}
	
	public static boolean doEdit(Order item, int tickets) {
		int ticketsOld = item.getTickets();
		
		aumentaBiglietti(item);

		item.setDate(new Date());
        item.setTickets(tickets);
        
        boolean success = doSave(item);
        
        if (success == false) {
        	// Restore del numero dei biglietti in caso di errore
        	item.setTickets(ticketsOld * (- 1));
        	aumentaBiglietti(item);
        }
        
        return success;
	}

	private static void aumentaBiglietti(Order order) {
		// Aumenta biglietti disponibili
		Event event = order.getEvent();
		
		event.setAvailableTickets(event.getAvailableTickets() + order.getTickets());
		EventDao.doSave(event); 
	}
	
	public static void doDeleteByKey(int id) {
		aumentaBiglietti(doRetrieveByKey(id));
		crud.doDeleteByKey(id);		
	}

	public static Order doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Order> doRetrieveAll() {
		return crud.doRetrieveAll();
	}
	
	public static List<Order> doRetrieveFilter(Date dataInizio, Date dataFine, User user) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		List<Order> resultList = null;

		try {
		    tx = session.beginTransaction();
		    
		    // Create the CriteriaBuilder and CriteriaQuery
		    HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
		    Root<Order> root = criteriaQuery.from(Order.class);
		    
		    JpaPredicate condition = builder.isTrue(builder.literal(true));
		    
		    if (dataInizio != null && dataFine != null)
		    	condition = builder.between(root.get("date"), dataInizio, dataFine);
		    
		    criteriaQuery.where(condition);
		    
		    criteriaQuery.orderBy(builder.asc(root.get("date")));
		    
		    // Execute the query and get the result list
		    resultList = session.createQuery(criteriaQuery)
				    .getResultList();
		    
		    tx.commit();
		} catch (Exception e) {
		    if (tx != null) {
		        tx.rollback();
		    }
		    e.printStackTrace();
		} finally {
		    session.close();
		}
		
		List<Order> orders = new ArrayList<>();

		for (Order o : resultList) {
			o.setUser(null);
			
			if (user == null || o.getUser().getId() == user.getId())
				orders.add(o);
		}
		
		return orders;
	}
}
