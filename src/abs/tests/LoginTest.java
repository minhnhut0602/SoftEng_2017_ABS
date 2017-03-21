/**
 * 
 */
package abs.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import abs.controller.UserAuth;
import abs.model.Customer;

/**
 * @author Daniel Caddaye
 * 
 *         This tests the UserAuth class. Checks login a user Checks register a
 *         user
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
	protected double phone;

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
		phone = 0396657777D;
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

}
