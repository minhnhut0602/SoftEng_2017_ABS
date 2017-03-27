package abs.model;

import java.util.ArrayList;
import java.util.List;

//Employee Class 
public class Employee {
	private String name;
	private List<Availability> availabilities = new ArrayList<Availability>();

	public Employee(String name, List<Availability> availabilities) {
		this.name = name;
		this.availabilities = availabilities;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	/**
	 * @return the employees name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", availabilities=" + availabilities + "]";
	}
	

}