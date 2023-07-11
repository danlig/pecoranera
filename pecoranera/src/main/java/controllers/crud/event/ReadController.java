package controllers.crud.event;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import dao.ProductDao;
import dao.TagDao;
import model.Event;

public class ReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Event> events = EventDao.doRetrieveAll();

		// Nascondi event-artists
		for (Event e : events) {
			e.setEventArtists(null);
		}
		

		response.getWriter().write(new Gson().toJson(events));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
