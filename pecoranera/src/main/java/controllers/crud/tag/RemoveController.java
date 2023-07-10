package controllers.crud.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.TagDao;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_tag = request.getParameter("id_tag");
		
		try {
			TagDao.doDeleteByKey(Integer.parseInt(id_tag));			
		}
		catch (NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "id_tag Format Not Allowed");
			return ;
		}

		response.sendRedirect("list");			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
