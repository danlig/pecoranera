package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email == null || email.trim().equals("")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		if (password == null || password.trim().equals("")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		User user = UserDao.doRetrieveByEmail(email);
		if (user == null || !user.getPassword().equals(password)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else if (user.isAdmin()) {
			response.setStatus(200);
			request.getSession().setAttribute("isAdmin", Boolean.TRUE);
			
		} else {
			response.setStatus(200);
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		}

	}

}
