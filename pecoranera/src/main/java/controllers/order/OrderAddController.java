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

import dao.EventDao;
import dao.OrderDao;
import dao.UserDao;
import model.Event;
import model.Order;
import model.User;

/*
 * Aggiunge un nuovo ordine all'utente in sessione
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
		
		int tickets;
        try {
        	tickets = Integer.parseInt(request.getParameter("tickets"));
        } catch (Exception e) { tickets = 1; }
        
		int idEvento = Integer.parseInt(request.getParameter("event"));
		Event event = EventDao.doRetrieveByKey(idEvento);
        
        Order order = new Order();
        order.setUser(user);
        order.setDate(new Date());
        order.setPrice(event.getPrice());
        order.setEvent(event);
        order.setTickets(tickets);
        
        boolean success = OrderDao.doSave(order);
        
        if (!success) response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}