package controllers.crud.event;

import com.google.gson.Gson;

import dao.EventDao;
import dao.TagDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Event;
import model.Tag;
import utils.FileManager;


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
		
		
		String id_event = (String) request.getParameter("id_event");
		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");
		String price_string = (String) request.getParameter("price");
		String date_string = (String) request.getParameter("date");
		String maxTickets_string = (String) request.getParameter("max_tickets");
		
		List<String> tags_string = request.getParameterValues("tags") != null ? 
				Arrays.asList(request.getParameterValues("tags")) : null;
		Part filePart = request.getPart("photo");
		
		Event event = null;
		
		try {
			event = EventDao.doRetrieveByKey(Integer.parseInt(id_event));
			
			if (event == null) {
				messages.add("Event Not Found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
			}
			
		} catch(NumberFormatException ex) {
			messages.add("Id Event Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));			
		}
			
		if (name == null || name.trim().equals("")) {
			name = event.getName();
		}

		if (description == null || description.trim().equals("")) {
			description = event.getDescription();
		}

		if (price_string == null || price_string.trim().equals("")) {
			price_strin
		}

		if (date_string == null || date_string.trim().equals("")) {
			messages.put("error", "Insert Date");
		}

		if (maxTickets_string == null || maxTickets_string.trim().equals("")) {
			messages.put("error", "Insert Max Tickets");
		}
		
		if (filePart == null || filePart.getSize() == 0) {
			messages.put("error", "Insert Image");
		} else {
			String fileExtesion = FileManager.getFileExtension(filePart);
			if (!(fileExtesion.equals(".jpeg") || fileExtesion.equals(".png"))) {
				messages.put("warning", "Extansion Not Allowed");
			}
		}

		if (tags_string != null && tags_string.stream().anyMatch(t -> t == null || t.trim().equals(""))) {
			messages.put("error", "Id Tag Null");
		}
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new Gson().toJson(messages));
		}
		
		try {
			double price = Double.parseDouble(price_string);
			Integer maxTickets = Integer.parseInt(maxTickets_string);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
			Date date = formatter.parse(date_string);

			Set<Tag> tags = new HashSet<>();
			for (String tag_string : tags_string) {
				tags.add(TagDao.doRetrieveByKey(Integer.parseInt(tag_string)));
			}
			
			Event event = new Event();
			event.setName(name);
			event.setDescription(description);
			event.setPrice(price);
			event.setMaxTickets(maxTickets);
			event.setAvailableTickets(maxTickets);
			event.setDate(date);
			event.setTags(tags.isEmpty() ? null : tags);
			event.setCancellation(null);
			
			event = EventDao.doSave(event);
			
			// TODO:: Fare l'upload dell'immagine
			
		} catch(NumberFormatException ex) {
			messages.put("error", "Number Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
		} catch (ParseException e) {
			messages.put("error", "Date Format Not Allowed");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			e.printStackTrace();
		}
		
		response.sendRedirect("list");
	}

}
