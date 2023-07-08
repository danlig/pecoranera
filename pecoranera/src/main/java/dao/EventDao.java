package dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaPredicate;

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
		    			 condition2 = builder.isTrue(builder.literal(true));;
		    
		    if (titolo != null)
		    	condition1 = builder.like(root.get("name"), "%" + titolo + "%");
		    
		    if (dataInizio != null && dataFine != null)
		    	condition2 = builder.between(root.get("date"), dataInizio, dataFine);

		    criteriaQuery.where(builder.and(condition1, condition2));
		    
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
		
		// Filtro tag
		if (tags != null && !tags.isEmpty()) {
			List<Event> clonedList = new ArrayList<>(resultList);
			
			for (Event e : resultList) {
				for (Integer t : tags) {
					if (!e.getTags().stream().filter(eventTag -> eventTag.getId() == t).findAny().isPresent()) {
						clonedList.remove(e);
						break;
					}		
				}
			}	
			
			return clonedList;
		}

		return resultList;
	}
	
	public static List<Event> doRetrieveUpcomingPage(int pageSize, int offset){
		Session session = HibernateUtils.getSessionFactory().openSession();
		HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Event> query = builder.createQuery(Event.class);
		
		Root<Event> root = query.from(Event.class);
		query.select(root);
		query.where(builder.greaterThan(root.get("date"), LocalDate.now()));
		
		/*
		 * query.where(
				    builder.and(
				        builder.greaterThan(root.get("date"), LocalDate.now()),
				        builder.isNotNull(root.get("cancellationDate"))
				    )
				);
		 * */
		
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
