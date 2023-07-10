package controllers.cart;

import java.io.IOException;
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

/*
 * Aggiunge un item al carrello dell'utente in sessione
 *  */

@WebServlet("/CartAddController")
public class CartAddController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public CartAddController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tickets;
        try {
        	tickets = Integer.parseInt(request.getParameter("tickets"));
        } catch (Exception e) { tickets = 1; }
		
        
		int idEvento = Integer.parseInt(request.getParameter("event"));
		Event event = EventDao.doRetrieveByKey(idEvento);
		
		boolean edit = request.getParameter("edit") != null;
		
		
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
        	
        	// Inserisci la nuova entry di CartEvent nel carrello
        	Set<CartEvent> ces = cart.getCartEvents();
    		CartEvent newCE = CartDao.getCartEvent(cart, event, tickets, edit);
    		
    		ces.add(newCE);
    		cart.setCartEvents(ces);
    		
    		request.getSession().setAttribute("cart", cart);
        	return;
        }
		
		Cart cart = UserDao.doRetrieveByKey(idUser).getCart();
        
        CartDao.addEvent(cart, event, tickets, edit);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}