package abs.model;
//Availability Class

//Essentially creating our own Date Time Data Type plus things that interact with it

public class Availability {
	private String date;
	private String time;

	/**
	 * Instantiates a new availability.
	 *
	 * @param date
	 *            the date
	 * @param time
	 *            the time
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