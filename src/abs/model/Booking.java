package abs.model;

/**
 * The Class Booking.
 */
public class Booking {

	/** The bookings slot. */
	private Availability slot;

	/** The staff assigned to this booking (String). */
	private Employee staff;

	/** The business. */
	private Business business;

	/** The status (Booked/Available). */
	private String status;

	/**
	 * Instantiates a new booking.
	 *
	 * @param slot
	 *            the slot an availability
	 * @param staff
	 *            the staff a string
	 * @param status
	 *            the status of the booking a string (booked or available)
	 */
	public Booking(Availability slot, Employee staff, String status) {
		this.slot = slot;
		this.staff = staff;
		this.status = status;
	}

	/**
	 * Instantiates a new booking.
	 *
	 * @param slot
	 *            the slot an availability
	 * @param staff
	 *            the staff a string
	 * @param status
	 *            the status of the booking a string (booked or available)
	 * @param business
	 *            the business to add
	 */
	public Booking(Availability slot, Employee staff, String status, Business business) {
		this.slot = slot;
		this.staff = staff;
		this.status = status;
		this.business = business;
	}

	/**
	 * Gets the slot.
	 *
	 * @return the slot
	 */
	public Availability getSlot() {
		return slot;
	}

	/**
	 * Gets the staff.
	 *
	 * @return the staff
	 */
	public Employee getStaff() {
		return staff;
	}

	/**
	 * Gets the business.
	 *
	 * @return the business
	 */
	public Business getBusiness() {
		return business;
	}

	/**
	 * Sets the business.
	 *
	 * @param business
	 *            the new business
	 */
	public void setBusiness(Business business) {
		this.business = business;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Booking [slot=" + slot + ", staff=" + staff + ", busniness=" + business + ", status=" + status + "]";

	}

	public String getService() {
		// TODO Set up services
		return null;
	}

}
