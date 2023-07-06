package controllers.menu;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductTypeDao;
import model.ProductType;

import utils.GraphAdapterBuilder;

@WebServlet("/menu")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		/*GsonBuilder gsonBuilder = new GsonBuilder();
		new GraphAdapterBuilder()
			.addType(ProductType.class)
			.registerOn(gsonBuilder);

		Gson gson = gsonBuilder.create();*/

		response.getWriter().write(new Gson().toJson(ProductTypeDao.doRetrieveAll()));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
