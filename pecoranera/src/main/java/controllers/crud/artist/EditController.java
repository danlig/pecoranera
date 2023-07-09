package controllers.crud.artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ArtistDao;
import model.Artist;

public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		
		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");
		
		Artist artist = null;
		
		try {
			if (request.getParameter("id_artist") == null) {
				messages.add("Artist Not Found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
				return ;
			}
			
			artist = ArtistDao.doRetrieveByKey(Integer.parseInt(request.getParameter("id_artist")));
			
		} catch (NumberFormatException e) {
			messages.add("Id Artist Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		
		if (name == null || name.trim().equals("")) {
			artist.setName(artist.getName());
		} else {
			artist.setName(name);
		}
		
		
		if (description == null || description.trim().equals("")) {
			artist.setDescription(artist.getDescription());
		} else {
			artist.setDescription(description);
		}
		
		ArtistDao.doSave(artist);
		
		response.sendRedirect("list");
	}

}
