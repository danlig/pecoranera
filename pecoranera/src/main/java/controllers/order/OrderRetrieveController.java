package controllers.order;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import model.Event;
import model.Order;
import model.User;

/*
 * Restituisce ordini dell'utente corrente
 *  */

@WebServlet("/OrderRetrieveController")
public class OrderRetrieveController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public OrderRetrieveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(idUser);
		Set<Order> orders = user.getOrders();
		
		for (Order o : orders) {
			o.setUser(null);
			
			Event e = o.getEvent();
			e.setEventArtists(null);
		}

		response.getWriter().write(new Gson().toJson(orders));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}