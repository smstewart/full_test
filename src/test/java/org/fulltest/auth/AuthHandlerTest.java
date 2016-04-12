package org.fulltest.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fulltest.entity.User;
import org.junit.Test;

public class AuthHandlerTest {
	@Test
	public void testIsValidCredentials() {
		boolean shouldBeFalse = AuthHandler.isValidCredentials("fakeUser", "password");
		assertFalse(shouldBeFalse);
		
		User user = User.createUser("realUser", "password");
		boolean shouldBeTrue = AuthHandler.isValidCredentials(user.getUsername(), user.getPassword());
		assertTrue(shouldBeTrue);
	}
}
