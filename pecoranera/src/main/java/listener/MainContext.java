package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@WebListener
public class MainContext implements ServletContextListener {
	private SessionFactory sessionFactory;
	
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	// Inizializzazione di Hibernate        
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = registryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        
        // Imposta sessione di hibernate come variabile di contesto
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("SessionFactory", sessionFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {        
        if (sessionFactory != null)
        	sessionFactory.close();
    }
}
