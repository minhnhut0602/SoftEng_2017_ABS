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

	/** The employees name. */
	private String name;

	/** The employees availabilities. */
	private List<Availability> availabilities = new ArrayList<Availability>();

	/**
	 * Instantiates a new employee.
	 *
	 * @param name
	 *            the employees name
	 * @param availabilities
	 *            the employees availabilities
	 */
	public Employee(String name, List<Availability> availabilities) {
		this.name = name;
		this.availabilities = availabilities;
	}

	/**
	 * Gets the availabilities.
	 *
	 * @return the availabilities
	 */
	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	/**
	 * Gets the name.
	 *
	 * @return the employees name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the availabilities.
	 *
	 * @param availabilities
	 *            the new availabilities
	 */
	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [name=" + name + ", availabilities=" + availabilities + "]";
	}

}