package controllers.cart;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.EventDao;
import dao.UserDao;
import model.Cart;
import model.CartEvent;
import model.Event;
import model.keys.CartEventKey;

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

		int eventID = Integer.parseInt(request.getParameter("event"));
		Event event = EventDao.doRetrieveByKey(eventID);
		
		int idUser;
		try {
			idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
        } catch (Exception e) {
        	// Utente non loggato
        	
        	// Carrello in sessione esistente?
        	Cart cart = (Cart) request.getSession().getAttribute("cart");
        	
        	if (cart == null) {
        		cart = new Cart();
        		cart.setId(-1);
        	}
        	
        	// Elimina cartEvent
    		CartEventKey id = new CartEventKey(event.getId(), cart.getId());
    		Set<CartEvent> ces = cart.getCartEvents();
    		
    		Optional<CartEvent> cartEvent = ces.stream().filter(ce -> ce.getId().equals(id)).findFirst();
    		
    		cartEvent.ifPresent(ce -> ces.remove(ce));
    		
    		request.getSession().setAttribute("cart", cart);
    		
        	return;
        }
		
		Cart cart = UserDao.doRetrieveByKey(idUser).getCart();
		CartDao.removeEvent(cart, event);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}