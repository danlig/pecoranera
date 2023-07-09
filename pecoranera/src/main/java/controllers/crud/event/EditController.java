package controllers.crud.event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import dao.EventDao;
import dao.TagDao;
import model.Event;
import model.Tag;
import utils.EventImageUpload;


@MultipartConfig
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		
		String id_event = (String) request.getParameter("id_event");
		String name = (String) request.getParameter("name");
		
		String description = (String) request.getParameter("description");
		
		Set<Tag> tags = new HashSet<>();
		double price = 0;
		int max_tickets = 0;
		int available_tickets = 0;
		Date date = null;
		
		Part filePart = request.getPart("photo");
		
		// Dichiarazione e init del Evento
		Event event = null;
		
		try {
			event = EventDao.doRetrieveByKey(Integer.parseInt(id_event));
			
			if (event == null) {
				messages.add("Event not Found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
				return ;
			}
			
		} catch(NumberFormatException ex) {
			messages.add("Id Event Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		// Controllo sul nome
		
		if (name == null || name.trim().equals("")) {
			name = event.getName();
		} 
		
		
		// Controllo sul cognome

		if (description == null || description.trim().equals("")) {
			description = event.getDescription();
		}

		
		// Controllo sul prezzo
		
		try {
			if (request.getParameter("price") == null) {
				price = event.getPrice();
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
				max_tickets = event.getMaxTickets();
				available_tickets = event.getAvailableTickets();
			} else {
				max_tickets = Integer.parseInt(request.getParameter("max_tickets"));
				
				if (max_tickets < event.getMaxTickets()) {
					messages.add("Max Ticket cannot be less than the max ticket stored");
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
					return ;
				} else if (max_tickets > event.getMaxTickets()) {
					available_tickets = max_tickets - (event.getMaxTickets() - event.getAvailableTickets());
				} else {
					available_tickets = event.getAvailableTickets();
				}
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
			date = event.getDate();
		
		} catch (ParseException e) {
			messages.add("Date Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		// Controllo per il file

		if (filePart != null) {
			try {
				if (!EventImageUpload.isImage(filePart))
					messages.add("Extension Not Allowed");					
			} catch (Exception e) {
				filePart = null;
			}
		}
		
		
		// Controllo dei tag
		
		List<String> tags_string = request.getParameterValues("tags") != null ? 
				Arrays.asList(request.getParameterValues("tags")) : null;

		try {
			
			if (request.getParameterValues("tags") != null) {
				for (String tag : request.getParameterValues("tags")) {
					if (tag == null) {
						tags.add(TagDao.doRetrieveByKey(Integer.parseInt(tag)));
					}
				}	
				
			} else {
				tags = event.getTags();
			}
			
		} catch(NumberFormatException e) {
			messages.add("ID Tag Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		
		event.setName(name);
		event.setDescription(description);
		event.setPrice(price);
		event.setMaxTickets(max_tickets);
		event.setAvailableTickets(available_tickets);
		event.setDate(date);
		event.setTags(tags);
		event.setCancellation(null);
		
		EventDao.doSave(event);
		
		if (filePart != null)
			EventImageUpload.upload(getServletContext().getRealPath("/"), filePart, event.getId());
		
		response.sendRedirect("list");
	}
}

