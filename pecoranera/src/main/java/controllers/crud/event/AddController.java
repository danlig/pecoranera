package controllers.crud.event;

import com.google.gson.Gson;

import dao.EventDao;
import dao.TagDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Event;
import model.Tag;
import utils.EventImageUpload;


@MultipartConfig
public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		getServletContext();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		
		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");
		
		Set<Tag> tags = new HashSet<>();
		double price = 0;
		int max_tickets = 0;
		Date date = null;
		
		
		// Controllo del Nome
		
		if (name == null || name.trim().equals("")) {
			messages.add("Insert Name");
		}
		
		
		// Controllo della Descrizione
		
		if (description == null || description.trim().equals("")) {
			messages.add("Insert Description");
		}

		
		// Controllo sul prezzo
		
		try {
			if (request.getParameter("price") == null) {
				messages.add("Insert price");
			}
			price = Double.parseDouble(request.getParameter("price"));
		} catch (NumberFormatException ex) {
			messages.add("Price Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		
		// Controllo per il max ticket

		try {
			if (request.getParameter("max_tickets") == null) {
				messages.add("Insert max tickets");
			} else {
				max_tickets = Integer.parseInt(request.getParameter("max_tickets"));
			}
		} catch (NumberFormatException ex) {
			messages.add("Max Tickets Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		
		// Controllo per la data
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
			date = formatter.parse(request.getParameter("date"));
			
		} catch (NullPointerException e) {
			messages.add("Insert Date");
		
		} catch (ParseException e) {
			messages.add("Date Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}

		
		// Controllo dei tag
		try {
			
			if (request.getParameterValues("tags") != null) {
				for (String tag : request.getParameterValues("tags")) {
					if (tag == null) {
						tags.add(TagDao.doRetrieveByKey(Integer.parseInt(tag)));
					}
				}	
				
			} else {
				messages.add("Insert Tags");
			}
			
		} catch(NumberFormatException e) {
			messages.add("ID Tag Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		

		Part filePart = request.getPart("photo");
		
		if (filePart == null || filePart.getSize() == 0) {
			messages.add("Insert Image");
		} else {
			// Controllo per il file

			if (filePart != null) {
				try {
					if (!EventImageUpload.isImage(filePart))
						messages.add("Extension Not Allowed");					
				} catch (Exception e) {
					filePart = null;
				}
			}
			
		}
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new Gson().toJson(messages));
			return ;
		}
	
		Event event = new Event();
		event.setName(name);
		event.setDescription(description);
		event.setPrice(price);
		event.setMaxTickets(max_tickets);
		event.setAvailableTickets(max_tickets);
		event.setDate(date);
		event.setTags(tags.isEmpty() ? null : tags);
		event.setCancellation(null);
		
		event = EventDao.doSave(event);

		if (filePart != null)
			EventImageUpload.upload(getServletContext().getRealPath("/"), filePart, event.getId());
		
		response.sendRedirect("list");
	}
}
