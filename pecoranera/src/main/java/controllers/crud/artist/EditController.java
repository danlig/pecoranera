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

public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();

		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");
		
		String id_artist = request.getParameter("id_artist");
		if (id_artist != null) {
			Artist artist = ArtistDao.doRetrieveByKey(Integer.parseInt(id_artist));
			
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
			
			messages.put("successfully", "Aggiornato Artista");
		} else {
			messages.put("error", "Artista non esistente");
		}
		
		request.setAttribute("messages", messages);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/artist/page.jsp");
		dispatcher.forward(request, response);
	}

}
