package dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaPredicate;
import jakarta.persistence.criteria.Join;

import jakarta.persistence.criteria.Root;
import model.Artist;
import model.Event;
import model.EventArtist;

import model.keys.EventArtistKey;
import utils.HibernateUtils;

import model.Tag;


public class EventDao {
	private static BasicCrudDao<Event> crud = new BasicCrudDao<>(Event.class);

	public static Event doSave(Event item) {
		return crud.doSave(item);
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
		event_artist.setId(new EventArtistKey(event, artist));
		event_artist.setEvent(event);
		event_artist.setArtist(artist);
		event_artist.setRole(role);
		
		crudEA.doSave(event_artist);
	}
		
	public static void deleteArtist(Event event, Artist artist) {
		Session session = null;
        Transaction transaction = null;
        
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            EventArtistKey eventArtistKey = new EventArtistKey(event.getId(), artist.getId());
            EventArtist eventArtist = session.get(EventArtist.class, eventArtistKey);
            
            if (eventArtist != null) {
                session.remove(eventArtist);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
	
	public static List<Event> doRetrieveAllActiveEvent() {
	    Session session = null;
	    try {
	        session = HibernateUtils.getSessionFactory().openSession();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
	        Root<Event> root = criteriaQuery.from(Event.class);

	        Predicate cancellationDateNotNullPredicate = criteriaBuilder.isNull(root.get("cancellation"));
	        criteriaQuery.where(cancellationDateNotNullPredicate);

	        return session.createQuery(criteriaQuery).getResultList();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
	
	public static List<Event> doRetrieveFilter(String titolo, Date dataInizio, Date dataFine, Set<Integer> tags, int pageSize, int offset) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		List<Event> resultList = null;

		try {
		    tx = session.beginTransaction();
		    
		    // Create the CriteriaBuilder and CriteriaQuery
		    HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Event> criteriaQuery = builder.createQuery(Event.class);
		    Root<Event> root = criteriaQuery.from(Event.class);
		    
		    JpaPredicate condition1 = builder.isTrue(builder.literal(true)), 
		    			 condition2 = builder.isTrue(builder.literal(true)),
    					 condition3 = builder.isTrue(builder.literal(true));
		    
		    if (titolo != null)
		    	condition1 = builder.like(root.get("name"), "%" + titolo + "%");
		    
		    if (dataInizio != null && dataFine != null)
		    	condition2 = builder.between(root.get("date"), dataInizio, dataFine);

		    if (tags != null && !tags.isEmpty()) {
		    	Join<Event, Tag> tagJoin = root.join("tags");
		    	condition3 = builder.in(tagJoin.get("id"), tags);
		    }
		    
		    criteriaQuery.where(builder.and(condition1, condition2, condition3, 
		    		builder.isNull(root.get("cancellation"))));
		    
		    criteriaQuery.orderBy(builder.asc(root.get("date")));
		    
		    // Execute the query and get the result list
		    resultList = session.createQuery(criteriaQuery)  
		    		.setFirstResult(offset)
				    .setMaxResults(pageSize)
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
		
		// Nascondi event-artists
		for (Event e : resultList) {
			e.setEventArtists(null);
		}
		
		return resultList;
	}
	
	public static List<Event> doRetrieveUpcomingPage(int pageSize, int offset){
		Session session = HibernateUtils.getSessionFactory().openSession();
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Event> query = builder.createQuery(Event.class);
		
		Root<Event> root = query.from(Event.class);
		query.select(root);
		query.where(
				    builder.and(
				        builder.greaterThan(root.get("date"), LocalDate.now()),
				        builder.isNull(root.get("cancellation"))
				    )
				);
		
		query.orderBy(builder.asc(root.get("date")));
		
		List<Event> upcomingEvents = session.createQuery(query)
			    .setFirstResult(offset)
			    .setMaxResults(pageSize)
			    .getResultList();

		session.close();
		
		// Nascondi event-artists
		for (Event e : upcomingEvents) {
			e.setEventArtists(null);
		}
		
		return upcomingEvents;
	}
}
