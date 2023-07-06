package utils;

import java.util.regex.Pattern;

public class ValidatorUtils {
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
		               + "(?=.*[@#$%^&+=])"
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
