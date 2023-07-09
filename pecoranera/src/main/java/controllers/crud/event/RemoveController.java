package controllers.crud.event;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import model.Event;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> message = new ArrayList<>();
		String id_event = request.getParameter("id_event");
		
		try {
			Event event = EventDao.doRetrieveByKey(Integer.parseInt(id_event));
			
			if (event == null) {
				message.add("Event Not Found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(message));
			}
			
			event.setCancellation(new Date());
			EventDao.doSave(event);
			
			response.sendRedirect("list");
		} catch (NumberFormatException ex) {
			message.add("Id Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(message));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
