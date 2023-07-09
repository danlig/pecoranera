package controllers.crud.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductDao;
import dao.ProductTypeDao;
import model.Product;
import model.ProductType;

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
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Product product = null;
		ProductType product_type = null;
		
		try {
			product = ProductDao.doRetrieveByKey(Integer.parseInt(request.getParameter("id_artist")));
			
			if (product == null) {
				messages.add("Product Not Found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
				return ;
			}
		} catch (NumberFormatException e) {
			messages.add("Id Artist Format Not Allowed Or Null");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		if (name != null && name.trim().equals("")) {
			product.setName(name);
		}
		
		if (description != null && description.trim().equals("")) {
			product.setDescription(description);
		}
		
		try {
			if (request.getParameter("price") != null) {
				product.setPrice(Double.parseDouble(request.getParameter("price")));
			}
		} catch (NumberFormatException e) {
			messages.add("Price Format Not Allowed");
		}

		try {
			if (request.getParameter("id_product_type") != null) {
				product_type = ProductTypeDao.doRetrieveByKey(Integer.parseInt(request.getParameter("id_product_type")));
				
				if (product_type == null) {
					messages.add("ProductType not found");
					response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
					return ;
				} else {
					product.setType(product_type);
				}
				
			}
		} catch(NumberFormatException e) {
			messages.add("Id ProductType Format Not Allowed Or Null");
		}

				
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new Gson().toJson(messages));
		} 
		
		
		ProductDao.doSave(product);
		
		response.sendRedirect("list");
	}

}
