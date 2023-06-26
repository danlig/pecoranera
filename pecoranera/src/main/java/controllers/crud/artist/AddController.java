package controllers.crud.artist;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Map<String, String> messages = new HashMap<>();

		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");

		if (name == null || name.trim().equals("")) {
			messages.put("error", "Insert Name");
		}
		
		if (description == null || description.trim().equals("")) {
			messages.put("error", "Insert Description");
		}
		
		if (!messages.isEmpty()) {
			request.setAttribute("messages", messages);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/artist/add.jsp");
			dispatcher.forward(request, response);
		} else {
			Artist artist = new Artist();
			
			artist.setName(name);
			artist.setDescription(description);
			
			ArtistDao.doSave(artist);
			
			messages.put("successfully", "Aggiunto Artista");
			
			request.setAttribute("messages", messages);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/artist/page.jsp");
			dispatcher.forward(request, response);
		}
	}

}
