package abs.model;

//import java.util.ArrayList;
//import java.util.List;

/**
 * The abstract user class implements the user interface. and is extended by
 * customer and owner.
 *
 * @see User User Interface
 * @see Owner
 * @see Customer
 */
public abstract class AbstractUser implements User {

	/** The users name. */
	private String name;

	/** The users email. */
	private String email;

	/** The users password. */
	private String password; // Hashed??

	// private List<Booking> bookings = new ArrayList<Booking>();

	/**
	 * Instantiates a new abstract user.
	 *
	 * @param name
	 *            the name
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 */

	public AbstractUser(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// public AbstractUser(String name, String email, String password,
	// List<Booking> bookings) {
	// super();
	// this.name = name;
	// this.email = email;
	// this.password = password;
	// this.setBookings(bookings);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see abs.model.User#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see abs.model.User#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see abs.model.User#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractUser [name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	// public List<Booking> getBookings() {
	// return bookings;
	// }
	//
	// public void setBookings(List<Booking> bookings) {
	// this.bookings = bookings;
	// }

}
