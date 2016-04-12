package org.fulltest.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.fulltest.auth.AuthHandler;
import org.fulltest.util.ResponseUtil;

public class LoginServlet extends HttpServlet {
	static final String LOGIN_PAGE = "login.vm";
	static final String LOGIN_FAILED = "login_failed";
	static ResponseUtil responseUtil = ResponseUtil.getUtil();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean loggedIn = false;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			cookies = new Cookie[0];
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(AuthHandler.authCookieName)) {
				loggedIn = AuthHandler.isValidAuthCookie(c);
				if (loggedIn && request.getServletPath().contains("/logout")) {
					c.setMaxAge(0); // Expire auth cookie
					response.addCookie(c);
					loggedIn = false; 
				}
			}
		}
		
		if (loggedIn) {
			response.sendRedirect("/test/");
			return;
		}
		responseUtil.writeFileToResponse(response, LOGIN_PAGE);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isValid = AuthHandler.isValidCredentials(username, password);
		if (isValid) {
			response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			response.addCookie(AuthHandler.createAuthCookie(username, password));
			response.sendRedirect(response.encodeRedirectURL("/test/"));
			return;
		} else {
			VelocityContext context = new VelocityContext();
			context.put(LOGIN_FAILED, true);
			responseUtil.writeFileToResponse(response, LOGIN_PAGE, context);
		}
	}
	
	protected MessageDigest getMessageDigest() {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// oh snap
		}
		return md;
	}
}
