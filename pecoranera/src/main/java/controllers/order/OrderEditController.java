package controllers.order;

import java.io.IOException;
import java.util.Date;

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
import utils.ValidatorUtils;

/*
 * Modifica un ordine dell'utente in sessione 
 *  */

@WebServlet("/OrderEditController")
public class OrderEditController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public OrderEditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order;
		int idOrder;
        try {
        	idOrder = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) { 
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id ordine invalido");
        	return;
    	}
        
        order = OrderDao.doRetrieveByKey(idOrder);
        
        if (order == null) {
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ordine non esistente");
        	return;
        }
		
		int tickets;
        try {
        	tickets = Integer.parseInt(request.getParameter("tickets"));
        } catch (Exception e) { tickets = 1; }
        
        if (!ValidatorUtils.CheckOrder(request, response, order))
        	return;
        
        // Salva ordine
        if (!OrderDao.doEdit(order, tickets))
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Numero di ticket invalido");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}