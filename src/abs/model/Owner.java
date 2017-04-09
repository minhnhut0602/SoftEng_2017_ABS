package abs.model;

import java.util.List;

/**
 * The Owner class extends abstractUser.
 *
 * @see AbstractUser
 * @see User
 */
public class Owner extends AbstractUser {

	/** The owners business. */
	private Business business;

	/**
	 * Instantiates a new owner.
	 *
	 * @param name
	 *            the name
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 */

	public Owner(String name, String email, String password) {
		super(name, email, password);
	}

	// // construct an owner type object
	// public Owner(String name, String email, String password, List<Booking>
	// bookings) {
	// super(name, email, password, bookings);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see abs.model.AbstractUser#toString()
	 */
	@Override
	public String toString() {
		return "Owner [name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword()
				+ "]";
	}

	/**
	 * Gets the business.
	 *
	 * @return the business
	 */
	// returns Business
	public Business getBusiness() {
		return business;
	}

	/**
	 * Sets the business.
	 *
	 * @param business
	 *            the new business
	 */
	// set the Business
	public void setBusiness(Business business) {
		this.business = business;
	}

	/**
	 * shows all the times a business is booked
	 */
	public void viewBookings() {
		// save the booking temporarily
		List<Booking> bookings = business.getAvBookings();

		for (int i = 0; i < bookings.size(); i++) {
			System.out.println(bookings.get(i).getSlot().getTime() + " " + bookings.get(i).getSlot().getDate() + " "
					+ bookings.get(i).getStatus());

		}
	}

}