package abs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The employee class.
 * 
 * Has Availability
 *
 */
public class Employee {
	private String name;
	private List<Availability> availabilities = new ArrayList<Availability>();

	/**
	 * @param name
	 * @param availabilities
	 */
	public Employee(String name, List<Availability> availabilities) {
		this.name = name;
		this.availabilities = availabilities;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	/**
	 * @return the employees name
	 */
	public String getName() {
		return name;
	}

	public void setAvailafbilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", availabilities=" + availabilities + "]";
	}

}