package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArtistDao;
import dao.EventDao;
import dao.UserDao;
import model.Artist;
import model.Event;
import model.User;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		for (User item : UserDao.doRetrieveAll()) {
			out.println(item);
		}
		
//		User user = UserDao.doRetrieveByEmail("example@mail.com");
//		out.println(user);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
