package controllers.order;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.UserDao;
import model.User;
import model.Order;

/*
 * Elimina un ordine dell'utente in sessione se ancora non è avvenuto
 *  */

@WebServlet("/OrderDeleteController")
public class OrderDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public OrderDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(idUser);
		
		int idOrder =  Integer.parseInt(request.getParameter("idOrder"));
		Order order = OrderDao.doRetrieveByKey(idOrder);
		
		// Verifica se l'ordine appartiene all'utente
		if (!user.getOrders().stream().anyMatch(o -> o.getId() == idOrder)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "l'ordine non è dell'utente in sessione");
			return;
		}
		
		// Verifica se la data l'evento è già avvenuto		
		if (order.getEvent().getDate().compareTo(new Date()) < 0) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "l'evento è già avvenuto");
			return;
		}
			
		// Elimina l'ordine
		OrderDao.doDeleteByKey(idOrder);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
