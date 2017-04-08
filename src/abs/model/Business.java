package abs.model;
//Business Class

import java.util.ArrayList;
import java.util.List;

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

	public String getAddress() {
		return address;
	}

	public List<Booking> getAvBookings() {
		return avBookings;
	}

	public String getDesc() {
		return desc;
	}

	public String getName() {
		return name;
	}

	public int getPhone() {
		return phone;
	}

	public List<Employee> getStaff() {
		return staff;
	}
	
	public Owner getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "Business [name=" + name + ", desc=" + desc + ", staff=" + staff + ", avBookings=" + avBookings + "]";
	}

}