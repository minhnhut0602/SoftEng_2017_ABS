package abs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Business class.
 * 
 * <p>
 * The business stores its availabilities and staff.
 * </p>
 */
public class Business {

	/** The business name. */
	private String name;

	/** The business description. */
	private String desc;

	/** The business address. */
	private String address;

	/** The business contact number. */
	private int phone;

	/** The staff. */
	private List<Employee> staff = new ArrayList<Employee>();

	/** The Available bookings. */
	private List<Booking> avBookings = new ArrayList<Booking>();

	/** The owner of the business. */
	private Owner owner;

	/**
	 * Instantiates a new business.
	 *
	 * @param name
	 *            the business name
	 * @param desc
	 *            the business description
	 * @param address
	 *            the street address
	 * @param phone
	 *            the contact phone number
	 * @param staff
	 *            the staff
	 * @param avBookings
	 *            the available bookings
	 * @param owner
	 *            the owner
	 */
	public Business(String name, String desc, String address, int phone, List<Employee> staff, List<Booking> avBookings,
			Owner owner) {
		super();
		this.name = name;
		this.desc = desc;
		this.address = address;
		this.phone = phone;
		this.staff = staff;
		this.avBookings = avBookings;
		this.owner = owner;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets the available bookings.
	 *
	 * @return the available bookings
	 */
	public List<Booking> getAvBookings() {
		return avBookings;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Gets the business name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * Gets the staff.
	 *
	 * @return the staff
	 */
	public List<Employee> getStaff() {
		return staff;
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Business [name=" + name + ", desc=" + desc + ", staff=" + staff + ", avBookings=" + avBookings + "]";
	}

}