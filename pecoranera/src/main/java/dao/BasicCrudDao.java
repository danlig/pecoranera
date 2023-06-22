package dao;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;

class BasicCrudDao<T> {
	private Class<T> cls;
	private SessionFactory sessionFactory;
	
	public BasicCrudDao(Class<T> cls) {		
		this.cls = cls;
	}
	
	public void init(SessionFactory sessionFactory) {
		if (sessionFactory != null) {
			this.sessionFactory = sessionFactory;	
		} else {
			throw new NullPointerException();	
		}
	}
	
    private <R> R executeTransaction(Function<Session, R> operation) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        R result = null;
        
        session.clear();

        try {
            transaction = session.beginTransaction();
            result = operation.apply(session);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    void doSave(T item) {
        executeTransaction(session -> {
            session.merge(item);
            return null;
        });
    }

    private void doDelete(T item) {
        executeTransaction(session -> {
            session.remove(item);
            return null;
        });
    }
    
    void doDeleteByKey(int id) {
    	T item = doRetrieveByKey(id);
    	doDelete(item);
    }

    T doRetrieveByKey(int id) {
        return executeTransaction(session -> session.get(cls, id));
    }

    List<T> doRetrieveAll() {
        return executeTransaction(session -> {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(cls);
            criteriaQuery.from(cls);

            return session.createQuery(criteriaQuery).getResultList();
        });
    }
}

