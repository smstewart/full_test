package org.fulltest.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

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
		writeFileToResponse(response, fileName, new VelocityContext());
	}
	
	public void writeFileToResponse(
			HttpServletResponse response, 
			String fileName, 
			VelocityContext context) 
	{
		try {
			String resourceName = "/templates/"+fileName;
			VelocityEngine ve = VelocityUtil.getUtil().getVelocityEngine();
			Template t = ve.getTemplate(resourceName);	
			
			t.merge(context, response.getWriter());

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/html");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}
}
