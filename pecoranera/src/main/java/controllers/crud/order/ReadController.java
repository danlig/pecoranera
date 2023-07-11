package controllers.crud.order;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.OrderDao;
import dao.UserDao;
import model.Event;
import model.Order;
import model.User;


@WebServlet("/admin/order/list")
public class ReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        
        try {
            startDate = dateFormat.parse(request.getParameter("startDate"));
        } catch (Exception e) { startDate = null; }
        
		Date endDate = null;
		
        try {
            endDate = dateFormat.parse(request.getParameter("endDate"));
        } catch (Exception e) { endDate = null; }

        User user = null;
        try {
        	int id =  Integer.parseInt(request.getParameter("idUser"));
        	user = UserDao.doRetrieveByKey(id);
        }
        catch (Exception e){
        	user = null;
        }

        List<Order> orders = new ArrayList<>();
		
		for (Order o : OrderDao.doRetrieveFilter(startDate, endDate, user)) {
			o.setUser(null);
			
			Event e = o.getEvent();
			e.setEventArtists(null);
			
			orders.add(o);
		}
		
		orders.sort(Comparator.comparing(Order::getDate).reversed());
		
		response.getWriter().write(new Gson().toJson(orders));	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
