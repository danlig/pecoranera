package dao;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import utils.HibernateUtils;

class BasicCrudDao<T> {
	private Class<T> cls;
	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

	public BasicCrudDao(Class<T> cls) {
		this.cls = cls;
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
	
	public void doSave(T item) {
		executeTransaction(session -> {
			session.merge(item);
			return null;
		});
	}

	public void doDelete(T item) {
		executeTransaction(session -> {
			session.remove(item);
			return null;
		});
	}

	public void doDeleteByKey(int id) {
		T item = doRetrieveByKey(id);
		doDelete(item);
	}

	public T doRetrieveByKey(int id) {
		return executeTransaction(session -> session.get(cls, id));
	}

	public List<T> doRetrieveAll() {
		return executeTransaction(session -> {
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(cls);
			criteriaQuery.from(cls);

			return session.createQuery(criteriaQuery).getResultList();
		});
	}
}
