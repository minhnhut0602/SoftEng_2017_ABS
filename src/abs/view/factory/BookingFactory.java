package abs.view.factory;

import javax.swing.JTextPane;

import abs.model.Booking;
import abs.view.GUIComponents.AvButton;
import abs.view.style.AppStyle;

public class BookingFactory {

	public static String bookingString(Booking booking) {

		String bookingString = "";
		String length;

		if (booking.getService() != null) {
			bookingString = bookingString + "Type: " + booking.getService() + ", ";
		}
		bookingString = bookingString + "Date: " + booking.getSlot().getDate() + ", Start Time: "
				+ booking.getSlot().getTime();

		if (booking.getSlot().getBlocks() > 0) {
			length = (booking.getSlot().getBlocks() * booking.getBusiness().BLOCK_LENGTH) + " Mins";
		} else {
			length = booking.getBusiness().BLOCK_LENGTH + " Mins";
		}
		bookingString = bookingString + ", Length: " + length + ", ";
		bookingString = bookingString + "Staff: " + booking.getStaff().getName();
		return bookingString;
	}

	public static JTextPane bookingPane(Booking booking) {
		JTextPane bookingPane = new JTextPane();

		bookingPane.setEditable(false);
		bookingPane.setText(bookingString(booking));

		if (booking.getStatus().compareTo("Available") == 0) {
			bookingPane.setBackground(AppStyle.successColor);
		} else {
			bookingPane.setBackground(AppStyle.warningColor);
		}

		return bookingPane;
	}

	public static AvButton bookingButton(Booking booking) {
		AvButton bookingButton = new AvButton(bookingString(booking));
		bookingButton.setBooking(booking);

		if (booking.getStatus().compareTo("Available") == 0) {
			bookingButton.setForeground(AppStyle.successColor);
			bookingButton.setEnabled(true);
		} else {
			bookingButton.setBackground(AppStyle.warningColor);
			bookingButton.setEnabled(false);
		}

		return bookingButton;
	}

	public static JTextPane TestBookingPane() {
		JTextPane bookingPane = new JTextPane();

		bookingPane.setEditable(false);
		bookingPane.setBackground(AppStyle.successColor);
		bookingPane
				.setText("Type: Haircut" + "Date: 1/5" + "Start Time: 8:30 am" + "Length: 0.5 hrs" + "Staff: Daniel");

		return bookingPane;
	}

}
