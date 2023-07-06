package controllers.menu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductTypeDao;
import model.ProductType;

@WebServlet("/menu/product")
public class MenuProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_product_type = request.getParameter("type");
		
		try {
			ProductType productType = ProductTypeDao.doRetrieveByKey(Integer.parseInt(id_product_type));
			
			if (productType == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			response.setContentType("application/json");
			//response.getWriter().write(new Gson().toJson(productType.getProducts()));
		} catch (NumberFormatException ex) {
			response.sendError(HttpServletResponse.SC_ACCEPTED, "Id Format Not Allow");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
