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
import utils.ValidatorUtils;
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
		int idOrder =  Integer.parseInt(request.getParameter("idOrder"));
		Order order = OrderDao.doRetrieveByKey(idOrder);
		
		if (ValidatorUtils.CheckOrder(request, response, order))
			OrderDao.doDeleteByKey(idOrder);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
