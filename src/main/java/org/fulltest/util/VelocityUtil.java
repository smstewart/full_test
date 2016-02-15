package org.fulltest.util;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {
	private static VelocityEngine engine;
	private static VelocityUtil instance;
	
	private VelocityUtil() {
		
	}
	
	public static VelocityUtil getUtil() {
		if (instance == null) {
			instance = new VelocityUtil();
		}
		return instance;
	}
	
	protected VelocityEngine getVelocityEngine() {
		if (engine == null) {
			engine = new VelocityEngine();			
			engine.init(getProperties());
		}
		
		return engine;
	}
	
	private Properties getProperties() {
		Properties prop = new Properties();
		
		prop.put("resource.loader", "class");
		prop.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		return prop;
	}
}
