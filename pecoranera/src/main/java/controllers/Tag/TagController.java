package controllers.Tag;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.EventDao;
import dao.UserDao;
import model.User;

@WebServlet("/TagController")
public class TagController {
	private static final long serialVersionUID = 1L;
    
    public TagController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(id);
		
		Gson gson = new Gson();
		int[] array = gson.fromJson(request.getParameter("ids"), int[].class);
		
		for (int n : array) {
			System.out.println(n);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
