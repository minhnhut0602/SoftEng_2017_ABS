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

	/** The date. */
	private String date;

	/** The time. */
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

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Sets the time.
	 *
	 * @param time
	 *            the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Availability [date=" + date + ", time=" + time + "]";
	}

}