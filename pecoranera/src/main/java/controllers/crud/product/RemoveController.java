package controllers.crud.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import dao.ProductTypeDao;
import model.Product;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_product = request.getParameter("id");
		
		try {
			ProductDao.doDeleteByKey(Integer.parseInt(id_product));		
		}
		catch (NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "id_product Format Not Allowed");
			return ;
		}
		
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
