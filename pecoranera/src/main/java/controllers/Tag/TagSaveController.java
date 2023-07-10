package controllers.Tag;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.EventDao;
import dao.TagDao;
import dao.UserDao;
import model.User;
import model.Tag;

/*
 * Associa i tags nella request all'utente in sessione
 *  */

@WebServlet("/TagSaveController")
public class TagSaveController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public TagSaveController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(id);
		
		Gson gson = new Gson();
		
		int array[] = Arrays.stream(gson.fromJson(request.getParameter("ids"), String[].class)).mapToInt(Integer::parseInt).toArray();
		
		Set<Tag> tags = new HashSet<>();
		
		for (int x : array) {
			tags.add(TagDao.doRetrieveByKey(x));
		}
		
		user.setTags(tags);
		UserDao.doSave(user);
		
		response.setStatus(200);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
