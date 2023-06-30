package controllers.crud.product_type;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductType;
import dao.ProductTypeDao;

public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		String id_product_type = request.getParameter("id_product_type");

		ProductType product_type = null;
		if (id_product_type != null) {

			product_type = ProductTypeDao.doRetrieveByKey(Integer.parseInt(id_product_type));

			if (name == null || name.trim().equals("")) {
				name = product_type.getName();
			} 
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		product_type.setName(name);
		ProductTypeDao.doSave(product_type);

		response.sendRedirect("list");
	}

}
