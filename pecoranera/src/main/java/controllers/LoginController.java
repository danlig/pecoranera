package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		List<String> errors = new ArrayList<>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RequestDispatcher dispatcherToLoginPage = getServletContext().getRequestDispatcher("/login.jsp");
		
		if (email == null || email.trim().equals("")) {
			errors.add("Insert email<br>");
		}
		
		if (password == null || password.trim().equals("")) {
			errors.add("Insert password<br>");
		}
		
		if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return; 
		}
		
		User user = UserDao.doRetrieveByEmail(email);
		if (user == null || !user.getPassword().equals(password)) {
			errors.add("Wrong Email or Password");
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
		} else if (user.isAdmin()) {
			System.out.println("FATTOOOO");
			request.getSession().setAttribute("isAdmin", Boolean.TRUE);
			response.sendRedirect("admin/personal-page.jsp");
		} else {
			request.getSession().setAttribute("isAdmin", Boolean.FALSE);
			response.sendRedirect("common/personal-page.jsp");
		}
	}

}
