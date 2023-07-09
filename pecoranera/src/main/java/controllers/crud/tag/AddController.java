package controllers.crud.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.ProductType;
import model.Tag;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.GenericController;
import dao.ProductTypeDao;
import dao.TagDao;


public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Tag tag = new Tag();
		
		if (!GenericController.Add(tag, request, response))
			return;
	
		TagDao.doSave(tag);

		response.sendRedirect("list");
	}
}
