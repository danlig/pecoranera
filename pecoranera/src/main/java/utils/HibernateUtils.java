package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final SessionFactory sessionFactory;
	private static final Logger LOGGER = LogManager.getLogger(HibernateUtils.class);
	
	private HibernateUtils() {
		throw new IllegalStateException("Utility class");
	}
	
    static {
        try {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
        	LOGGER.error("Error occurred in context", ex);
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
