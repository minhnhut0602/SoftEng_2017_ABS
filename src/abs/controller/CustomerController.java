package abs.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import abs.model.Booking;
import abs.model.Customer;
import abs.view.AppFrame;
import abs.view.GUIComponents.ABSMenuBar;
import abs.view.customer.BookingPanel;
import abs.view.customer.CustomerDashboard;
import abs.view.customer.RemoveBookingPanel;

/**
 * The Customer Controller, controls the customers functions and views.
 * 
 * @version 1.0
 * 
 * @see UserController
 * @see OwnerController
 */
public class CustomerController {

	/** The Customer Controller logger. */
	private static final Logger logger = Logger.getLogger("Controller:Customer Logger");

	/** The app frame. */
	private static AppFrame appFrame;

	/**
	 * Instantiates a new customer controller.
	 *
	 * @param appFrame
	 *            the app frame for reference
	 */
	public CustomerController(AppFrame appFrame) {
		CustomerController.appFrame = appFrame;
	}

	/**
	 * Loads/reloads the Customer Dashboard.
	 */
	public static void dashboard() {

		// TODO MAYBE: Check for null businesses, show error msg.

		// Remove all content and load a Bookings panel
		appFrame.getContent().removeAll();

		// Creates a new dash panel
		appFrame.getContent().add(new CustomerDashboard(appFrame));

		// Update available buttons
		ABSMenuBar.toggleButton("login", false);
		ABSMenuBar.toggleButton("register", false);
		ABSMenuBar.toggleButton("logout", true);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	/**
	 * Adds a booking for the user and/or loads/reloads the add bookings view.
	 *
	 * @param booking
	 *            the booking, null for view refresh
	 */
	public static void addBooking(Booking booking) {

		if (booking != null) {
			// add booking
			if (((Customer) Registry.getUserAuth().getActiveUser()).addBooking(booking)) {
				logger.log(Level.INFO, "Booking Successfull");
			} else {
				logger.log(Level.WARNING, "Booking Failed");
			}

			// TODO on screen success message
		}

		// Remove all content and load a Bookings panel
		appFrame.getContent().removeAll();

		// Creates a new bookings panel with active bus bookings
		appFrame.getContent().add(new BookingPanel((UserController.getActiveBusiness().getAvBookings())));

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	/**
	 * Removes a booking for the user and/or loads/reloads the remove bookings
	 * view.
	 *
	 * @param booking
	 *            the booking, null for view refresh
	 */
	public static void removeBooking(Booking booking) {

		if (booking != null) {
			// Remove booking
			if (((Customer) Registry.getUserAuth().getActiveUser()).cancelBooking(booking)) {
				logger.log(Level.INFO, "Booking Cancelled");
			} else {
				logger.log(Level.WARNING, "Booking Failed to Cancel");
			}
			// TODO Update success message
		}

		// Remove all content and load a remove panel
		appFrame.getContent().removeAll();

		// creates new remove panel with users bookings
		appFrame.getContent()
				.add(new RemoveBookingPanel(((Customer) Registry.getUserAuth().getActiveUser()).getBookings()));

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

}
