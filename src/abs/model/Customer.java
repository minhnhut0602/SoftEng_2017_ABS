package abs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
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

	// to add a booking the booking is added to the list in the user.
	public boolean addBooking(Booking booking) {
		// appends the booking time
		// this.bookings.add(booking);

		// eventually we should sort the list after appending, or better yet
		// insert into the list where it should actually go.

		return this.bookings.add(booking);
	}

	/**
	 * @param booking
	 * @return
	 */
	public boolean cancelBooking(Booking booking) {

		return this.bookings.remove(booking);
	}

}
