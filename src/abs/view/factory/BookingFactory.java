package abs.view.factory;

import javax.swing.JButton;
import javax.swing.JTextPane;

import abs.model.Booking;

public class BookingFactory {

	public static String bookingString(Booking booking) {

		// String length = (booking.getSlot().getBlocks()) / 2.0 + "hrs"; //
		// Only works for bloacks of 30 min

		String length = (booking.getSlot().getBlocks() * booking.getBusiness().BLOCK_LENGTH) + "Mins";

		String bookingString = "Type: " + booking.getService() + "Date: " + booking.getSlot().getDate() + "Start Time: "
				+ booking.getSlot().getTime() + "Length: " + length + "Staff: " + booking.getStaff().getName();

		return bookingString;
	}

	public static JTextPane bookingPane(Booking booking) {
		JTextPane bookingPane = new JTextPane();

		bookingPane.setEditable(false);
		bookingPane.setText(bookingString(booking));

		// TODO Style text and pane

		return bookingPane;
	}

	public static JButton bookingButton(Booking booking) {
		JButton bookingButton = new JButton();

		bookingButton.setText(bookingString(booking));

		// TODO Style text and pane

		return bookingButton;
	}

	public static JTextPane TestBookingPane() {
		JTextPane bookingPane = new JTextPane();

		bookingPane.setEditable(false);
		bookingPane
				.setText("Type: Haircut" + "Date: 1/5" + "Start Time: 8:30 am" + "Length: 0.5 hrs" + "Staff: Daniel");

		// Style text and pane

		return bookingPane;
	}

}
