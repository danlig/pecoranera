package controllers.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;
import utils.LoginUtils;
import utils.ValidatorUtils;

/*
 * Modifica dati personali utente (email, password, username)
 * */

@WebServlet("/UserEditController")
public class UserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserEditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(id);
		
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newPassword");
		String oldPassword = request.getParameter("oldPassword");
		String username = request.getParameter("username");
		
		
		if (email != null) {
			if (!ValidatorUtils.CheckEmail(email) || UserDao.doRetrieveByEmail(email) != null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "email");
				return;
			}

			user.setEmail(email);	
		}
		
		if (username != null) {
			if (!ValidatorUtils.CheckUsername(username)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "username");
				return;
			}
			
			user.setUsername(username);
		}
			
		if (newPassword != null && oldPassword != null) {
			if (newPassword == null || oldPassword == null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Inserire la nuova e la vecchia password");
				return;
			}
			
			if (!ValidatorUtils.CheckPassword(newPassword)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "La nuova password è invalida");
				return;
			}
			
			if (!user.getPassword().equals(LoginUtils.toHash(oldPassword))) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "La password non è corretta");
				return;
			}
			
			user.setPassword(LoginUtils.toHash(newPassword));
		}
		
				
		UserDao.doSave(user);
		
		request.getSession().setAttribute("username", user.getUsername());
		request.getSession().setAttribute("email", user.getEmail());
		
		response.setStatus(200);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
