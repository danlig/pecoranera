package controllers.crud.product_type;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductTypeDao;
import dao.TagDao;
import model.ProductType;

public class ReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("product_types", ProductTypeDao.doRetrieveAll());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product-type/page.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
