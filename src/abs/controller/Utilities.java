package abs.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abs.model.Availability;
import abs.model.Booking;
import abs.model.Business;
import abs.model.Employee;

public class Utilities {

	/** The file path where the data is stored. */
	private String filePath = "./data/";

	/** The customer info file name/type. */
	private String customerInfoFileName = "customerinfo.txt";

	/** The business info file name/type. */
	private String businessInfoFileName = "businessinfo.txt";

	/**
	 * Reads the data from business file and instantiates it.
	 *
	 * @return an int to show success/fail. -1 file not found, -2 empty file or
	 *         invalid formatting, -3 unimplemented, 0 success.
	 */
	public int readBusinessData() {
		try {
			FileReader reader = new FileReader(filePath + businessInfoFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);

			if (bufferedReader.readLine().contains("Business Info")) {
				String name; // Business name
				String desc; // Business description
				List<Employee> staff = new ArrayList<Employee>(); // Business's
																	// employees
				List<Booking> avBookings = new ArrayList<Booking>(); // Available
																		// bookings

				/**
				 * TODO read first Word -> name, next line until '.' is
				 * description.
				 * 
				 * e.g Damian Dentists Damian has run his family dentistry for
				 * over 25 years and prides himself on his work.
				 * 
				 * empty line, Then read line by line, read the employee name
				 * and their availabilities. Availabilities are a pair of date
				 * and time.
				 * 
				 * E.g Carry,mon,2pm-4pm,mon,5pm-6pm,wed,10am-4pm
				 * Grant,tue,8am-1pm,thu,3pm-4pm
				 *
				 * empty line, Then read the available bookings on one line.
				 *
				 * e.g tue,4:30,tue,5:00,tue,5:30,wed,1:00,wed,2:30
				 */

				name = bufferedReader.readLine();
				desc = bufferedReader.readLine();
				if (bufferedReader.readLine().contains("Employees")) // Signals
				// employee data
				{
					String[] employee;
					while ((employee = bufferedReader.readLine().split(",")).length > 1) {

						List<Availability> availabilities = null;

						for (int i = 1; i < employee.length; i++) {
							i++;
							availabilities = new ArrayList<Availability>(); // Employee's
																			// Availabilities
							availabilities.add(new Availability(employee[i - 1], employee[i]));

						}
						staff.add(new Employee(employee[0], availabilities));
					}

				} // else no employees

				String[] bookings;
				if (bufferedReader.readLine().contains("Employees")) // Signals
				{
					while ((bookings = bufferedReader.readLine().split(",")).length > 1) {

						for (int i = 0; i < bookings.length; i++) { // for all
																	// bookings
							i++;
							avBookings.add(
									new Booking((new Availability(bookings[i], bookings[i + 1])), bookings[i - 1]));
							i++;
						} // TODO match employeeID to employee object.

					}
				}

				new Business(name, desc, staff, avBookings);

				reader.close(); // Close file
				return 0;
			} else {
				reader.close(); // Close file
				return -2; // File exists but is empty or not formatted
							// correctly
			}

		} catch (FileNotFoundException e) {
			return -1; // File not found

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return -3; // Unimplemented

	}

	/**
	 * Reads the data from customer file and instantiates it.
	 *
	 * @return an int to show success/fail. -1 file not found. 0 success
	 */
	public int readCustomerData() {
		try {
			FileReader reader = new FileReader(customerInfoFileName);

			// TODO

		} catch (FileNotFoundException e) {
			return -1; // File not found
		}
		return -2; // Unimplemented

	}

	/**
	 * Reads the data from both files and instantiates it.
	 *
	 * @return an int to show success/fail. -1 file not found. 0 success
	 */
	public int readData() {
		int bus = readBusinessData();
		int cus = readCustomerData();

		if ((bus == 0) && (cus == 0)) {
			return 0;
		}
		return -1;
	}

	public int writeData(String type) {
		if (type == "customer") {
			return -3;
		}

		else if (type == "business") {
			return -3;
		} else {

			return -3;
		}

	}
}