package controllers.crud.event_artist;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ArtistDao;
import dao.EventDao;
import model.Event;

public class ReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		String id_event = (String) request.getParameter("id_event");
		
		Event event = null;
		
		try {
			event = EventDao.doRetrieveByKey(Integer.parseInt(id_event));
			if (event == null) {
				messages.add("Evento non trovato");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
				return ;
			} 
		} catch (NumberFormatException e) {
			messages.add("Id Event Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;			
		}
		
		request.setAttribute("event", event);
		request.setAttribute("artists", ArtistDao.doRetrieveAll());
		request.setAttribute("eventArtists", event.getEventArtists());

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/event-artist/page.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
