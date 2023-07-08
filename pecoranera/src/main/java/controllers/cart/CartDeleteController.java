package controllers.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.EventDao;
import dao.UserDao;
import model.Cart;
import model.Event;

/*
 * Elimina un evento dal carrello
 *  */

@WebServlet("/CartDeleteController")
public class CartDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public CartDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		Cart cart = UserDao.doRetrieveByKey(idUser).getCart();
		
		int eventID = Integer.parseInt(request.getParameter("event"));
		Event event = EventDao.doRetrieveByKey(eventID);
		
		CartDao.removeEvent(cart, event);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}