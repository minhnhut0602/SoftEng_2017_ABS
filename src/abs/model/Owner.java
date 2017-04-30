package abs.model;

import java.util.List;

import abs.controller.Utilities;

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
	
	
	/**
	 * book on behalf of a customer
	 * 
	 * @param custEmail
	 * this is the customer's email to search for
	 * @param business
	 * the business you are booking them into
	 * @param booking
	 * the booking time to actually book
	 * @return
	 */
	public boolean bookForCustomer(String custEmail, Business business, Booking booking, Utilities utils){
		//try to search for customer
		User customer = utils.searchCustomers(custEmail);
		if(customer != null){
			//then the customer was found
			//make sure the booking is actually in the business
			if(business.getAvBookings().contains(booking)){
				//so add a booking for that customer 
				((Customer)customer).addBooking(booking);
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
	 * the customer to book for
	 * @param b
	 * the business
	 * @param booking
	 * the booking time and date
	 * @return
	 * true if booking is made, false if not
	 */
	public boolean bookForCustomer(Customer cust, Business b, Booking booking){
		
		//check the booking exists
		if(b.getAvBookings().contains(booking)){
			//make the booking for the customer
			cust.addBooking(booking);
			//update the status of the availability
			booking.setStatus("");
		}
		
		return false;
	}

}