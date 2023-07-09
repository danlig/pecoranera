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
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		double price = 0.0;
		ProductType product_type = null;
		
		if (name == null || name.trim().equals("")) {
			messages.add("Inserire il Nome");
		}
		
		
		if (description == null || description.trim().equals("")) {
			messages.add("Inserire la descrizione");
		}
		

		try {
			if (request.getParameter("price") == null) {
				messages.add("Inserire il prezzo");
			} else {
				price = Double.parseDouble(request.getParameter("price"));
			}
		} catch (NumberFormatException e) {
			messages.add("Price Format Not Allowed");
		}
		
		
		try {
			product_type = ProductTypeDao.doRetrieveByKey(Integer.parseInt(request.getParameter("id_product_type")));
		
			if (product_type == null) {
				messages.add("ProductType not found");
				response.sendError(HttpServletResponse.SC_NOT_FOUND, new Gson().toJson(messages));
				return ;
			}
		} catch(NumberFormatException e) {
			messages.add("Id ProductType Format Not Allowed Or Null");
		}
					
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, new Gson().toJson(messages));
			return ;
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
