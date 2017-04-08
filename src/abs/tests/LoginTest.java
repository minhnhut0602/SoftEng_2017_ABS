/**
 * 
 */
package abs.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import abs.controller.UserAuth;
import abs.model.Customer;

/**
 * 
 * This tests the UserAuth class. Checks login a user Checks register a user
 *
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

	@Test
	public void alreadyRegisteredUser() {
		// try registering a use who is already registered

		auth.registerUser(name, email, address, phone, pass);
		assertFalse(auth.registerUser(name, email, address, phone, pass));
	}

	@Test
	public void loginWithoutRegistering() {
		// use an invalid username and password
		assertFalse(auth.authUser("unregistered@email.com", "unregisteredPassword12!"));
	}

	@Test
	public void loginWithRegisteredUser() {
		// use a valid username and password

		// not sure if the testRegister is persistent so i registered the user
		// again. but that might fail this one
		auth.registerUser(name, email, address, phone, pass);
		assertTrue(auth.authUser(email, pass));
	}

	@Test
	public void noPasswordRegister() {
		// try to register without a password
		assertFalse(auth.registerUser(name, email, address, phone, ""));
	}

	@Test
	public void noUsernameRegister() {
		// try registering without username

		assertFalse(auth.registerUser("", email, address, phone, pass));
	}

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


	/**
	 * Test user registration, registerUser method
	 * 
	 */
	@Test
	public void testRegister() {

		boolean result = auth.registerUser(name, "email@email", "12345", address, phone);
		assertTrue(result == true);
	}

}
