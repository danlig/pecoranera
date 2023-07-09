package controllers.crud.product_type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		List<String> messages = new ArrayList<>();
		
		String name = request.getParameter("name");		
		String id_product_type = request.getParameter("id_product_type");
		
		ProductType product_type = null;
		try {
			if (id_product_type != null) {
				product_type = ProductTypeDao.doRetrieveByKey(Integer.parseInt(id_product_type));
				
				if (name == null || name.trim().equals("")) {
					name = product_type.getName();
				} 
			} else {
				messages.add("Inserisci product_type");
			}
		} catch (NumberFormatException ex) {
			messages.add("id_product_type Format Not Allowed");
		}
		
		if (!messages.isEmpty()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new Gson().toJson(messages));
			return ;
		}
		
		product_type.setName(name);
		ProductTypeDao.doSave(product_type);

		response.sendRedirect("list");
	}

}
