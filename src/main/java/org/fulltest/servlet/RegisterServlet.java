package org.fulltest.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fulltest.entity.User;
import org.fulltest.util.ResponseUtil;

public class RegisterServlet extends HttpServlet {
	
	private static final String REGISTER_PAGE = "register.vm";
	static ResponseUtil responseUtil = ResponseUtil.getUtil();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		responseUtil.writeFileToResponse(response, REGISTER_PAGE);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username;
		String password;
		
		username = request.getParameter("username");
		password = request.getParameter("password");
		
		User.createUser(username, password);
		
		response.sendRedirect("/login/");
		return;
	}
}
