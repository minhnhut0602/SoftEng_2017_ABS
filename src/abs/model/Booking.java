/**
 * 
 */
package abs.model;

/**
 *
 */
public class Booking {

	private Availability slot;
	private String staff;
	private Business busniness;
	private String status;

	/**
	 * Instantiates a new booking.
	 *
	 * @param slot
	 *            the slot
	 * @param staff
	 *            the staff
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

	public Business getBusniness() {
		return busniness;
	}

	public void setBusniness(Business busniness) {
		this.busniness = busniness;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [slot=" + slot + ", staff=" + staff + ", busniness=" + busniness + ", status=" + status + "]";
	}

}
