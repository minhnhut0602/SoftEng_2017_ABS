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
import abs.model.Customer;
import abs.model.Employee;
import abs.model.Owner;
import abs.model.User;

/**
 * The Utilities class provides reads and imports data from file and can export
 * the system back to file.
 *
 * Can be run with default data with a no arg constructor or can use other data
 * files set manually.
 * 
 * you can read all data or just one or the other, same goes for writeData
 * 
 * @see #Utilities() Utilities()
 * @see #Utilities(String, String, String, String) Utilities(String, String,
 *      String, String)
 * @see #readData()
 * @see #writeData(String)
 * 
 */
public class Utilities {

	/** The file path where the data is stored. */
	private String filePath;

	/** The customer info file name/type. */
	private String customerInfoFileName;

	/** The business info file name/type. */
	private String businessInfoFileName;

	private String splitChar;

	private Business business;
	private List<User> customers;
	private Owner owner;

	/**
	 * 
	 * Default constructor. Assumes businessinfo.txt and customerinfo.txt use
	 * CSV and are stored in ./data/
	 * 
	 */
	public Utilities() {
		this.filePath = "./data/";
		this.businessInfoFileName = "businessinfo.txt";
		this.customerInfoFileName = "customerinfo.txt";
		this.splitChar = ",";
	}

	/**
	 * @param filePath
	 *            Relative file path to data files.
	 * @param customerInfoFileName
	 *            Name for customer info file.
	 * @param businessInfoFileName
	 *            Name for business info file.
	 * @param splitChar
	 *            Character for splitting.
	 */
	public Utilities(String filePath, String customerInfoFileName, String businessInfoFileName, String splitChar) {
		this.filePath = filePath;
		this.customerInfoFileName = customerInfoFileName;
		this.businessInfoFileName = businessInfoFileName;
		this.splitChar = splitChar;
	}

	/**
	 * Reads the data from business info file and instantiates it.
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

				// Business's employees
				List<Employee> staff = new ArrayList<Employee>();

				// Available bookings
				List<Booking> avBookings = new ArrayList<Booking>();

				name = bufferedReader.readLine();
				desc = bufferedReader.readLine();

				// Create owner user.
				String ownerName = bufferedReader.readLine();
				String ownerEmail = bufferedReader.readLine();
				String ownerPass = "root"; // TODO update for encryption

				owner = new Owner(ownerName, ownerEmail, ownerPass);

				// Checks if document is empty
				String emTest = bufferedReader.readLine();
				if ((emTest != null)) {

					// Checks for employee data
					if (emTest.contains("Employees")) {
						String[] employee;
						// Employee data is CSV
						while ((employee = bufferedReader.readLine().split(splitChar)).length > 1) {

							List<Availability> availabilities = null;
							availabilities = new ArrayList<Availability>();

							// Adds the employees availabilities, if any.
							for (int i = 1; i < employee.length; i++) {
								i++;
								availabilities.add(new Availability(employee[i - 1], employee[i]));
							} // close for

							// adds new employee
							staff.add(new Employee(employee[0], availabilities));
						} // close while

					} // else format incorrect

					String line = bufferedReader.readLine();
					if (line != null) {
						String[] bookings = line.split(splitChar);

						if (bookings.length > 1) {

							for (int i = 0; i < bookings.length; i++) { // for
																		// all
																		// bookings
								i++;
								avBookings.add(
										new Booking((new Availability(bookings[i], bookings[i + 1])), bookings[i - 1]));
								i++;
								// TODO match employeeID to employee object.
							} // close for

						} // Else incorrect bookings format
					} // Else no bookings
				} // Else no employees or bookings

				business = new Business(name, desc, staff, avBookings);

				reader.close(); // Close file
				return 0; // Success
			} else {
				reader.close(); // Close file
				return -2; // File exists but is empty or not formatted
							// correctly
			}

		} catch (

		FileNotFoundException e) {
			return -1; // File not found

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return -3; // Unimplemented

	}

	public Business getBusiness() {
		if (business == null) {
			readBusinessData();
		}
		return business;
	}

	public Owner getOwner() {
		return owner;
	}

	/**
	 * Reads the data from customer file and instantiates it.
	 *
	 * @return an int to show success/fail. -1 file not found, -2 empty file or
	 *         invalid formatting, -3 unimplemented, 0 success.
	 */
	public int readCustomerData() {
		try {
			FileReader reader = new FileReader(filePath + customerInfoFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			customers = new ArrayList<User>();

			if (bufferedReader.readLine().contains("Customer Info")) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if (!(line.startsWith("#"))) {
						String[] customerIn = line.split(splitChar);
						int phone = Integer.parseInt(customerIn[3]);
						customers.add(new Customer(customerIn[0], customerIn[1], customerIn[2], phone, customerIn[4]));
					}
				}

				reader.close(); // Close file
				return 0; // Success
			} else {
				reader.close(); // Close file
				return -2; // File exists but is empty or not formatted
							// correctly
			}

		} catch (

		FileNotFoundException e) {
			return -1; // File not found

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return -3; // Unimplemented

	}

	public List<User> getCustomers() {
		return customers;
	}

	/**
	 * Reads the data from both files and instantiates it.
	 *
	 * @return an int to show success/fail. 0 success, -1 unsuccessful
	 */
	public int readData() {
		int bus = readBusinessData();
		int cus = readCustomerData();

		if ((bus == 0) && (cus == 0)) { // if both return with success
			return 0;
		}
		return -2;
	}

	/**
	 * @param type
	 *            The type of data to write to file, business, customer or both
	 * @return an int to show success/fail. 1 file not found, -3 unimplemented,
	 *         0 success.
	 */
	public int writeData(String type) { // TODO
		if (type == "customer") {
			return -3;
		}

		else if (type == "business") {
			return -3;
		} else { // Write both

			return -3;
		}

	}
}