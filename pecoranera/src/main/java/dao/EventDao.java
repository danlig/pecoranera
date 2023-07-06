package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Artist;
import model.Event;
import model.EventArtist;
import model.Tag;
import utils.HibernateUtils;

public class EventDao {
	private static BasicCrudDao<Event> crud = new BasicCrudDao<>(Event.class);

	public static void doSave(Event item) {
		crud.doSave(item);
	}

	public static void doDeleteByKey(int id) {
		crud.doDeleteByKey(id);
	}

	public static Event doRetrieveByKey(int id) {
		return crud.doRetrieveByKey(id);
	}

	public static List<Event> doRetrieveAll() {
		return crud.doRetrieveAll();
	}

	public static void addArtist(Event event, Artist artist, String role) {
		BasicCrudDao<EventArtist> crudEA = new BasicCrudDao<>(EventArtist.class);
		
		EventArtist event_artist = new EventArtist();
		event_artist.setEvent(event);
		event_artist.setArtist(artist);
		event_artist.setRole(role);
		
		crudEA.doSave(event_artist);
	}
	
	public static List<Event> doRetrieveFilter(String titolo){
		return doRetrieveFilter(titolo, null, null, null);
	}
	
	public static List<Event> doRetrieveFilter(String titolo, Date dataInizio, Date dataFine, List<Tag> tags) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		List<Event> resultList = null;

		try {
		    tx = session.beginTransaction();
		    
		    // Create the CriteriaBuilder and CriteriaQuery
		    HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Event> criteriaQuery = builder.createQuery(Event.class);
		    Root<Event> root = criteriaQuery.from(Event.class);
		    
		    // Define the condition for the attribute "name" using the LIKE operator
		    criteriaQuery.where(builder.like(root.get("name"), "%" + titolo + "%"));
		    
		    if (dataInizio != null && dataFine != null)
			    // Define the query and get the result list
			    criteriaQuery.where(builder.between(root.get("date"), dataInizio, dataFine));
		    
		    // Execute the query and get the result list
		    resultList = session.createQuery(criteriaQuery).getResultList();
		    
		    tx.commit();
		} catch (Exception e) {
		    if (tx != null) {
		        tx.rollback();
		    }
		    e.printStackTrace();
		} finally {
		    session.close();
		}

		return resultList;
	}
}
