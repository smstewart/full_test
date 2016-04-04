package org.fulltest.entity;

import org.junit.Test;

public class UserTest {
	@Test
	public void testCreateUser() {
		User u1 = User.createUser("user one", "pass one");
		
		User u2 = User.createUser("name two", "pass two");
	}
}
