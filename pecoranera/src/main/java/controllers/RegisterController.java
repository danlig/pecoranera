package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.TagDao;
import dao.UserDao;
import model.Cart;
import model.Tag;
import model.User;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String conf_password = request.getParameter("conf_password");
		
		Set<Tag> tags = new HashSet<>();
		for (Tag tag : TagDao.doRetrieveAll()) {
			if (request.getParameter(tag.getId() + "-tag") != null) {
				tags.add(tag);
			}
		}

		List<User> users = UserDao.doRetrieveAll();
		
		if (email == null || email.trim().equals("")) {
			errors.add("Insert email<br>");
		} else if (users.stream().filter(p -> p.getEmail().equals(email)).count() != 0) {
			errors.add("Existing email<br>");
		}
		
		if (username == null || username.trim().equals("")) {
			errors.add("Insert username<br>");
		} 
		
		if (password == null || password.trim().equals("")) {
			errors.add("Insert password<br>");
		} else if (!password.equals(conf_password)) {
			errors.add("Not equeals passwords<br>");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		} else {
			User user = new User();
				
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setAdmin(false);
			user.setTags(tags);
			UserDao.doSave(user);
			
			Cart cart = new Cart();
			cart.setUser(
					UserDao.doRetrieveAll().stream().filter(u -> u.getEmail().equals(email)).findFirst().get()
					);
			CartDao.doSave(cart);
			
			
			request.setAttribute("message", "tutt appost");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);
	}

}
