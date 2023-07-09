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

/**
 * Servlet implementation class RemoveController
 */
public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		
		try {
			if (request.getParameter("id_artist") == null) {
				messages.add("Artist Not Found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
			}
			
			ArtistDao.doDeleteByKey(Integer.parseInt(request.getParameter("id_artist")));
			response.sendRedirect("list");
			
		} catch(NumberFormatException e) {
			messages.add("Id Artist Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
