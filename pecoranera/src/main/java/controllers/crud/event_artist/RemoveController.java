package controllers.crud.event_artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ArtistDao;
import dao.EventDao;
import model.Artist;
import model.Event;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		String id_event = (String) request.getParameter("id_event");
		String id_artist = (String) request.getParameter("id_artist");
		
		Event event = null;
		Artist artist = null;
		
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
		
		try {
			artist = ArtistDao.doRetrieveByKey(Integer.parseInt(id_artist));
			if (artist == null) {
				messages.add("Artista non trovato");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
				return;
			} 
		} catch (NumberFormatException e) {
			messages.add("Id Artist Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;			
		}
		
		
		EventDao.deleteArtist(event, artist);
		response.sendRedirect("list?id_event=" + id_event);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
