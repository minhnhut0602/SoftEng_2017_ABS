package abs.view.factory;

import java.awt.Color;

import javax.swing.JTextPane;

import abs.model.Booking;
import abs.view.GUIComponents.AvButton;

public class BookingFactory {

	public static String bookingString(Booking booking) {

		String length = (booking.getSlot().getBlocks() * booking.getBusiness().BLOCK_LENGTH) + "Mins";

		String bookingString = "Type: " + booking.getService() + " Date: " + booking.getSlot().getDate() + " Start Time: "
				+ booking.getSlot().getTime() + " Length: " + length + " Staff: " + booking.getStaff().getName();

		return bookingString;
	}

	public static JTextPane bookingPane(Booking booking) {
		JTextPane bookingPane = new JTextPane();

		bookingPane.setEditable(false);
		bookingPane.setText(bookingString(booking));

		if (booking.getStatus().compareTo("Available") == 0) {
			bookingPane.setBackground(Color.GREEN);
		} else {
			bookingPane.setBackground(Color.RED);
		}

		return bookingPane;
	}

	public static AvButton bookingButton(Booking booking) {
		AvButton bookingButton = new AvButton(bookingString(booking));
		bookingButton.setBooking(booking);

		if (booking.getStatus().compareTo("Available") == 0) {
			bookingButton.setBackground(Color.GREEN);
			bookingButton.setEnabled(true);
		} else {
			bookingButton.setBackground(Color.RED);
			bookingButton.setEnabled(false);
		}

		return bookingButton;
	}

	public static JTextPane TestBookingPane() {
		JTextPane bookingPane = new JTextPane();

		bookingPane.setEditable(false);
		bookingPane.setBackground(Color.GREEN);
		bookingPane
				.setText("Type: Haircut" + "Date: 1/5" + "Start Time: 8:30 am" + "Length: 0.5 hrs" + "Staff: Daniel");

		return bookingPane;
	}

}
