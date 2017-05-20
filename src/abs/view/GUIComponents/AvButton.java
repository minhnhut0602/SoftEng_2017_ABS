package abs.view.GUIComponents;

import javax.swing.JButton;

import abs.model.Booking;

/**
 * The Availability Button.
 * 
 * @version 1.0
 * @since 1.0
 * @serial 6924951867221607392
 * @see abs.model.Booking Booking
 * @see javax.swing.JButton JButton
 */
public class AvButton extends JButton {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6924951867221607392L;

	/** The booking for this button. */
	private Booking booking;

	/**
	 * Instantiates a new Availability button.
	 *
	 * @param s
	 *            the string text
	 */
	public AvButton(String s) {
		super(s);
	}

	/**
	 * Gets the booking.
	 *
	 * @return the booking
	 */
	public Booking getBooking() {
		return booking;
	}

	/**
	 * Sets the booking.
	 *
	 * @param booking
	 *            the new booking
	 */
	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
