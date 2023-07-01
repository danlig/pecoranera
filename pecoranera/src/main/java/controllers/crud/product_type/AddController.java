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
		String name = (String) request.getParameter("name");

		if (name == null || name.trim().equals("")) {
			messages.put("error", "Inserire il nome");

			request.setAttribute("product_types", ProductTypeDao.doRetrieveAll());
			request.setAttribute("messages", messages);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product-type/page.jsp");
			dispatcher.forward(request, response);
		} else {
			ProductType product_type = new ProductType();
			product_type.setName(name);
			ProductTypeDao.doSave(product_type);

			response.sendRedirect("list");
		}
	}

}
