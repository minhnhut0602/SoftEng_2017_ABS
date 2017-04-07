package abs.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import abs.controller.UserAuth;
import abs.exceptions.PasswordInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;

/**
 * This tests the UserAuth class.
 * 
 * <p>
 * Tests: login a user, register a user with valid and invalid data.
 * </p>
 *
 * @see abs.controller.UserAuth UserAuth
 */
public class LoginTest {

	// info for a customer
	protected String address;

	protected UserAuth auth;
	protected String email;
	// info for a user
	protected String name;

	protected String pass;
	protected String phone;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		auth = new UserAuth();
		name = "TestName";
		email = "test@gmail.com";
		pass = "TestPass123";
		address = "123 Fake St, Melbourne";
		phone = "0396657777";
	}

	@Test
	public void alreadyRegisteredUser() {
		// try registering a use who is already registered

		try {
			auth.registerUser(name, email, address, phone, pass);
		} catch (RegistrationNonUniqueException e) {
			fail("Existing Error");
		} catch (RegistrationValidationException e) {
			fail("Format error");
		}

		try {
			auth.registerUser(name, email, address, phone, pass);
		} catch (RegistrationNonUniqueException e) {
			assertTrue(true);
		} catch (RegistrationValidationException e) {
			fail("Format error");
		}
	}

	@Test
	public void loginWithoutRegistering() {
		// use an invalid username and password
		try {
			auth.authUser("unregistered@email.com", "unregisteredPassword12!");
		} catch (PasswordInvalidException e) {
			assertTrue(true);
		}
	}

	@Test
	public void loginWithRegisteredUser() {
		// use a valid username and password

		// not sure if the testRegister is persistent so i registered the user
		// again. but that might fail this one
		try {
			auth.registerUser(name, email, address, phone, pass);
		} catch (RegistrationNonUniqueException e) {
			fail("Existing Error");
		} catch (RegistrationValidationException e) {
			fail("Format error");
		}

		try {
			assertTrue(auth.authUser(email, pass));
		} catch (PasswordInvalidException e) {
			fail("Credentials error");
		}
	}

	@Test
	public void noPasswordRegister() {
		// try to register without a password
		try {
			auth.registerUser(name, email, address, phone, "");
		} catch (RegistrationNonUniqueException e) {
			fail("Existing Error");
		} catch (RegistrationValidationException e) {
			assertTrue(true);
		}
	}

	@Test
	public void noUsernameRegister() {
		// try registering without username

		try {
			assertFalse(auth.registerUser("", email, address, phone, pass));
		} catch (RegistrationNonUniqueException e) {
			fail("Existing Error");
		} catch (RegistrationValidationException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test Login, authUser method
	 */
	@Test
	public void testLogin() {
		boolean result = false;
		try {
			result = auth.authUser(email, pass);
		} catch (PasswordInvalidException e) {
			fail("Credentials error");
		}
		assertTrue(result == true);
	}

	/**
	 * Test user registration, registerUser method
	 * 
	 */
	@Test
	public void testRegister() {

		boolean result = false;
		try {
			result = auth.registerUser(name, email, address, phone, pass);
		} catch (RegistrationNonUniqueException e) {
			fail("Existing Error");
		} catch (RegistrationValidationException e) {
			fail("Format Error");
		}
		assertTrue(result == true);
	}

}
