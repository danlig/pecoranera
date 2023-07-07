package controllers.event;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import dao.TagDao;
import model.Tag;

/*
 * Restituisce tutti gli eventi e, se presenti i parametri, in modo filtrato
 * */

@WebServlet("/EventRetrieveController")
public class EventRetrieveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventRetrieveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		String name = request.getParameter("name");
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        
        try {
            startDate = dateFormat.parse(request.getParameter("startDate"));
        } catch (Exception e) { startDate = null; }
        
		Date endDate = null;
		
        try {
            endDate = dateFormat.parse(request.getParameter("endDate"));
        } catch (Exception e) { endDate = null; }

        Set<Tag> tags = null;
        if (request.getParameter("tags") != null) {
    		int array[] = Arrays.stream(new Gson().fromJson(request.getParameter("tags"), String[].class)).mapToInt(Integer::parseInt).toArray();
    		tags = new HashSet<>();
    		
    		for (int x : array) {
    			tags.add(TagDao.doRetrieveByKey(x));
    		}
        }
        
		int pageSize, offset;
		
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		catch(Exception e) {pageSize = 9; }
		
		
		try{
			offset =  Integer.parseInt(request.getParameter("offset")) * pageSize;
		}
		catch(Exception e){ offset = 0; }

		
		response.getWriter().write(new Gson().toJson(EventDao.doRetrieveFilter(name, startDate, endDate, tags, pageSize, offset)));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}