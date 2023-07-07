package controllers.event;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import model.Event;

/*
 * Restituisce i primi due eventi prossimi
 * */

@WebServlet("/UpcomingEventsController")
public class UpcomingEventsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpcomingEventsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		response.getWriter().write(new Gson().toJson(EventDao.doRetrieveUpcoming()));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}