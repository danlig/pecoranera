package controllers.crud.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Tag;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.TagDao;


public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<>();
		String name = (String) request.getParameter("name");
		
		if (name == null || name.trim().equals("")) {
			messages.add("Inserire il nome");
		} 
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		Tag tag = new Tag();
		tag.setName(name);
		TagDao.doSave(tag);
		
		response.sendRedirect("list");
	}
}
