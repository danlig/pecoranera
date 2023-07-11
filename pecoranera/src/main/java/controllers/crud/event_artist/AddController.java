package controllers.crud.event_artist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GenericCrudController;
import dao.EventDao;
import model.EventArtist;

public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventArtist ea = new EventArtist();
		if (!GenericCrudController.Validate(ea, GenericCrudController.operation.ADD_MODE, request, response))
			return;
		
		EventDao.addArtist(ea.getEvent(), ea.getArtist(), ea.getRole());
		response.sendRedirect("list?id_event=" + ea.getEvent().getId());
	}

}
