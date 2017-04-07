package abs.model;

/**
 *
 */
public class Booking {

	private Availability slot;
	private String staff;
	private Business business;
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
	public Booking(Availability slot, String staff, String status) {
		this.slot = slot;
		this.staff = staff;
		this.status = status;
	}

	public Availability getSlot() {
		return slot;
	}

	public String getStaff() {
		return staff;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;

	}

	@Override
	public String toString() {

		return "Booking [slot=" + slot + ", staff=" + staff + ", busniness=" + business + ", status=" + status + "]";

	}

}
