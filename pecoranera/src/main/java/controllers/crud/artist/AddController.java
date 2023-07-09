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

		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");

		if (name == null || name.trim().equals("")) {
			messages.add("Insert Name");
		}
		
		if (description == null || description.trim().equals("")) {
			messages.add("Insert Description");
		}
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new Gson().toJson(messages));
			return ;
		}
		
		Artist artist = new Artist();
		artist.setName(name);
		artist.setDescription(description);
		ArtistDao.doSave(artist);
		
		response.sendRedirect("list");
	}

}
