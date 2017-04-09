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
	static UserAuth userAuth = new UserAuth(utils);

	/** The menu object. */
	static Menu menu = new Menu(utils, userAuth);

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

		while (status == 0) {
			// runs the main menu until exit == true
			// each sub-menu is called by the switch statements inside each
			// static method in this class
			mainMenu();
		}

	}

	/**
	 * Control Main menu.
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
			System.out.println("Invalid Return from mainMenu");
		}

	}

	/**
	 * Control register menu.
	 * 
	 * @see Menu#registerMenu()
	 */
	private static void register() {
		User select = menu.registerMenu();
		System.out.println();
		if (select != null) {
			businessSelect();
		} else
			mainMenu();

	}

	/**
	 * Control Login menu.
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
		} else
			businessSelect();

	}

	/**
	 * Control business select.
	 * 
	 * @see Menu#businessSelect()
	 */
	private static void businessSelect() {
		menu.businessSelect();
		System.out.println();
		customerDash();
	}

	/**
	 * Control customer dashboard.
	 * 
	 * @see Menu#customerDashboard()
	 */
	private static void customerDash() {
		int select = menu.customerDashboard();
		System.out.println();
		switch (select) {
		case 1:
			viewAvailableBookings();
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
			System.out.println("Invalid Return from menu");
		}
	}

	/**
	 * Owner dashboard.
	 */
	private static void ownerDash() {
		// TODO part B

	}

	/**
	 * View customers my bookings.
	 */
	private static void viewMyBookings() {
		// TODO Auto-generated method stub

	}

	/**
	 * View available bookings.
	 */
	private static void viewAvailableBookings() {
		// TODO Auto-generated method stub

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
	 */
	private static void exit() {
		System.out.println("Goodbye");
		status = -1;

	}

}
