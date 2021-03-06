package abs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Business class.
 * 
 * <p>
 * The business stores its availabilities and staff.
 * </p>
 * 
 * @since Alpha
 * @version 1.2
 */
public class Business {

	/** Block length for business (Default 30 min) */
	public final int BLOCK_LENGTH = 30;

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
	 * 
	 * @see #Business()
	 * @see #Business(String, String, String, int, Owner)
	 */
	public Business(String name, String desc, String address, int phone, List<Employee> staff, List<Booking> avBookings,
			Owner owner) {
		this.name = name;
		this.desc = desc;
		this.address = address;
		this.phone = phone;
		this.staff = staff;
		this.avBookings = avBookings;
		this.owner = owner;
	}

	/**
	 * Generic constructor, no values set
	 * 
	 * @see #Business(String, String, String, int, Owner)
	 * @see #Business(String, String, String, int, List, List, Owner)
	 */
	public Business() {
		this.name = null;
		this.desc = null;
		this.address = null;
		this.owner = null;

	}

	/**
	 * To create a business without setting any employees or availabilities.
	 *
	 * @param name
	 *            name of business
	 * @param desc
	 *            description
	 * @param address
	 *            business address
	 * @param phone
	 *            business phone number
	 * @param owner
	 *            the owner of this business
	 * 
	 * @see #Business()
	 * @see #Business(String, String, String, int, List, List, Owner)
	 */
	public Business(String name, String desc, String address, int phone, Owner owner) {
		this.name = name;
		this.desc = desc;
		this.address = address;
		this.phone = phone;
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
	 * Adds one employee to the employee list
	 * 
	 * @param employee
	 */
	public void addStaff(Employee employee) {
		this.staff.add(employee);
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}

	public Employee findStaff(String name) {
		Employee employee = null;

		for (Employee e : staff) {
			if (e.getName().compareTo(name) == 0) {
				employee = e;
			}
		}
		return employee;

	}

	public void addBookingTime(Booking booking) {
		avBookings.add(booking);
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

	/**
	 * Displays the businesses available bookings.
	 */
	public void displayBookings() {
		// check if there are any times entered
		if (this.avBookings.size() == 0) {
			System.out.println("Sorry, this business doesn't have any times entered, check back soon");
		} else {
			for (int i = 0; i < this.avBookings.size(); i++) {
				// loop over the bookings and print nicely
				System.out.printf("<<--|" + i + ". [ " + this.avBookings.get(i).getStatus() + " ] "
						+ this.avBookings.get(i).getSlot().getDate() + ", " + this.avBookings.get(i).getSlot().getTime()
						+ " |-->> \n");

			}
		}

	}

	/**
	 * Remove a booking from the business
	 * 
	 * @param booking
	 *            to be removed
	 */
	public void removeBooking(Booking booking) {
		avBookings.remove(booking);

	}

}