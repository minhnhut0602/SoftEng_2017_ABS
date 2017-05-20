package abs.model;

import java.util.ArrayList;
import java.util.List;

import abs.controller.Utilities;

/**
 * The Owner class extends abstractUser.
 *
 * @see AbstractUser
 * @see User
 * 
 * @version 1.3
 * @since Alpha
 */
public class Owner extends AbstractUser {

	/** The owners business. */
	private List<Business> businesses = new ArrayList<Business>();

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
	public List<Business> getBusinesses() {
		return businesses;
	}

	/**
	 * Sets the business.
	 *
	 * @param business
	 *            the new business
	 */
	public void setBusiness(List<Business> business) {
		this.businesses = business;
	}

	/**
	 * shows all the times a business is booked
	 */
	public void viewBookings() {

		for (int i = 0; i < businesses.size(); i++) {
			// save the booking temporarily
			List<Booking> bookings = businesses.get(i).getAvBookings();

			for (int j = 0; j < bookings.size(); j++) {
				System.out.println(bookings.get(j).getSlot().getTime() + " " + bookings.get(i).getSlot().getDate() + " "
						+ bookings.get(j).getStatus());

			}
		}

	}

	/**
	 * book on behalf of a customer.
	 *
	 * @param custEmail
	 *            this is the customer's email to search for
	 * @param business
	 *            the business you are booking them into
	 * @param booking
	 *            the booking time to actually book
	 * @param utils
	 *            the utils
	 * @return true, if successful
	 */
	public boolean bookForCustomer(String custEmail, Business business, Booking booking, Utilities utils) {
		// try to search for customer
		User customer = utils.searchCustomers(custEmail);
		if (customer != null) {
			// then the customer was found
			// make sure the booking is actually in the business, just do double
			// check
			if (business.getAvBookings().contains(booking)) {
				// so add a booking for that customer
				((Customer) customer).addBooking(booking);
				return true;
			}

		}

		return false;
	}

	/**
	 * 
	 * Makes a booking on behalf of a customer
	 * 
	 * @param cust
	 *            the customer to book for
	 * @param b
	 *            the business
	 * @param booking
	 *            the booking time and date
	 * @return true if booking is made, false if not
	 */
	public boolean bookForCustomer(Customer cust, Business b, Booking booking) {

		// check the booking exists
		if (b.getAvBookings().contains(booking)) {
			// make the booking for the customer
			cust.addBooking(booking);
			// update the status of the availability
			booking.setStatus("Booked");

			return true;
		}

		return false;
	}

	/**
	 * A a business the user owns
	 * 
	 * @param business
	 *            to be added
	 */
	public void addBusiness(Business business) {
		this.businesses.add(business);
	}

}