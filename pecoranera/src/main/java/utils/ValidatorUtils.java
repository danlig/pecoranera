package utils;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.Order;
import model.User;

public class ValidatorUtils {
	private ValidatorUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean CheckOrder(HttpServletRequest request, HttpServletResponse response, Order order) throws IOException {
		// Ottieni utente in sessione
		int idUser = Integer.parseInt(request.getSession().getAttribute("user").toString());
		User user = UserDao.doRetrieveByKey(idUser);
		
		// l'ordine appartiene all'utente?
		if (order.getUser().getId() != user.getId()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "l'ordine non appartiene all'utente in sessione");
			return false;
		}	
		
		// evento futuro?	
		if (order.getEvent().getDate().compareTo(new Date()) < 0) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "l'evento è già avvenuto");
			return false;
		}
		
		// evento è attivo?
		if (order.getEvent().getCancellation() != null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "l'evento è stato cancellato");
			return false;
		}
		
		return true;
	}
	
	private static boolean RegexValidation(String regex, String str) {
		if (str == null) return false;
		return Pattern.compile(regex)
			      .matcher(str)
			      .matches();
	}
	
	public static boolean CheckEmail(String email) {
		// Standard RFC 5322
		
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		return RegexValidation(regex, email);
	}
	
	 public static boolean CheckPassword(String password) {
		 // Requisiti password:
		 // - almeno 8 e massimo 20 caratteri
		 // - almeno un numero
		 // - almeno un lettera minuscola
		 // - almeno una lettera maiuscola
		 // - almeno un carattere speciale fra !@#$%&*()-+=^
		 // - non contiene spazi
		 
		 String regex = "^(?=.*[0-9])"
		               + "(?=.*[a-z])(?=.*[A-Z])"
		               + "(?=.*[@#!$%^&+=])"
		               + "(?=\\S+$).{8,20}$";
		 return RegexValidation(regex, password);
	 }
	 
	 public static boolean CheckUsername(String username) {
		 // Requisiti username:
		 // - almeno 4 caratteri e massimo 15
		 // - può contenere lettere minuscule, maiuscole, numeri e underscores
		 
		 String regex = "^[a-zA-Z0-9_]{4,15}$";
		 return RegexValidation(regex, username);
	 }
}
