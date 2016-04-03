package org.fulltest.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
	private static SessionFactory factory;
	private static SessionUtil instance;
	
	private SessionUtil() {
		factory = getFactory();
	}
	
	public static SessionUtil getUtil() {
		if (instance == null) {
			instance = new SessionUtil();
		}
		return instance;
	}
	private SessionFactory getFactory() {
		return new Configuration().configure().buildSessionFactory();
	}
	
	public Session getSession() {
		return factory.openSession();
	}
}
