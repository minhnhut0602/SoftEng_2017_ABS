package abs.controller;

import abs.model.Booking;
import abs.model.Customer;
import abs.view.AppFrame;
import abs.view.BookingPanel;

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
		// (Check that user is customer)
	}

	public static void addBooking(Booking booking) {

		if (booking != null) {
			// add
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

	public static void removeBooking() {

		// Remove all content and load a Bookings panel
		appFrame.getContent().removeAll();

		// creates new bookings panel with users bookings
		appFrame.getContent().add(new BookingPanel(((Customer) Registry.getUserAuth().getActiveUser()).getBookings()));

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

}
