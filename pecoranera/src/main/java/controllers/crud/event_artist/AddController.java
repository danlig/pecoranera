package controllers.crud.event_artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ArtistDao;
import dao.EventDao;
import model.Artist;
import model.Event;

public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		String id_event = (String) request.getParameter("id_event");
		String id_artist = (String) request.getParameter("id_artist");
		String role = (String) request.getParameter("role");
		
		Event event = null;
		Artist artist = null;
		
		
		try {
			event = EventDao.doRetrieveByKey(Integer.parseInt(id_event));
			if (event == null) {
				messages.add("Inserire l'evento");
			} 
		} catch (NumberFormatException e) {
			messages.add("Id Event Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;			
		}
		
		try {
			artist = ArtistDao.doRetrieveByKey(Integer.parseInt(id_artist));
			if (artist == null) {
				messages.add("Inserire l'artista");
			} 
		} catch (NumberFormatException e) {
			messages.add("Id Artist Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;			
		}
			
		if (role == null || role.trim().equals("")) {
			messages.add("Inserire il ruolo");
		} 

		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
			return ;
		}

		System.out.println("Event: " + event +"\n");
		System.out.println("Artist: " + artist +"\n");
		System.out.println("Role: " + role +"\n\n");
		EventDao.addArtist(event, artist, role);
		response.sendRedirect("list?id_event=" + id_event);
	}

}
