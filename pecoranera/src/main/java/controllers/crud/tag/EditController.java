package controllers.crud.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductTypeDao;
import dao.TagDao;
import model.Tag;

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
		String name = (String) request.getParameter("name");
		String id_tag = request.getParameter("id_tag");
		
		Tag tag = null;
		try {
			if (id_tag != null) {			
				tag = TagDao.doRetrieveByKey(Integer.parseInt(id_tag));
				
				if (name == null || name.trim().equals("")) {
					messages.add("Inserire il nome");
				} 
			} else {
				messages.add("Inserisci id_tag");
			}
		} catch (NumberFormatException ex) {
			messages.add("id_tag Format Not Allowed");
		}
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
			
		tag.setName(name);
		TagDao.doSave(tag);
		
		response.sendRedirect("list");
	}

}
