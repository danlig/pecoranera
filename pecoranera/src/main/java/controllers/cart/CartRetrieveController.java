package controllers.cart;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import model.Cart;
import model.CartEvent;
import model.keys.CartEventKey;

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
		
		Cart cart;

	
		int idUser;
		try {
			idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
			cart = UserDao.doRetrieveByKey(idUser).getCart();
        } catch (Exception e) {
        	// Utente non loggato
        	
        	// Carrello in sessione esistente?
        	cart = (Cart) request.getSession().getAttribute("cart");

        	
        	if (cart == null) {
        		cart = new Cart();
        		cart.setId(-1);
        	}
        }
		
		Set<CartEvent> orders = new HashSet<>();
		
		for (CartEvent ce : cart.getCartEvents()) {
			CartEvent ceClone = new CartEvent();
			
			ceClone.setId(ce.getId());
			ceClone.setTickets(ce.getTickets());
			
			orders.add(ceClone);
		}

		response.getWriter().write(new Gson().toJson(orders));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}