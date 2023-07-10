package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.LoginUtils;
import utils.ValidatorUtils;
import dao.CartDao;
import dao.UserDao;
import dao.CartDao;
import model.Cart;
import model.CartEvent;
import model.User;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
    
		if (!ValidatorUtils.CheckEmail(email)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		if (!ValidatorUtils.CheckPassword(password)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		User user = UserDao.doRetrieveByEmail(email);
		if (user == null || !user.getPassword().equals(LoginUtils.toHash(password))) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else if (user.isAdmin()) {
			response.setStatus(200);
			request.getSession().setAttribute("isAdmin", Boolean.TRUE);
			
		} else {			
			response.setStatus(200);	
			
			// Salva carrello in sessione
			Cart cart = (Cart) request.getSession().getAttribute("cart");
	    	
	    	if (cart != null) {
	    		for (CartEvent ce : cart.getCartEvents()) {
	    			CartDao.addEvent(user.getCart(), ce.getEvent(), ce.getTickets(), true);
	    		}
	    	}

			request.getSession().setAttribute("user", user.getId());
			request.getSession().setAttribute("email", user.getEmail());
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		}

	}
}
