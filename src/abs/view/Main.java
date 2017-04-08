package abs.view;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.model.User;

// TODO: Auto-generated Javadoc
/**
 * The main class that runs the console based program.
 */
public class Main {

	/** The utils. */
	static Utilities utils = new Utilities();

	/** The user auth. */
	static UserAuth userAuth = new UserAuth(utils);

	/** The menu. */
	static Menu menu = new Menu(utils, userAuth);

	/** The status. */
	static int status = 0;

	/**
	 * The main method.
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
	 * Main menu.
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
	 * Register.
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
	 * Login.
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

		// TODO for owner dash check class of user.
	}

	/**
	 * Business select.
	 */
	private static void businessSelect() {
		menu.businessSelect();
		System.out.println();
		customerDash();
	}

	/**
	 * Customer dash.
	 */
	private static void customerDash() {
		int select = menu.customerDashboard();
		System.out.println();
		switch (select) {
		case 1:
			viewAvaliableBookings();
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
	 * Owner dash.
	 */
	private static void ownerDash() {
		// TODO Auto-generated method stub

	}

	/**
	 * View my bookings.
	 */
	private static void viewMyBookings() {
		// TODO Auto-generated method stub

	}

	/**
	 * View avaliable bookings.
	 */
	private static void viewAvaliableBookings() {
		// TODO Auto-generated method stub

	}

	/**
	 * Business info.
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
	 * Exit.
	 */
	private static void exit() {
		System.out.println("Goodbye");
		status = -1;

	}
}