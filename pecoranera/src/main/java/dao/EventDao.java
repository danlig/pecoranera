package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.Artist;
import model.Event;
import model.EventArtist;
import model.keys.EventArtistKey;
import utils.HibernateUtils;

public class EventDao {
	private static BasicCrudDao<Event> crud = new BasicCrudDao<>(Event.class);

	public static Event doSave(Event item) {
		return crud.doSave(item);
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
		event_artist.setId(new EventArtistKey(event.getId(), artist.getId()));
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
}
