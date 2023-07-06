package controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.UserDao;
import model.Cart;
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
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String conf_password = request.getParameter("conf_password");
			
		if (!ValidatorUtils.CheckEmail(email) || UserDao.doRetrieveByEmail(email) != null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "email");
			return;
		}
		
		if (!ValidatorUtils.CheckUsername(username)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "username");
			return;
		} 

		if (!ValidatorUtils.CheckPassword(password) || !password.equals(conf_password) || email.equals(password)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "password");
			return;
		}
		
		User user = new User();
		
		response.setStatus(200);

		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(LoginUtils.toHash(password));
		user.setAdmin(false);
		UserDao.doSave(user);
		
		Cart cart = new Cart();
		cart.setUser(UserDao.doRetrieveByEmail(email));
		CartDao.doSave(cart);
		
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
	}
}
