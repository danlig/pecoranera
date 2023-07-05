package controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEmailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		if (email == null || email.trim().equals("") || UserDao.doRetrieveByEmail(email) != null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
            response.setStatus(200);
        }
    }

}
