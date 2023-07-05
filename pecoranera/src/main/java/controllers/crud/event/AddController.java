package controllers.crud.event;

import org.json.JSONObject;

import dao.TagDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import model.Artist;
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
		Map<String, String> messages = new HashMap<>();
		
		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");
		String price_string = (String) request.getParameter("price");
		String date_string = (String) request.getParameter("date");
		String availableTickets_string = (String) request.getParameter("available_tickets");
		String maxTickets_string = (String) request.getParameter("max_tickets");
		List<String> tags_string = Arrays.asList(request.getParameterValues("tags"));
		List<String> artists_string = Arrays.asList(request.getParameterValues("artists"));
		Part filePart = request.getPart("file");
		
		if (name == null || name.trim().equals("")) {
			messages.put("error", "Insert Name");
		}

		if (description == null || description.trim().equals("")) {
			messages.put("error", "Insert Description");
		}

		if (price_string == null || price_string.trim().equals("")) {
			messages.put("error", "Insert Price");
		}

		if (date_string == null || date_string.trim().equals("")) {
			messages.put("error", "Insert Date");
		}

		if (availableTickets_string == null || availableTickets_string.trim().equals("")) {
			messages.put("error", "Insert Available Tickets");
		}

		if (maxTickets_string == null || maxTickets_string.trim().equals("")) {
			messages.put("error", "Insert Max Tickets");
		}
		
		if (filePart == null) {
			messages.put("error", "Insert Image");
		} else {
			if (!(FileManager.getFileExtension(filePart).equals("jpeg") || FileManager.getFileExtension(filePart).equals("png"))) {
				messages.put("warning", "Extansion Not Allowed");
			}
		}

		if (tags_string.stream().anyMatch(t -> t == null || t.trim().equals(""))) {
			messages.put("error", "Id Tag Null");
		}
		
		if (tags_string.stream().anyMatch(t -> t == null || t.trim().equals(""))) {
			messages.put("error", "Id Tag Null");
		}
		
		if (!messages.isEmpty()) {
			JSONObject messages_json = new JSONObject(messages);
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, messages_json.toString());
		}
		
		try {
			double price = Double.parseDouble(price_string);
			Integer availableTickets = Integer.parseInt(availableTickets_string);
			Integer maxTickets = Integer.parseInt(maxTickets_string);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ITALIAN);
			Date date = formatter.parse(date_string);

			Set<Tag> tags = new HashSet<>();
			for (String tag_string : tags_string) {
				tags.add(TagDao.doRetrieveByKey(Integer.parseInt(tag_string)));
			}
			
			Set<Artist> artists = new HashSet<>();
			for (String artist_string : artists_string) {
				artists.add(TagDao.doRetrieveByKey(Integer.parseInt(artist_string)));
			}
			
			Event event = new Event();
			event.setName(name);
			event.setDescription(description);
			event.setPrice(price);
			event.setMaxTickets(maxTickets);
			event.setAvailableTickets(availableTickets);
			event.setDate(date);
			event.setTags(tags.isEmpty() ? null : tags);
			event.setCancellation(null);
			event.setEventArtists(artists);
	        
	
		} catch(NumberFormatException ex) {
			messages.put("error", "Number Format Not Allowed");
			JSONObject messages_json = new JSONObject(messages);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, messages_json.toString());
		} catch (ParseException e) {
			messages.put("error", "Date Format Not Allowed");
			JSONObject messages_json = new JSONObject(messages);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, messages_json.toString());
			e.printStackTrace();
		}
	}

}
