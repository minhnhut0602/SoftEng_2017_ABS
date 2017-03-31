/**
 * 
 */
package abs.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abs.controller.Utilities;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Owner;
import abs.model.User;
import abs.view.Menu;

/**
 * This test verifies the menu is is displayed correctly and responds as
 * expected.
 * 
 * @see abs.view.Menu Menu
 *
 */
public class MenuTest {

	private Utilities utils;
	private int dataRead;
	private Menu menu;
	private List<Business> dataBus;
	private Owner ownerData;
	private List<User> customerData;
	String dummyIn;

	// Scanners
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		utils = new Utilities();// look for default data
		dataRead = utils.readData(); // import data

		if (dataRead != 0) { // checks for read error
			fail("Data import error code: " + dataRead);
		}

		// Stores business data from file to compare with menu output
		dataBus = utils.getBusiness();

		// Owner data for login tests
		ownerData = dataBus.get(0).getOwner();

		// Customer data for login test
		customerData = utils.getCustomers();

		menu = new Menu(); // New menu object should set up the menu object

		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testMenuDispayMainMenu() {
		dummyIn = "0"; // Sending dummy input to menu
		System.setIn(new ByteArrayInputStream(dummyIn.getBytes()));

		int mainMenu = menu.mainMenu(); // Prints main menu, prompts for login
		// or register.

		// TEST what method prints
		String expectedOutput = "Welcome to the Appointment Booking System\n" + "Please select an option:\n"
				+ "1. Login\n" + "2. Register\n";

		assertEquals(expectedOutput, outContent.toString()); // Compared the
																// output of

	}

	@Test
	public void testMenuDispayLoginScreen() {

		// Simulate input of correct email and password
		dummyIn = "admin@danielsdentist.com,root"; // Sending dummy input to
													// menu
		System.setIn(new ByteArrayInputStream(dummyIn.getBytes()));

		User loginReturn = menu.loginMenu();

		// Test what method Prints
		String expectedOutputa = "Welcome to the Appointment Booking System\n"
				+ "Please enter your email and password seperated by a comma/n" + "e.g. email,password/n";
		assertEquals(expectedOutputa, outContent.toString());

	}

	@Test
	public void testMenuDispayRegisterScreen() {
		// Simulate input of name,email,address,phone,password
		dummyIn = "Dave,dave@gmail.com,123 test St,0404044044,root";

		System.setIn(new ByteArrayInputStream(dummyIn.getBytes()));

		User loginReturn = menu.registerMenu();

		// Test what method Prints
		String expectedOutputa = "Welcome to the Appointment Booking System\n"
				+ "To Register Please enter your details seperated by a comma/n"
				+ "e.g. name,email,address,phone,password/n";
		assertEquals(expectedOutputa, outContent.toString());

	}

	@Test
	public void testMenuDispayBusinessSelection() {

		fail("Not yet implemented"); // TODO

	}

	@Test
	public void testMenuDispayOwnerDashboard() {

		fail("Implemented in Part B of assignmnet"); // TODO in part B of
														// assignment

	}

	@Test
	public void testMenuDispayCustomerDashboard() {

		fail("Not yet implemented"); // TODO

	}

	@After
	public void tearDown() {
		// reset scanner
		System.setOut(null);
		System.setIn(System.in);
	}
}
