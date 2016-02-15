package org.fulltest.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	private static ResponseUtil instance;
	
	private ResponseUtil() {
		
	}
	
	public static ResponseUtil getUtil() {
		if (instance == null) {
			instance = new ResponseUtil();
		}
		return instance;
	}
	
	public void writeFileToResponse(HttpServletResponse response, String fileName) {
		try {
			String resourceName = fileName.startsWith("/") ? fileName : "/"+fileName;
			
			InputStream is = getClass().getResourceAsStream(resourceName);
			if (is == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			byte[] buffer = new byte[10240];
			for (int length = 0; ((length = is.read(buffer)) > 0);) {
				response.getOutputStream().write(buffer, 0, length);
			}
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/html");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
