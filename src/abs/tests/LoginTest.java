/**
 * 
 */
package abs.tests;

import static org.junit.Assert.*;

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
	protected int phone;

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
		phone = 396657777;
	}

	/**
	 * Test Login, authUser method
	 */
	@Test
	public void testLogin() {
		boolean result = auth.authUser(email, pass);
		assertTrue(result == true);
	}

	/**
	 * Test user registration, registerUser method
	 * 
	 */
	@Test
	public void testRegister() {

		boolean result = auth.registerUser(new Customer(name, email, address, phone, pass));
		assertTrue(result == true);
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
		auth.registerUser(new Customer(name, email, address, phone, pass));
		assertTrue(auth.authUser(email, pass));
	}

	@Test
	public void noUsernameRegister() {
		// try registering without username

		assertFalse(auth.registerUser(new Customer("", email, address, phone, pass)));
	}

	@Test
	public void noPasswordRegister() {
		// try to register without a password
		assertFalse(auth.registerUser(new Customer(name, email, address, phone, "")));
	}

	@Test
	public void alreadyRegisteredUser() {
		// try registering a use who is already registered

		auth.registerUser(new Customer(name, email, address, phone, pass));
		assertFalse(auth.registerUser(new Customer(name, email, address, phone, pass)));
	}

}
