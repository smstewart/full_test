package org.fulltest.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FullTestServlet extends HttpServlet {
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>Hello from FullTest</h1>");
		HashMap<String, Integer> testMap = new HashMap<String, Integer>();
		testMap.put("one", 1);
		testMap.put("two", 2);
		response.getWriter().println(toJson(testMap));
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
