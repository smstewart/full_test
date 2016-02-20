package org.fulltest.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.fulltest.auth.AuthHandler;
import org.fulltest.util.ResponseUtil;

public class FullTestServlet extends HttpServlet {
	static ResponseUtil responseUtil = ResponseUtil.getUtil();
	private final static String HOME_PAGE = "home.vm";
	
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = null;
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			response.sendRedirect("/login/");
			return;
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(AuthHandler.authCookieName)) {
				username = c.getValue();
			}
		}
		if (username == null) {
			response.sendRedirect("/login/");
			return;
		}
		VelocityContext context = new VelocityContext();
		context.put("username", username);
		responseUtil.writeFileToResponse(response, HOME_PAGE, context);
	}
	
	private String toJson(HashMap<String, Integer> map) {
		boolean firstElement = true;
		StringBuilder sb = new StringBuilder("{");
		for (String key : map.keySet()) {
			if (!firstElement) {
				sb.append(",");
			} else {
				firstElement = false;
			}
			sb.append(key);
			sb.append(":");
			sb.append(String.valueOf(map.get(key)));
		}
		sb.append("}");
		return sb.toString();
	}
}
