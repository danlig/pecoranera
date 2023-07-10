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
    	
    	System.out.println("Controllo Database...");
    	if (EventDao.doRetrieveAll().isEmpty()) {
    		System.out.println("Init Database...");
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		SeedDatabase.seed();
    	} 

    	System.out.println("Database Pronto");
    }
	
}
