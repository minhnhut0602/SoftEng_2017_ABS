package abs.view;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.model.User;

/**
 * The main class that runs the console based program.
 */
public class Main {

	/** The Utilities object. */
	static Utilities utils = new Utilities();

	/** The user Auth object. */
	static UserAuth userAuth;

	/** The menu object. */
	static Menu menu;

	/** The exit status. */
	static int status = 0;

	/**
	 * The main method.
	 * 
	 * <p>
	 * Runs console version.
	 * </p>
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		utils.readData();
		userAuth = new UserAuth(utils);
		menu = new Menu(utils, userAuth);
		while (status == 0) {
			// Initiates the menu.
			// Runs until status is not 0.
			mainMenu();
		}

	}

	/**
	 * Controls the Main menu.
	 * 
	 * @see Menu#mainMenu()
	 */
	private static void mainMenu() {
		int select = menu.mainMenu();
		System.out.println();
		switch (select) {
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		default:
			System.out.println("Invalid Return from the Main Menu");
		}

	}

	/**
	 * Controls the register menu.
	 * 
	 * @see Menu#registerMenu()
	 */
	private static void register() {
		User select = menu.registerMenu();
		System.out.println();
		if (select == null) {
			mainMenu();
		} else if (select.getClass().getName().equals("Owner")) {
			ownerDash();
		} else // A Customer
			businessSelect();
	}

	/**
	 * Controls the Login menu.
	 * 
	 * @see Menu#loginMenu()
	 */
	private static void login() {
		User select = menu.loginMenu();
		System.out.println();
		if (select == null) {
			mainMenu();
		} else if (select.getClass().getName().equals("Owner")) {
			ownerDash();
		} else // A Customer
			businessSelect();

	}

	/**
	 * Controls the business select menu.
	 * 
	 * @see Menu#businessSelect()
	 */
	private static void businessSelect() {
		menu.businessSelect();
		System.out.println();
		customerDash();
	}

	/**
	 * Controls the customer dashboard.
	 * 
	 * @see Menu#customerDashboard()
	 */
	private static void customerDash() {
		int select = menu.customerDashboard();
		System.out.println();
		switch (select) {
		case 1:
			viewAvailablEBookings();
			break;
		case 2:
			viewMyBookings();
			break;
		case 3: // logout
			userAuth.setActiveUser(null);
			mainMenu();
			break;
		case 4:
			businessInfo();
			break;
		case 5:
			exit();
			break;
		default:
			System.out.println("Invalid Return from the Customer Dashboard");
		}
	}

	/**
	 * Controls the Owner dashboard.
	 * 
	 * @see Menu#ownerDashboard()
	 */
	private static void ownerDash() {
		// pass to menu class
		int select = menu.ownerDashboard();
		System.out.println();
		switch (select) {
		case 1: // view bookings
			menu.getActiveBusiness().displayBookings();
			break;
		case 2: // logout
			userAuth.setActiveUser(null);
			mainMenu();
			break;
		case 3:
			businessInfo();
			break;
		case 4:
			exit();
			break;
		default:
			System.out.println("Invalid Return from the Owner Dashboard");
		}

	}

	/**
	 * Controls the customers bookings menu.
	 * 
	 * @see Menu#myBookings()
	 */
	private static void viewMyBookings() {
		// pass to the menu class
		int select = menu.myBookings();
		System.out.println();
		switch (select) {
		case 1: // remove a booking
			menu.cancelBooking();
			customerDash();
			break;
		case 2:// go back to dashboard
			customerDash();
			break;
		default:
			System.out.println("Invalid Return from Customer Bookings");
		}

	}

	/**
	 * Controls view available bookings.
	 * 
	 * @see Menu#bookingOptions()
	 */
	private static void viewAvailablEBookings() {
		// pass to the menu class
		int select = menu.bookingOptions();
		System.out.println();
		switch (select) {
		case 1: // add a booking
			menu.addBooking();
			customerDash();
			break;
		case 2: // return to the dashboard
			customerDash();
			break;
		default:
			System.out.println("Invalid Return from menu");
		}

	}

	/**
	 * Control Business info screen.
	 * 
	 * @see Menu#businessInfo()
	 */
	private static void businessInfo() {

		int select = menu.businessInfo();
		System.out.println();
		switch (select) {
		case 1:
			customerDash();
			break;
		case 2:
			exit();
			break;
		default:
			System.out.println("Invalid Return from menu");
		}
	}

	/**
	 * Exit program.
	 * 
	 * <i>Sets status to -1</i>
	 */
	private static void exit() {
		System.out.println("Saving data to file...");
		int result = utils.writeData(utils.getBusiness(), utils.getCustomers());
		if (result != 0) {
			System.out.print("Error saving data.\n");
		} else
			System.out.print("Success!\n");
		System.out.println("Goodbye");
		status = -1;

	}

}
