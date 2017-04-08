package abs.model;

/**
 * The Class Availability.
 * <p>
 * Essentially creating our own Date Time pair class. plus things that interact
 * with it.
 * </p>
 *
 */
public class Availability {
	private String date;
	private String time;

	/**
	 * Instantiates a new availability.
	 *
	 * @param date
	 *            the date as a string
	 * @param time
	 *            the time as a string
	 */
	public Availability(String date, String time) {
		this.date = date;
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Availability [date=" + date + ", time=" + time + "]";
	}

}