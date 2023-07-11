package controllers.crud.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;

import dao.ProductDao;
import dao.ProductTypeDao;

public class ReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().write(new Gson().toJson(ProductDao.doRetrieveAll()));	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
