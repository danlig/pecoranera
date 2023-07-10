package controllers.crud.event;

import controllers.GenericController;
import dao.EventDao;
import dao.TagDao;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import model.Event;
import model.Tag;
import utils.EventImageUpload;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


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
	
	protected void doaPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Set<Tag> tags = new HashSet<>();
		
		// Controllo dei tag
		try {
			
			Gson gson = new Gson();	
			int array[] = Arrays.stream(gson.fromJson(request.getParameter("tags"), String[].class)).mapToInt(Integer::parseInt).toArray();
			
			tags = new HashSet<>();
			for (int x : array) {
				tags.add(TagDao.doRetrieveByKey(x));
			}
		}
		catch (Exception e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore inserimento dei tag");
		}
		
		// Controllo dei file
		Part filePart = request.getPart("photo");
		
		if (filePart == null || filePart.getSize() == 0) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Inserisci immagine");
			return;
		} else {
			if (filePart != null) {
				try {
					if (!EventImageUpload.isImage(filePart)) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Estensione file non valida");					
						return;
					}
				} catch (Exception e) {
					filePart = null;
				}
			}
			
		}

		Event event = new Event();
		
		if (!GenericController.Validate(event, request, response))
			return;
		
		event.setTags(tags.isEmpty() ? null : tags);
		event.setCancellation(null);
		
		event = EventDao.doSave(event);

		if (filePart != null)
			EventImageUpload.upload(getServletContext().getRealPath("/"), filePart, event.getId());
		
		response.sendRedirect("list");
	}
}
