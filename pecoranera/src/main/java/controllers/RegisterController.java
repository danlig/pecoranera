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
import utils.ValidatorUtils;
import utils.LoginUtils;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String conf_password = request.getParameter("conf_password");
		
		// Prendere tutti i tag che non sono null
		Set<Tag> tags = new HashSet<>();
		for (Tag tag : TagDao.doRetrieveAll()) {
			if (request.getParameter(tag.getId() + "-tag") != null) {
				tags.add(tag);
			}
		}
		
		if (!ValidatorUtils.CheckEmail(email)) {
			errors.add("Invalid email");
		} else if (UserDao.doRetrieveByEmail(email) != null) {
			errors.add("Existing email");
		}
		
		if (!ValidatorUtils.CheckUsername(username)) {
			errors.add("Invalid username");
		} 
		
		if (!ValidatorUtils.CheckPassword(password)) {
			errors.add("Invalid password");
		} else if (!password.equals(conf_password)) {
			errors.add("Passwords do not match");
		}
		
		if (email.equals(password)) {
			errors.add("Password and email match");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
		} else {
			User user = new User();
				
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(LoginUtils.toHash(password));
			user.setAdmin(false);
			user.setTags(tags);
			UserDao.doSave(user);
			
			Cart cart = new Cart();
			cart.setUser(UserDao.doRetrieveByEmail(email));
			CartDao.doSave(cart);
			
			request.getSession().setAttribute("isAdmin", Boolean.FALSE);
			response.sendRedirect("common/personal-page.jsp");
		}
	}
}
