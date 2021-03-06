package abs.view;

import java.util.List;
import java.util.Scanner;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.MenuInputException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Business;
import abs.model.Customer;
import abs.model.User;

/**
 * Class that prints the menu screens, accepts and validates inputs then returns
 * results.
 * 
 * @since Alpha
 * @version 1.3
 * @deprecated
 */
public class Menu {

	/** The sc. */
	private Scanner sc;

	/** The utils. */
	private Utilities utils;

	/** The active business. */
	private Business activeBusiness;

	/** The user auth. */
	private UserAuth userAuth;

	/**
	 * This constructor used the default scanner.
	 * 
	 * @param utils
	 *            A utilities object that loads/stores the data
	 * @param userAuth
	 *            a UserAuth object that verifies and stores active user info.
	 * 
	 * 
	 * @see #Menu(Scanner, Utilities, UserAuth)
	 */
	public Menu(Utilities utils, UserAuth userAuth) {
		super();
		this.sc = new Scanner(System.in);
		this.utils = utils;
		this.userAuth = userAuth;
	}

	/**
	 * For default scanner see other constructor.
	 * 
	 * @param sc
	 *            a specified scanner, used for testing
	 * @param utils
	 *            A utilities object that loads/stores the data
	 * @param userAuth
	 *            a UserAuth object that verifies and stores active user info.
	 * 
	 * @see #Menu(Utilities, UserAuth)
	 */
	public Menu(Scanner sc, Utilities utils, UserAuth userAuth) {
		super();
		this.sc = sc;
		this.utils = utils;
		this.userAuth = userAuth;
	}

	/**
	 * Gets the active business.
	 *
	 * @return the business selected by the user
	 */
	public Business getActiveBusiness() {
		return activeBusiness;
	}

