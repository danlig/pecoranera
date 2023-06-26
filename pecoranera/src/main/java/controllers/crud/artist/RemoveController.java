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

/**
 * Servlet implementation class RemoveController
 */
public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_artist = Integer.parseInt(request.getParameter("id_artist"));
		Map<String, String> messages = new HashMap<>();
		
		ArtistDao.doDeleteByKey(id_artist);
		messages.put("successfully", "Artista cancellato");
		
		request.setAttribute("messages", messages);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/artist/page.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
