package controllers.crud.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.GenericCrudController;
import dao.ProductTypeDao;
import dao.TagDao;
import model.Tag;

public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!GenericCrudController.Edit(Tag.class, request, response))
			return;
		
		response.sendRedirect("list"); 
	}

}
