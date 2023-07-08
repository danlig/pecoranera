package controllers.cart;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import model.Cart;
import model.CartEvent;

/*
 * Restituisce eventi e numero di ticket nel carrello dell'utente in sessione
 *  */

@WebServlet("/CartRetrieveController")
public class CartRetrieveController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public CartRetrieveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		Cart cart = UserDao.doRetrieveByKey(idUser).getCart();
		
		Set<CartEvent> orders = cart.getCartEvents();
		
		for (CartEvent order : orders) {
			order.setCart(null);
			order.setEvent(null);
		}

		response.getWriter().write(new Gson().toJson(orders));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}