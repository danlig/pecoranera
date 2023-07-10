package controllers.crud.product_type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductTypeDao;
/**
 * Servlet implementation class RemoveController
 */
public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_product_type = request.getParameter("id");
		
		try {
			ProductTypeDao.doDeleteByKey(Integer.parseInt(id_product_type));		
		}
		catch (NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "id_product_type Format Not Allowed");
			return ;
		}
		
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
