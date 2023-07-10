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
		String id_artist = request.getParameter("id");
		
		if (id_artist == null) {
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "id_artist formato non valido");
		}
		
		ArtistDao.doDeleteByKey(Integer.parseInt(id_artist));
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