	/**
	 * Prints the main menu prompting for login or register.
	 * 
	 * 
	 * @return the int value of the users selection.
	 */
	public int mainMenu() {

		String selection;
		boolean valid = false;

		System.out.printf("Welcome to the Appointment Booking System\n" + "Please select an option:\n" + "1. Login\n"
				+ "2. Register\n");
		selection = sc.next();

		while (valid != true) {// isnt a valid selection
			try {
				if (Integer.parseInt(selection) != 1 && Integer.parseInt(selection) != 2) {
					throw new MenuInputException(selection);
				} else {
					valid = true;
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}
		return Integer.parseInt(selection);
	}

	/**
	 * Prints the login screen. Accepts credentials and attempts to log the user
	 * in.
	 * 
	 * @return the logged in user, else null
	 */
	public User loginMenu() {
		String selection;

		boolean valid = false;

		System.out.printf("Welcome to the Appointment Booking System\n"
				+ "Please enter your email and password seperated by a comma\n" + "e.g. email,password\n");
		System.out.printf("To return to the main menu enter exit\n");
		selection = sc.next();

		// if the user wants to return, exit loop without login
		if (selection.equals("exit") || selection.equals("Exit") || selection.equals("EXIT")) {
			valid = true;
		}

		while (valid != true) {

			// if the user wants to return, exit loop without login
			if (selection.equals("exit") || selection.equals("Exit") || selection.equals("EXIT")) {
				valid = true;
			}

			// Splits the input into email and password
			String[] selectionAr = selection.split(",");
			try {
				if (selectionAr == null) { // check for null input
					throw new MenuInputException(selection);

				} else {
					if (selectionAr.length == 2) { // check there are two string
						if (userAuth.authUser(selectionAr[0], selectionAr[1])) { // attempt
																					// to
																					// login
							valid = true; // if login true then return
						}
					} else if (!(selection.equals("exit") || selection.equals("Exit") || selection.equals("EXIT"))) { // any
																														// invalid
																														// entry
						throw new MenuInputException(selection);
					}
				}
			} catch (MenuInputException e) { // invalid entry
				System.out.printf("Sorry " + e.getInputS() + " is an invalid selection, please try again\n");
				System.out.printf("To return to the main menu enter exit\n");
				selection = sc.next();

			} catch (CredentialsInvalidException e) { // if userAuth thorws
														// password invalid.
				System.out.printf(e.getMessage());
				System.out.printf("\nTo return to the main menu enter exit\n");
				selection = sc.next();
			}
		}

		return userAuth.getActiveUser();
	}

	/**
	 * Prints the registration screen. Accepts user registration info and
	 * attempts to register an log that user in.
	 * 
	 * 
	 * @return the newly registered and logged in user, else null
	 */
	public User registerMenu() {
		String selection;

		boolean valid = false;

		System.out.printf("Welcome to the Appointment Booking System\n"
				+ "To Register Please enter your details seperated by a comma\n"
				+ "e.g. name,email,password,address,phone\n");
		System.out.printf("To return to the main menu enter exit\n");
		selection = sc.nextLine();

		// if the user wants to return, exit loop without login
		if (selection.equals("exit") || selection.equals("Exit") || selection.equals("EXIT")) {
			valid = true;
		}

		while (valid != true) {

			// if the user wants to return, exit loop without login
			if (selection.equals("exit") || selection.equals("Exit") || selection.equals("EXIT")) {
				valid = true;
			}

			// Splits the input into each field
			String[] selectionAr = selection.split(",");
			try {
				if (selectionAr == null) { // check for null input
					throw new MenuInputException(selection);

				} else {
					if (selectionAr.length == 5) { // check there are 5 string

						// Attempt to register
						if (userAuth.registerUser(selectionAr[0], selectionAr[1], selectionAr[2], selectionAr[3],
								selectionAr[4])) {

							valid = true; // if register true then return
						}
					} else if (!(selection.equals("exit") || selection.equals("Exit") || selection.equals("EXIT"))) { // any
						// invalid
						// entry
						throw new MenuInputException(selection);
					}
				}
			} catch (MenuInputException e) { // invalid entry
				System.out.printf("Sorry " + e.getInputS() + " is an invalid selection, please try again\n");
				System.out.printf("To return to the main menu enter exit\n");
				selection = sc.nextLine();

				// If the registered info is already registered
			} catch (RegistrationNonUniqueException e) {
				System.out.printf(e.getMessage());
				System.out.printf("\nTo return to the main menu enter exit\n");
				selection = sc.nextLine();

				// if there are 5 fields but one or more is invalid
			} catch (RegistrationValidationException e) {
				System.out.printf(e.getMessage());
				System.out.printf("\nTo return to the main menu enter exit\n");
				selection = sc.nextLine();
			}
		}

		return userAuth.getActiveUser();
	}

	/**
	 * Prints the business selection screen. Upon a valid selection the active
	 * business is set as the selection.
	 * 
	 */
	public void businessSelect() {
		String selection;

		boolean valid = false;

		System.out.printf("Welcome to the Appointment Booking System\n" + "Please select an option:\n");
		List<Business> businessses = utils.getBusiness();
		int[] options = new int[businessses.size()];
		for (int i = 0; i < businessses.size(); i++) {
			System.out.printf((i + 1) + ". " + businessses.get(i).getName() + "\n");
			options[i] = i + 1;
		}

		selection = sc.next();

		while (!valid) {
			try {

				for (int opt : options) {
					if (Integer.parseInt(selection) == opt) {
						this.activeBusiness = businessses.get(opt - 1);
						valid = true;
					}
				}

				if (!valid) {
					throw new MenuInputException(selection);
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}

	}

	/**
	 * Prints the customer dashboard for the active user and the active
	 * business.
	 * 
	 * 
	 * @return the int value of the users selection.
	 */
	public int customerDashboard() {
		String selection;
		User customer = userAuth.getActiveUser(); // static call to the active
													// user.
		boolean valid = false;

		System.out.printf("Welcome " + customer.getName() + " to the Appointment Booking System\n"
				+ "Please select an option:\n" + "1. View avaliable bookings\n" + "2. View my bookings\n"
				+ "3. Logout\n" + "4. View Business info\n" + "5. Exit\n");
		selection = sc.next();
		int[] options = { 1, 2, 3, 4, 5 };
		while (valid != true) {
			try {

				for (int opt : options) {
					if (Integer.parseInt(selection) == opt) {
						valid = true;
					}
				}

				if (!valid) {
					throw new MenuInputException(selection);
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}
		return Integer.parseInt(selection);
	}

	/**
	 * Prints the Business info for the active business.
	 *
	 * @return the int value of the selection, 1 back, 2 exit
	 */
	public int businessInfo() {
		String selection;

		boolean valid = false;

		System.out.printf("Welcome to the Appointment Booking System\n " + activeBusiness.getName() + "\n "
				+ activeBusiness.getDesc() + "\n Phone number: " + activeBusiness.getPhone() + "\n Address: "
				+ activeBusiness.getAddress() + "\nPlease select an option:\n" + "1. Return\n" + "2. Exit\n");
		selection = sc.next();

		while (valid != true) {
			try {
				if (Integer.parseInt(selection) != 1 && Integer.parseInt(selection) != 2) {
					throw new MenuInputException(selection);
				} else {
					valid = true;
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}
		return Integer.parseInt(selection);
	}

	/**
	 * Print the available bookings and Booking options.
	 *
	 * @return the int value of the users selection.
	 */
	public int bookingOptions() {
		String selection;

		boolean valid = false;

		// display Business available bookings
		activeBusiness.displayBookings();

		// user can book a time or go back
		System.out.printf("\nWould you like to: \n1.Book an Appointment\n2.Go Back\n");

		selection = sc.next();

		while (valid != true) {
			try {
				if (Integer.parseInt(selection) != 1 && Integer.parseInt(selection) != 2) {
					throw new MenuInputException(selection);
				} else {
					valid = true;
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}

		return Integer.parseInt(selection);
	}

	/**
	 * Prints the customers booking and options.
	 *
	 * @return the int value of the users selection
	 */
	public int myBookings() {

		String selection;

		boolean valid = false;

		// display the user's bookings
		((Customer) userAuth.getActiveUser()).viewBookings();

		System.out.printf("\nWould you like to: \n1.Remove a Booking\n2.Go Back\n");
		selection = sc.next();

		while (valid != true) {
			try {
				if (Integer.parseInt(selection) != 1 && Integer.parseInt(selection) != 2) {
					throw new MenuInputException(selection);
				} else {
					valid = true;
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}

		return Integer.parseInt(selection);
	}

	/**
	 * Prints the cancel booking menu.
	 */
	public void cancelBooking() {
		// ask for a selection number (they are printed when displaying the
		// bookings)
		System.out.println("Please enter a booking number:");

		String selection;

		boolean valid = false;

		selection = sc.next();

		while (valid != true) {
			try {
				if (Integer.parseInt(selection) <= 0
						&& Integer.parseInt(selection) >= ((Customer) userAuth.getActiveUser()).getBookings().size()) {
					throw new MenuInputException(selection);
				} else {
					valid = true;
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}

		// btw, yes i know this line is yuck
		// the selection number is on the list sop get rid of that booking and
		// change the status
		// changing the status
		((Customer) userAuth.getActiveUser()).getBookings().get(Integer.parseInt(selection)).setStatus("Available");
		((Customer) userAuth.getActiveUser())
				.cancelBooking(((Customer) userAuth.getActiveUser()).getBookings().get(Integer.parseInt(selection)));
		// changing the status

	}

	/**
	 * Prints the add booking menu and options.
	 */
	public void addBooking() {
		// ask for a selection number (they are printed when displaying the
		// bookings)
		System.out.println("Please enter a booking number:");

		String selection;

		boolean valid = false;

		selection = sc.next();

		while (valid != true) {
			try {
				if (Integer.parseInt(selection) < 0
						&& Integer.parseInt(selection) >= activeBusiness.getAvBookings().size()) {
					throw new MenuInputException(selection);
				} else {
					valid = true;
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}

		// check and see if the status is booked
		if (activeBusiness.getAvBookings().get(Integer.parseInt(selection)).getStatus().compareTo("Booked") == 0) {
			System.out.println("sorry, that appointment is already booked!");
		} else {
			// change the status to taken
			activeBusiness.getAvBookings().get(Integer.parseInt(selection)).setStatus("Booked");

			// call add booking and pass in the booking
			((Customer) userAuth.getActiveUser())
					.addBooking(activeBusiness.getAvBookings().get(Integer.parseInt(selection)));
		}
	}

	/**
	 * Prints the Owner dashboard and options.
	 *
	 * @return the int value of the users selection.
	 */
	public int ownerDashboard() {
		// print menu options for owner, more to be added in part B

		String selection;
		User owner = userAuth.getActiveUser(); // static call to the active
												// user.
		boolean valid = false;

		System.out.printf(
				"Welcome " + owner.getName() + " to the Appointment Booking System\n" + "Please select an option:\n"
						+ "1. View avaliable bookings\n" + "2. Logout\n" + "3. View Business info\n" + "4. Exit\n");
		selection = sc.next();
		int[] options = { 1, 2, 3, 4 };
		while (valid != true) {
			try {

				for (int opt : options) {
					if (Integer.parseInt(selection) == opt) {
						valid = true;
					}
				}

				if (!valid) {
					throw new MenuInputException(selection);
				}
			} catch (MenuInputException e) {
				System.out.printf("Sorry " + e.getInput() + " is an invalid selection, please try again\n");
				selection = sc.next();
			} catch (NumberFormatException e) {
				System.out.printf("Sorry only numbers are valid, please try again\n");
				selection = sc.next();

			}
		}
		return Integer.parseInt(selection);
	}

}
