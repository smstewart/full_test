package org.fulltest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fulltest.auth.AuthHandler;

public class AuthServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (AuthHandler.isValidCredentials(username, password)) {
			Cookie authCookie = AuthHandler.createAuthCookie(username, password);
			response.addCookie(authCookie);
			response.encodeRedirectURL("/test/");
		} else {
			response.encodeRedirectURL("/login/");
		}
	}
}
