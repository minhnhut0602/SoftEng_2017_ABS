package abs.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

	private List<Business> businesses;
	private List<User> customers;

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

	public List<Business> getBusiness() {
		if (businesses == null) {
			readBusinessData();
		}
		return businesses;
	}

	public List<User> getCustomers() {
		return customers;
	}

	/**
	 * Reads the data from business info file and instantiates it.
	 *
	 * @return an int to show success/fail. -1 file not found, -2 empty file or
	 *         invalid formatting, -3 unimplemented, 0 success.
	 */
	public int readBusinessData() {

		// TODO update method to loop for multiple businesses

		try {
			FileReader reader = new FileReader(filePath + businessInfoFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);

			if (bufferedReader.readLine().contains("Business Info")) {
				String name; // Business name
				String desc; // Business description
				String address; // Business address
				int number; // Business contact number

				// Business's employees
				List<Employee> staff = new ArrayList<Employee>();

				// Available bookings
				List<Booking> avBookings = new ArrayList<Booking>();

				businesses = new ArrayList<Business>();

				name = bufferedReader.readLine();
				desc = bufferedReader.readLine();
				address = bufferedReader.readLine();
				number = Integer.parseInt(bufferedReader.readLine());

				// Create owner user.
				String ownerName = bufferedReader.readLine();
				String ownerEmail = bufferedReader.readLine();
				String ownerPass = bufferedReader.readLine();// TODO update for
																// encryption
				Owner owner = new Owner(ownerName, ownerEmail, ownerPass);

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

				Business business = new Business(name, desc, address, number, staff, avBookings, owner);
				owner.setBusiness(business);
				businesses.add(business);

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
						customers.add(new Customer(customerIn[0], customerIn[1], customerIn[2], customerIn[3],
								customerIn[4]));
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
	 * Set all the systems customers
	 * 
	 * 
	 * @param customers
	 *            A List of all the customers
	 */
	public void setCustomers(List<User> customers) {
		this.customers = customers;
	}

	/**
	 * @param customer
	 *            A single new customer
	 * @return True for successful add, false if param is null or already exists
	 */
	public boolean addCustomers(Customer customer) {
		if (customer != null) { // Not null
			if (!customers.contains(customer)) { // Not existing.
				this.customers.add(customer);
				return true;
			} else {
				return false;
			}
		} else
			return false;

	}

	public int writeBusinessData(List<Business> businessesWr) {

		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		try {
			writer = new FileWriter(filePath + businessInfoFileName);
			bufferedWriter = new BufferedWriter(writer);

			// TODO i = 0 for now, when multi business surround with loop

			int k = 0;

			// Wrote business info
			bufferedWriter.write("# Business Info\n");
			bufferedWriter.write(businessesWr.get(k).getName());
			bufferedWriter.newLine();
			bufferedWriter.write(businessesWr.get(k).getDesc());
			bufferedWriter.newLine();
			bufferedWriter.write(businessesWr.get(k).getAddress());
			bufferedWriter.newLine();
			bufferedWriter.write(Integer.toString(businessesWr.get(k).getPhone()));
			bufferedWriter.newLine();

			// write owner user info
			Owner owner = businessesWr.get(k).getOwner();
			bufferedWriter.write(owner.getName());
			bufferedWriter.newLine();
			bufferedWriter.write(owner.getEmail());
			bufferedWriter.newLine();
			bufferedWriter.write(owner.getPassword());
			bufferedWriter.newLine();

			// Write employee data
			bufferedWriter.write("# Employees\n");
			List<Employee> staff = businessesWr.get(k).getStaff();
			for (int i = 0; i < staff.size(); i++) {
				bufferedWriter.write(staff.get(i).getName());
				List<Availability> staffAvalib = staff.get(i).getAvailabilities();
				for (int j = 0; j < staffAvalib.size(); j++) {
					bufferedWriter.write(splitChar + staffAvalib.get(j).getDate());
					bufferedWriter.write(splitChar + staffAvalib.get(j).getTime());
				}
				bufferedWriter.newLine();
			}

			// Write available bookings data
			bufferedWriter.write("# Bookings\n");
			List<Booking> avBookings = businessesWr.get(k).getAvBookings();
			for (int i = 0; i < avBookings.size(); i++) {
				if (i == 0) {
					bufferedWriter.write(avBookings.get(i).getStaff());
					Availability slot = avBookings.get(i).getSlot();
					bufferedWriter.write(splitChar + slot.getDate());
					bufferedWriter.write(splitChar + slot.getTime());
				} else {
					bufferedWriter.write(splitChar + avBookings.get(i).getStaff());
					Availability slot = avBookings.get(i).getSlot();
					bufferedWriter.write(splitChar + slot.getDate());
					bufferedWriter.write(splitChar + slot.getTime());
				}

			}

			return 0;
		} catch (IOException e) {
			return -2; //
		} finally {

			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (writer != null)
					writer.close();

			} catch (IOException ex) {

			}
		}

	}

	public int writeCustomerData(List<User> customer) {
		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		try {
			writer = new FileWriter(filePath + customerInfoFileName);
			bufferedWriter = new BufferedWriter(writer);

			// Wrote customer info
			bufferedWriter.write("# Customer Info\n");
			bufferedWriter.write("# name,email,address,phone,password\n");

			List<User> customeres = getCustomers();
			for (int i = 0; i < customers.size(); i++) {
				bufferedWriter.write(customeres.get(i).getName());

				bufferedWriter.write(splitChar + (customeres.get(i)).getEmail());

				bufferedWriter.write(splitChar + ((Customer) customeres.get(i)).getAddress());

				bufferedWriter.write(splitChar + ((Customer) customeres.get(i)).getPhone());

				bufferedWriter.write(splitChar + (customeres.get(i)).getPassword());
				bufferedWriter.newLine();

			}

			return 0;
		} catch (IOException e) {
			return -2; //
		} finally {

			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (writer != null)
					writer.close();

			} catch (IOException ex) {

			}
		}

	}

	/**
	 * @param type
	 *            The type of data to write to file, business, customer or both
	 * @return an int to show success/fail. -1 error, -3 unimplemented, 0
	 *         success.
	 */
	public int writeData(List<Business> businessesWr, List<User> customerWr) { // TODO

		int bus = writeBusinessData(businesses);
		int cus = writeCustomerData(customerWr);
		if ((bus == 0) && (cus == 0)) {
			return 0;
		}
		return -1;

	}
}