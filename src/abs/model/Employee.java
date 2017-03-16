package abs.model;

import java.util.ArrayList;
import java.util.List;

//Employee Class 
public class Employee {
	private List<Availability> availabilities = new ArrayList<Availability>();

	public Employee(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

}