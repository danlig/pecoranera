package controllers.crud.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Map<String, String> messages = new HashMap<>();
		String name = (String) request.getParameter("name");
		String id_tag = request.getParameter("id_tag");
		
		Tag tag = null;
		if (id_tag != null) {
			
			tag = TagDao.doRetrieveByKey(Integer.parseInt(id_tag));
			
			if (name == null || name.trim().equals("")) {
				messages.put("error", "Inserire il nome");
			} 
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		if (!messages.isEmpty()) {
			request.setAttribute("tags", TagDao.doRetrieveAll());
			request.setAttribute("messages", messages);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/tag/page.jsp");
			dispatcher.forward(request, response);
		} else {
			tag.setName(name);
			TagDao.doSave(tag);
			
			response.sendRedirect("list");
		}
		
	}

}
