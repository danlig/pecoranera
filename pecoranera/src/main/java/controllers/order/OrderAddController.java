package controllers.order;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.EventDao;
import dao.OrderDao;
import dao.UserDao;
import model.Cart;
import model.CartEvent;
import model.Event;
import model.Order;
import model.User;
import utils.ValidatorUtils;

/*
 * Per ogni elemento nel carrello dell'utente in sessione, crea un ordine
 * e svuota il carrello
 *  */

@WebServlet("/OrderAddController")
public class OrderAddController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public OrderAddController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(idUser);
		Cart cart = UserDao.doRetrieveByKey(idUser).getCart();
		
		for (CartEvent ce : cart.getCartEvents()) {
			Event event = ce.getEvent();
			
	        Order order = new Order();
	        order.setUser(user);
	        order.setDate(new Date());
	        order.setPrice(event.getPrice());
	        order.setEvent(event);
	        order.setTickets(ce.getTickets());
     
	        if (!ValidatorUtils.CheckOrder(request, response, order))
	        	return;
	        
	        if (!OrderDao.doSave(order)) {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Numero di ticket invalido");
	        	return;
	        }
		}
		
		// Svuota carrello
		CartDao.doDeleteByKey(cart.getId());
		cart = new Cart();
		cart.setUser(user);
		CartDao.doSave(cart);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}