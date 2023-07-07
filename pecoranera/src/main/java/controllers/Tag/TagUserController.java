package controllers.Tag;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.TagDao;
import dao.UserDao;
import model.Tag;
import model.User;

/*
 * Restituisce i tags dell'utente in sessione
 * */

@WebServlet("/TagUserController")
public class TagUserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public TagUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		int id = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(id);
		
		response.getWriter().write(new Gson().toJson(user.getTags()));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}