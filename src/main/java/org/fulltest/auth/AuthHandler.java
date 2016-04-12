package org.fulltest.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;

import org.fulltest.entity.User;

public class AuthHandler {
	public static final String authCookieName = "fullTestAuth";
	
	public static boolean isValidCredentials(String username, String password) {
		User user = User.getUser(username);
		return (user != null && user.getPassword().equals(password));
		 /* Use once password is hashed
		MessageDigest md = getMessageDigest();
		md.update(password.getBytes());
		byte[] output = md.digest();
		
		if (username.equals("failme") || password.equals("failme")) {
			return false;
		}
		return true;*/ 
	}
	
	protected static MessageDigest getMessageDigest() {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			
		}
		return md;
	}
	
	public static Cookie createAuthCookie(String username, String password) {
		String cookieVal = getAuthCookieValue(username, password);
		Cookie authCookie = new Cookie(authCookieName, cookieVal);
		authCookie.setPath("/");
		authCookie.setMaxAge(300);
		
		return authCookie;
	}
	
	public static boolean isValidAuthCookie(Cookie cookie) {
		if (cookie.getName().equals(authCookieName) && validateAuthCookie(cookie.getValue())) {
			return true;
		}
		return false;
	}
	
	protected static String getAuthCookieValue(String username, String password) {
		return username;
	}
	
	protected static boolean validateAuthCookie(String value) {
		return true;
	}
}
