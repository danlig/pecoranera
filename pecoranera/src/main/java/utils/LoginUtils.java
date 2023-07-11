package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginUtils {
	private static final Logger LOGGER = LogManager.getLogger(LoginUtils.class);
	
	private LoginUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String toHash(String password) {
        String hashString = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString((hash[i]&0xFF)|0x100).toLowerCase().substring(1,3);
            }
        } catch (NoSuchAlgorithmException e) {
        	LOGGER.error("Error occurred in context", e);
        }
        return hashString;
    }
}
