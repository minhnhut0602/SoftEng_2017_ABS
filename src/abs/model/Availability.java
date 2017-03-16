package abs.model;
//Availability Class

//Essentially creating our own Date Time Data Type plus things that interact with it

public class Availability {
	private int date;
	private int time;

	public Availability(int date, int time) {
		this.date = date;
		this.time = time;
	}

	public int getDate() {
		return date;
	}

	public int getTime() {
		return time;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Availability [date=" + date + ", time=" + time + "]";
	}

}