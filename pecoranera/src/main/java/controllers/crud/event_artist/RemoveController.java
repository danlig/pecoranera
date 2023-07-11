package controllers.crud.event_artist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GenericCrudController;
import dao.EventDao;
import model.EventArtist;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventArtist ea = new EventArtist();
		
		if (GenericCrudController.Validate(ea, GenericCrudController.operation.REMOVE_MODE, request, response))
			EventDao.deleteArtist(ea.getEvent(), ea.getArtist());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
