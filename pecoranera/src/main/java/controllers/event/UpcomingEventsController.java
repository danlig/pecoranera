package controllers.event;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import model.Event;
import model.EventArtist;

/*
 * Restituisce gli eventi prossimi
 * (in input il numero di eventi da restituire e l'offset per simulare il numero pagina)
 * */

@WebServlet("/UpcomingEventsController")
public class UpcomingEventsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpcomingEventsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		int pageSize, offset;
		
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		catch(Exception e) {pageSize = 2; }
		
		
		try{
			offset =  Integer.parseInt(request.getParameter("offset"));
		}
		catch(Exception e){ offset = 0; }
		
		response.getWriter().write(new Gson().toJson(EventDao.doRetrieveUpcomingPage(pageSize, offset)));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}