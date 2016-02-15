package org.fulltest.servlet;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fulltest.auth.AuthHandler;

public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean loggedIn = false;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			cookies = new Cookie[0];
		}
		for (Cookie c : cookies) {
			if (c.getName() == AuthHandler.authCookieName) {
				loggedIn = AuthHandler.isValidAuthCookie(c);
			}
		}
		
		if (loggedIn) {
			response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.encodeRedirectURL("/test/");
			return;
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		response.getWriter().println("Login here k?");
		response.addCookie(AuthHandler.createAuthCookie("user", "pass"));
	}
}
