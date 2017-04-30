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
	 * 
	 * Makes a new employee with no availabilities
	 * 
	 * @param name
	 * 			the employee's name
	 */
	public Employee(String name){
		this.name = name;
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
	
	/**
	 * adds one availability at a time
	 * @param avail
	 * the availability to be added
	 */
	public void addAvailabilities(Availability avail){
		this.availabilities.add(avail);
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