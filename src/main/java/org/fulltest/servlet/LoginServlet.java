package org.fulltest.servlet;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fulltest.auth.AuthHandler;
import org.fulltest.util.ResponseUtil;

public class LoginServlet extends HttpServlet {
	static final String LOGIN_PAGE = "login.vm";
	
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
		ResponseUtil.getUtil().writeFileToResponse(response, LOGIN_PAGE);
		//response.addCookie(AuthHandler.createAuthCookie("user", "pass"));
	}
}
