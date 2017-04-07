package abs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public abstract class AbstractUser implements User {

	private String name;
	private String email;

	private String password; // Hashed??
	
	private List<Booking> bookings = new ArrayList<Booking>();

	/**
	 * @param name
	 * @param email
	 * @param password
	 */
	public AbstractUser(String name, String email, String password, List<Booking> bookings) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.setBookings(bookings);
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "AbstractUser [name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
