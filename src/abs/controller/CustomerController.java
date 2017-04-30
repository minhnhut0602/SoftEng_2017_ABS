package abs.controller;

import abs.model.Booking;
import abs.model.Customer;
import abs.view.ABSMenuBar;
import abs.view.AppFrame;
import abs.view.customer.BookingPanel;
import abs.view.customer.CustomerDashboard;
import abs.view.customer.RemoveBookingPanel;

/**
 *
 */
public class CustomerController {

	private static AppFrame appFrame;

	/**
	 * 
	 */
	public CustomerController(AppFrame appFrame) {
		CustomerController.appFrame = appFrame;
	}

	public static void dashboard() {
		// TODO Show dashboard

		// Check for any bus, if none show error else set the active to 1st for
		// now
		UserController.setActiveBusiness(Registry.getUtils().getBusiness().get(0));
		// Remove all content and load a Bookings panel
		appFrame.getContent().removeAll();

		// Creates a new bookings panel with active bus bookings
		appFrame.getContent().add(new CustomerDashboard());

		// Update available buttons
		ABSMenuBar.toggleButton("login", false);
		ABSMenuBar.toggleButton("register", false);
		ABSMenuBar.toggleButton("logout", true);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	public static void addBooking(Booking booking) {

		if (booking != null) {
			// add booking
			((Customer) Registry.getUserAuth().getActiveUser()).addBooking(booking);

			// Update success message ?
		}

		// Remove all content and load a Bookings panel
		appFrame.getContent().removeAll();

		// Creates a new bookings panel with active bus bookings
		appFrame.getContent().add(new BookingPanel((UserController.getActiveBusiness().getAvBookings())));

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	public static void removeBooking(Booking booking) {

		if (booking != null) {
			// Remove booking
			((Customer) Registry.getUserAuth().getActiveUser()).cancelBooking(booking);

			// Update success message ?
		}

		// Remove all content and load a Bookings panel
		appFrame.getContent().removeAll();

		// creates new bookings panel with users bookings
		appFrame.getContent()
				.add(new RemoveBookingPanel(((Customer) Registry.getUserAuth().getActiveUser()).getBookings()));

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

}
