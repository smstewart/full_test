package org.fulltest.auth;

import javax.servlet.http.Cookie;

public class AuthHandler {
	public static final String authCookieName = "fullTestAuth";
	
	public static boolean isValidCredentials(String username, String password) {
		return true;
	}
	
	public static Cookie createAuthCookie(String username, String password) {
		String cookieVal = getAuthCookieValue(username, password);
		Cookie authCookie = new Cookie(authCookieName, cookieVal);
		authCookie.setPath("/");
		authCookie.setMaxAge(300);
		
		return authCookie;
	}
	
	public static boolean isValidAuthCookie(Cookie cookie) {
		if (cookie.getName() == authCookieName && validateAuthCookie(cookie.getValue())) {
			return true;
		}
		return false;
	}
	
	protected static String getAuthCookieValue(String username, String password) {
		return "A1B2C3";
	}
	
	protected static boolean validateAuthCookie(String value) {
		return true;
	}
}
