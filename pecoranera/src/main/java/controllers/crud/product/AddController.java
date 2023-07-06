package controllers.crud.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import dao.ProductTypeDao;
import model.Product;
import model.ProductType;

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
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price_string = request.getParameter("price");
		String id_product_type_string = request.getParameter("id_product_type");
		
		double price = 0.0;
		int id_product_type = -1;
		
		if (name == null || name.trim().equals("")) {
			messages.put("error", "Inserire il Nome");
		}
		
		if (description == null || description.trim().equals("")) {
			messages.put("error", "Inserire la descrizione");
		}

		if (price_string == null || price_string.trim().equals("")) {
			messages.put("error", "Inserire il prezzo");
		} else {
			try {
				price = Double.parseDouble(price_string);
			} catch(NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		}
		
		if (id_product_type_string == null || id_product_type_string.trim().equals("")) {
			messages.put("error", "Inserire il tipo del prodotto");
		} else {
			try {
				id_product_type = Integer.parseInt(id_product_type_string);
			} catch(NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		}				
		
		if (!messages.isEmpty()) {
			request.setAttribute("messages", messages);
			request.setAttribute("products", ProductDao.doRetrieveAll());
			request.setAttribute("product_types", ProductTypeDao.doRetrieveAll());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/page.jsp");
			dispatcher.forward(request, response);
		} else {
			ProductType product_type = ProductTypeDao.doRetrieveByKey(id_product_type);
			
			if (product_type == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setType(product_type);
			ProductDao.doSave(product);
			
			response.sendRedirect("list"); 
		}
	}

}
