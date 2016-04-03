package org.fulltest.entity;

import org.fulltest.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class UserTest {
	@Test
	public void testCreateUser() {
		Session sess = SessionUtil.getUtil().getSession();
		
		Transaction t = sess.beginTransaction();
		
		User u1 = User.createUser("user one", "pass one");
		
		User u2 = User.createUser("name two", "pass two");
		
		sess.persist(u1);
		sess.persist(u2);
		
		t.commit();
		sess.close();
	}
}
