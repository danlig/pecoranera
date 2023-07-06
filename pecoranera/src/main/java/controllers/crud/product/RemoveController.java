package controllers.crud.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import model.Product;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_product_string = request.getParameter("id_product");
		
		int id_product = -1;
		
		if (id_product_string == null || id_product_string.trim().equals("")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				id_product = Integer.parseInt(id_product_string);
			} catch(NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		}

		Product product = ProductDao.doRetrieveByKey(id_product);
		if (product == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		ProductDao.doDeleteByKey(product.getId());
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
