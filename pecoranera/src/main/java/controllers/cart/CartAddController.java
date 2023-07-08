package controllers.cart;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CartDao;
import dao.EventDao;
import dao.TagDao;
import dao.UserDao;
import model.Tag;
import model.User;
import model.Cart;
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
		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		Cart cart = UserDao.doRetrieveByKey(idUser).getCart();
		
		int tickets;
        try {
        	tickets = Integer.parseInt(request.getParameter("tickets"));
        } catch (Exception e) { tickets = 1; }

		int idEvento = Integer.parseInt(request.getParameter("event"));
		Event event = EventDao.doRetrieveByKey(idEvento);
		
		boolean edit = request.getParameter("edit") != null;
        
        CartDao.addEvent(cart, event, tickets, edit);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}