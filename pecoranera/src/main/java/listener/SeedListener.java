package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.EventDao;
import utils.SeedDatabase;

@WebListener
public class SeedListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
    	// Se non sono presenti dati nel db, vengono generati
    	if (EventDao.doRetrieveAll().isEmpty()) {
    		SeedDatabase.seed();
    	} 
    }
	
}
