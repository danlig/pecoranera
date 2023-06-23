package utils.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static final SessionFactory sessionFactory;
	
    static {
        try {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static boolean closeSessionFactory(SessionFactory sessionFactory) {
    	try {
    		sessionFactory.close();
    		return true;
    	} catch (Throwable ex) {
    		return false;
    	}
    }
}
