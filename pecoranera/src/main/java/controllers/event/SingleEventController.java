package controllers.event;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.EventDao;
import model.Event;

/*
 * Restituisce un evento in base al suo id, comprensivo di artisti
 * */

@WebServlet("/SingleEventController")
public class SingleEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SingleEventController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		int id =  Integer.parseInt(request.getParameter("id"));
		Event e = EventDao.doRetrieveByKey(id);
		
		e.setArtists(e.getArtists());
		e.setEventArtists(null);
		
		response.getWriter().write(new Gson().toJson(e));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}