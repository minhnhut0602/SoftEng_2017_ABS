package abs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The customer class extends abstractUser
 * 
 * 
 * @see AbstractUser
 * @see User
 *
 */
public class Customer extends AbstractUser {

	private String address;

	private String phone;
	private List<Booking> bookings = new ArrayList<Booking>();

	/**
	 * @param name
	 * @param email
	 * @param address
	 * @param phone
	 * @param password
	 */

	public Customer(String name, String email, String address, String phone, String password) {
		super(name, email, password);
		this.address = address;
		this.phone = phone;
	}

	// public Customer(String name, String email, String address, String phone,
	// String password, List<Booking> bookings) {
	// super(name, email, password, bookings);
	// this.address = address;
	// this.phone = phone;
	// }

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {

		return super.toString() + "Customer [address=" + address + ", phone=" + phone + "]";
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	/*
	 * To add a booking the booking is added to the list in the user.
	 * 
	 * @param booking
	 */
	public boolean addBooking(Booking booking) {

		// check to see if the list is empty
		if (this.bookings == null) {
			this.bookings.add(booking);
		}

		// loop over the list
		for (int i = 0; i < this.bookings.size(); i++) {
			// compare the booking with the already booked bookings
			// if the output is == to 0 then it is the same date
			if (booking.getSlot().getDate().compareTo(this.bookings.get(i).getSlot().getDate()) == 0) {
				// compare the time
				if (booking.getSlot().getTime().compareTo(this.bookings.get(i).getSlot().getTime()) == 0) {
					// double booked the time, return false
					System.out.println("You already have an appointment booked at this time");
					return false;

				} else if (booking.getSlot().getTime().compareTo(this.bookings.get(i).getSlot().getTime()) > 0) {
					// the booking is after the current booking, so put here
					this.bookings.add(i + 1, booking);
				}
			} else if (booking.getSlot().getDate().compareTo(this.bookings.get(i).getSlot().getDate()) > 0) {
				// then the booking is the first to be made for this day, just
				// insert at that position
				this.bookings.add(i, booking);

			} else if (i == this.bookings.size()) {
				// if you get to this statement that means you cna just append
				// to the list since it got to the end
				this.bookings.add(booking);
			}
		}

		return true;
	}

	/**
	 * @param booking
	 * @return
	 */
	public boolean cancelBooking(Booking booking) {

		for (int i = 0; i < this.bookings.size(); i++) {
			if (booking.getSlot().getDate().compareTo(this.bookings.get(i).getSlot().getDate()) == 0) {
				// compare the time
				if (booking.getSlot().getTime().compareTo(this.bookings.get(i).getSlot().getTime()) == 0) {
					// remove the booking
					this.bookings.remove(i);
				}
			}
		}
		return false;
	}

	public void viewBookings() {
		// loop over the list of bookings stored for this user
		for (int i = 0; i < this.bookings.size(); i++) {
			System.out
					.println(this.bookings.get(i).getSlot().getTime() + " " + this.bookings.get(i).getSlot().getDate());
		}
		// print them out nicely

	}

}
