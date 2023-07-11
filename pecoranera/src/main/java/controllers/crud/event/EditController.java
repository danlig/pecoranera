package controllers.crud.event;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controllers.GenericCrudController;
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
		Set<Tag> tags = new HashSet<>();
		
		// Controllo dei tag 
		if (request.getParameterValues("tags") != null) {
			try {
				for (String tag_id : request.getParameterValues("tags")) {
					Tag tag = TagDao.doRetrieveByKey(Integer.parseInt(tag_id));
					if (tag != null) {
						tags.add(tag);
					} else {
						response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tag Non Trovato");
						return ;
					}
				}
			} catch (Exception e){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore inserimento dei tag");
				return ;
			}
		}
		
		// Controllo dei file
		Part filePart = request.getPart("photo");
		
		if (filePart != null && filePart.getSize() != 0) {
			try {
				if (!EventImageUpload.isImage(filePart)) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Estensione file non valida");					
					return;
				}
			} catch (Exception e) {
				filePart = null;
			}
		}
		
		if (!GenericCrudController.Edit(Event.class, request, response))
			return ;
		
		// Evitiamo il controllo sulla request.getParameter("id") 
		// perché già è stata effettuato il controllo nel GenericCrudController
		Event event = EventDao.doRetrieveByKey(Integer.parseInt(request.getParameter("id")));
		
		event.setTags(tags.isEmpty() ? null : tags);
		event.setCancellation(null);
		
		event = EventDao.doSave(event);

		if (filePart != null)
			EventImageUpload.upload(getServletContext().getRealPath("/"), filePart, event.getId());
		
		response.sendRedirect("list");
	}
}

