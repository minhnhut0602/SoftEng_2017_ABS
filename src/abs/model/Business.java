package abs.model;
//Business Class

import java.util.ArrayList;
import java.util.List;

public class Business {

	/** The business name. */
	private String name;

	/** The business description. */
	private String desc;

	/** The staff. */
	private List<Employee> staff = new ArrayList<Employee>();

	/** The Available bookings. */
	private List<Booking> avBookings = new ArrayList<Booking>();

	/**
	 * Instantiates a new business.
	 *
	 * @param name
	 *            the business name
	 * @param desc
	 *            the business description
	 * @param staff
	 *            the staff
	 * @param avBookings
	 *            the available bookings
	 */
	public Business(String name, String desc, List<Employee> staff, List<Booking> avBookings) {
		super();
		this.name = name;
		this.desc = desc;
		this.staff = staff;
		this.avBookings = avBookings;
	}

}