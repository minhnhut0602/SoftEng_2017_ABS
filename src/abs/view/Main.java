package abs.view;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.model.User;

/**
 * The main class that runs the console based program.
 */
public class Main {
	static Utilities utils = new Utilities();
	static UserAuth userAuth = new UserAuth(utils);
	static Menu menu = new Menu(utils, userAuth);

	static int status = 0;

	public static void main(String[] args) {

		while (status == 0) {
			// runs the main menu until exit == true
			// each sub-menu is called by the switch statements inside each
			// static method in this class
			mainMenu();
		}

	}

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	private static void businessSelect() {
		menu.businessSelect();
		System.out.println();
		customerDash();
	}

	/**
	 * 
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
	 * 
	 */
	private static void ownerDash() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private static void viewMyBookings() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private static void viewAvaliableBookings() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
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
	 * 
	 */
	private static void exit() {
		System.out.println("Goodbye");
		status = -1;

	}
}
