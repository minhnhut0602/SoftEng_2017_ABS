/**
 * 
 */
package abs.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.exceptions.PasswordInvalidException;
import abs.model.Business;
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
	// private Owner ownerData;
	// private List<User> customerData;
	private String dummyIn;
	private PrintStream stdout = System.out;
	private InputStream stdin = System.in;
	private UserAuth userAuth;
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
		// ownerData = dataBus.get(0).getOwner();

		// Customer data for login test
		// customerData = utils.getCustomers();

		// New menu object should set up the menu object

		System.setOut(new PrintStream(outContent));
		userAuth = new UserAuth();
	}

	@Test
	public void testMenuDispayMainMenu() {

		String dummyIn = "1\r\n"; // Sending dummy input to menu
		Scanner fakeIn = new Scanner(new ByteArrayInputStream(dummyIn.getBytes()));
		menu = new Menu(fakeIn, utils, userAuth);
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
		Scanner fakeIn = new Scanner(new ByteArrayInputStream(dummyIn.getBytes()));
		menu = new Menu(fakeIn, utils, userAuth);

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
		Scanner fakeIn = new Scanner(new ByteArrayInputStream(dummyIn.getBytes()));
		menu = new Menu(fakeIn, utils, userAuth);

		User loginReturn = menu.registerMenu();

		// Test what method Prints
		String expectedOutputa = "Welcome to the Appointment Booking System\n"
				+ "To Register Please enter your details seperated by a comma/n"
				+ "e.g. name,email,address,phone,password/n";
		assertEquals(expectedOutputa, outContent.toString());

	}

	@Test
	public void testMenuDispayBusinessSelection() {
		dummyIn = "1"; // Sending dummy input to menu
		Scanner fakeIn = new Scanner(new ByteArrayInputStream(dummyIn.getBytes()));
		menu = new Menu(fakeIn, utils, userAuth);
		menu.businessSelect(); // Prints main menu, prompts for
												// login
		// or register.

		// TEST what method prints
		String expectedOutput = "Welcome to the Appointment Booking System\n" + "Please select an option:\n"
				+ "1. Daniel's Dentists\n" + "2. Cory's Counselling\n" + "3. Marco's Manicures\n"
				+ "4. Sohum's Salon\n";

		assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testMenuDispayOwnerDashboard() {

		fail("Implemented in Part B of assignmnet"); // TODO in part B of
														// assignment

	}

	@Test
	public void testMenuDispayCustomerDashboard() {
		dummyIn = "1"; // Sending dummy input to menu
		Scanner fakeIn = new Scanner(new ByteArrayInputStream(dummyIn.getBytes()));
		menu = new Menu(fakeIn, utils, userAuth);
		int mainMenu = menu.customerDashboard(); // Prints main menu,
													// prompts for login
		// or register.
		String customerLoginEm = "stacy.d@gmail.com";

		String customerLoginPa = "stacypassword";
		UserAuth ua = new UserAuth();
		boolean userLogin = false;
		try {
			userLogin = ua.authUser(customerLoginEm, customerLoginPa);
		} catch (PasswordInvalidException e) {
			fail("user not authed");
		}

		if (!userLogin) {
			fail("User failed to login");
		}

		String custName = "Stacy";

		// TEST what method prints
		String expectedOutput = "Welcome " + custName + " to the Appointment Booking System\n"
				+ "Please select an option:\n" + "1. View avaliable bookings\n" + "2. View my bookings\n"
				+ "3. Logout\n" + "4. View Business info\n" + "4. Exit\n";

		assertEquals(expectedOutput, outContent.toString()); // Compared the
																// output of
	}

	@After
	public void tearDown() {
		// reset scanner
		System.setOut(stdout);
		System.setIn(stdin);
	}
}
