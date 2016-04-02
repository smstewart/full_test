package org.fulltest.entity;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.Test;

public class UserTest {
	@Test
	public void testCreateUser() {
		Session sess = new Configuration().configure().buildSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();
		
		User u1 = new User();
		u1.setId(1);
		u1.setUsername("user one");
		u1.setPassword("pass one");
		
		sess.persist(u1);
		
		t.commit();
		sess.close();
	}
}
