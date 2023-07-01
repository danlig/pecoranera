package controllers.crud.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.Tag;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Map<String, String> messages = new HashMap<>();
		String name = (String) request.getParameter("name");
		
		if (name == null || name.trim().equals("")) {
			messages.put("error", "Inserire il nome");
			
			request.setAttribute("tags", TagDao.doRetrieveAll());
			request.setAttribute("messages", messages);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/tag/page.jsp");
			dispatcher.forward(request, response);
		} else {
			Tag tag = new Tag();
			tag.setName(name);
			TagDao.doSave(tag);
			
			response.sendRedirect("list");
		}
		
		
	}

}
