package controllers.crud.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GenericController;
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
		Product product = new Product();
		
		if (!GenericController.Add(product, request, response))
			return;
		
		// Ottieni il product type associato
		ProductType productType;
		
		try {
			int id = Integer.parseInt(request.getParameter("type"));
			productType = ProductTypeDao.doRetrieveByKey(id);
		}
		catch (Exception e){
			productType = null;
		}
		
		if (productType == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo prodotto inesistente!");
			return;
		}
		
		
		product.setType(productType);
		ProductDao.doSave(product);
		
		response.sendRedirect("list"); 
	}
}
