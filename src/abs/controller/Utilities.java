package abs.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import abs.model.Availability;
import abs.model.Booking;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Employee;
import abs.model.Owner;
import abs.model.User;
import static java.lang.Integer.parseInt;

/**
 * The Utilities class provides reads and imports data from file and can export
 * the system back to file.
 *
 * <p>
 * Can be run with default data with a no parameter constructor or can use other
 * data files set manually. <br>
 * <i>You can read all data or just one or the other, same goes for
 * writeData.</i>
 * </p>
 * 
 * 
 * @see #Utilities() Utilities()
 * @see #Utilities(String, String, String, String) Utilities(String, String,
 *      String, String)
 * @see #readData()
 * @see #writeData(List, List)
 * @see #silentSave()
 * 
 * 
 * @version 1.02
 * @since Alpha
 * 
 */
public class Utilities {

	/** The data access logger */
	private static final Logger logger = Logger.getLogger("ABSLogger");

	/** The file path where the data is stored. */
	private String filePath;

	/** The customer info file name/type. */
	private String customerInfoFileName;

	/** The business info file name/type. */
	private String businessInfoFileName;

	/** The split char. */
	private String splitChar;

	/** The businesses. */
	private List<Business> businesses;

	/** The customers. */
	private List<User> customers;

	/** the owners of all the businesses */
	private List<User> owners;

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
	 * Instantiates a new utilities.
	 *
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
	 * Gets the business.
	 *
	 * @return the business
	 */
	public List<Business> getBusiness() {
		if (businesses == null) {
			readData();
		}
		return businesses;
	}

	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	public List<User> getCustomers() {
		if (customers == null) {
			readData();
		}
		return customers;
	}

	/**
	 * getter for owner list
	 * 
	 * @return owner list
	 */
	public List<User> getOwners() {
		return owners;
	}

	/**
	 * adds a single owner to the list of owners
	 * 
	 * 
	 * @param owner
	 *            the owner to add
	 * @return true, if success
	 */
	public boolean addOwner(Owner owner) {
		if (owner != null) { // Not null
			if (!owners.contains(owner)) { // Not existing.
				this.owners.add(owner);
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}

	/**
	 * Reads the data from business info file and instantiates it.
	 *
	 * @return an int to show success/fail. -1 file not found, -2 empty file or
	 *         invalid formatting, -3 unimplemented, 0 success.
	 */
	public int readBusinessData() {

		businesses = new ArrayList<Business>();
		owners = new ArrayList<User>();

		try {
			FileReader reader = new FileReader(filePath + businessInfoFileName);
			BufferedReader bufferedReader = new BufferedReader(reader);

			String firstLine;
			while ((firstLine = bufferedReader.readLine()) != null) {
				if (firstLine.contains("Business Info")) {
					String name; // Business name
					String desc; // Business description
					String address; // Business address
					int number; // Business contact number

					// Business's employees
					List<Employee> staff = new ArrayList<Employee>();

					// Available bookings
					List<Booking> avBookings = new ArrayList<Booking>();

					name = bufferedReader.readLine();
					desc = bufferedReader.readLine();
					address = bufferedReader.readLine();
					number = Integer.parseInt(bufferedReader.readLine());

					// Create owner user.
					String ownerName = bufferedReader.readLine();
					String ownerEmail = bufferedReader.readLine();
					String ownerPass = bufferedReader.readLine();
					// TODO update for encryption/hashing

					Owner owner = new Owner(ownerName, ownerEmail, ownerPass);

					owners.add(owner);

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
									i++;
                                                                        i++;
									avBookings.add(new Booking((new Availability(bookings[i - 2], bookings[i - 1], parseInt(bookings[i]))),
											searchEmplyees(bookings[i - 3], staff), bookings[i + 1]));
									i++;

								} // close for

							} // Else incorrect bookings format
						} // Else no bookings
					} // Else no employees or bookings

					Business business = new Business(name, desc, address, number, staff, avBookings, owner);
					owner.addBusiness(business);
					for (Booking booking : avBookings) {
						booking.setBusiness(business);
					}
					businesses.add(business);

				} else {
					reader.close(); // Close file
					bufferedReader.close(); // Close file
					return -2; // File exists but is empty or not formatted
								// correctly
				}

			}

			reader.close(); // Close file
			bufferedReader.close(); // Close file
			return 0; // Success

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
						Customer customer = new Customer(customerIn[0], customerIn[1], customerIn[2], customerIn[3],
								customerIn[4]);
						customers.add(customer);
						if ((line = bufferedReader.readLine()).contains("Booking")) {
							line = bufferedReader.readLine();
							String[] bookingIn = line.split(splitChar);

							for (int i = 0; i < bookingIn.length; i++) {
								i++;
								i++;
                                                                i++;

								// Booking booking = new Booking((new
								// Availability(bookingIn[i], bookingIn[i +
								// 1])),
								// bookingIn[i - 1], "Booked");

								for (Business business : businesses) {
									if (business.getName().equals(bookingIn[i - 3])) {
										for (Booking booking : business.getAvBookings()) {
											if (booking.getStatus().equals("Booked")) {
												if (booking.getStaff().getName().equals(bookingIn[i - 2])) {
													if (booking.getSlot().getDate().equals(bookingIn[i - 1])
															&& booking.getSlot().getTime().equals(bookingIn[i]) && booking.getSlot().getTime().equals(bookingIn[i + 1])) {
														customer.addBooking(booking);
													}
												}
											}
										}

									}

								} // close for
								i++;
							}
						}

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
	 * Set all the systems customers.
	 *
	 * @param customers
	 *            A List of all the customers
	 */
	public void setCustomers(List<User> customers) {
		this.customers = customers;
	}

	/**
	 * Adds the customers.
	 *
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

	/**
	 * Write business data.
	 *
	 * @param businessesWr
	 *            the businesses wr
	 * @return the int
	 */
	public int writeBusinessData(List<Business> businessesWr) {

		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;
		try {
			writer = new FileWriter(filePath + businessInfoFileName);
			bufferedWriter = new BufferedWriter(writer);

			for (Business business : businessesWr) {

				// Wrote business info
				bufferedWriter.write("# Business Info\n");
				bufferedWriter.write(business.getName());
				bufferedWriter.newLine();
				bufferedWriter.write(business.getDesc());
				bufferedWriter.newLine();
				bufferedWriter.write(business.getAddress());
				bufferedWriter.newLine();
				bufferedWriter.write(Integer.toString(business.getPhone()));
				bufferedWriter.newLine();

				// write owner user info
				Owner owner = business.getOwner();
				bufferedWriter.write(owner.getName());
				bufferedWriter.newLine();
				bufferedWriter.write(owner.getEmail());
				bufferedWriter.newLine();
				bufferedWriter.write(owner.getPassword());
				bufferedWriter.newLine();

				// Write employee data
				bufferedWriter.write("# Employees\n");
				List<Employee> staff = business.getStaff();
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
				List<Booking> avBookings = business.getAvBookings();
				for (int i = 0; i < avBookings.size(); i++) {
					if (i == 0) {
						bufferedWriter.write(avBookings.get(i).getStaff().getName());
						Availability slot = avBookings.get(i).getSlot();
						bufferedWriter.write(splitChar + slot.getDate());
						bufferedWriter.write(splitChar + slot.getTime());
                                                bufferedWriter.write(splitChar + slot.getBlocks());
						bufferedWriter.write(splitChar + (avBookings.get(i).getStatus()));
					} else {
						bufferedWriter.write(splitChar + avBookings.get(i).getStaff().getName());
						Availability slot = avBookings.get(i).getSlot();
						bufferedWriter.write(splitChar + slot.getDate());
						bufferedWriter.write(splitChar + slot.getTime());
                                                bufferedWriter.write(splitChar + slot.getBlocks());
						bufferedWriter.write(splitChar + (avBookings.get(i).getStatus()));
					}

				}
				bufferedWriter.write("\n");
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
	 * Write customer data.
	 *
	 * @param customer
	 *            the customer
	 * @return the int
	 */
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

				// loop through users booked, bookings
				if (!((Customer) customeres.get(i)).getBookings().isEmpty()) {
					bufferedWriter.write("# Bookings\n");
					List<Booking> bookings = ((Customer) customeres.get(i)).getBookings();
					for (int j = 0; j < bookings.size(); j++) {
						if (j == 0) {
							bufferedWriter.write(bookings.get(j).getBusiness().getName());
							bufferedWriter.write(splitChar + bookings.get(j).getStaff().getName());
							Availability slot = bookings.get(j).getSlot();
							bufferedWriter.write(splitChar + slot.getDate());
							bufferedWriter.write(splitChar + slot.getTime());
                                                        bufferedWriter.write(splitChar + slot.getBlocks());
						} else {
							bufferedWriter.write(splitChar + bookings.get(j).getBusiness().getName());
							bufferedWriter.write(splitChar + bookings.get(j).getStaff().getName());
							Availability slot = bookings.get(j).getSlot();
							bufferedWriter.write(splitChar + slot.getDate());
							bufferedWriter.write(splitChar + slot.getTime());
                                                        bufferedWriter.write(splitChar + slot.getBlocks());
						}

					}
					bufferedWriter.write("\n");

				} else {
					bufferedWriter.write("#\n");
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

	/**
	 * Write data.
	 *
	 * @param businessesWr
	 *            the businesses wr
	 * @param customerWr
	 *            the customer wr
	 * @return an int to show success/fail. -1 error, -3 unimplemented, 0
	 *         success.
	 */
	public int writeData(List<Business> businessesWr, List<User> customerWr) {

		int bus = writeBusinessData(businessesWr);
		int cus = writeCustomerData(customerWr);
		if ((bus == 0) && (cus == 0)) {
			return 0;
		}
		return -1;

	}

	/**
	 * Silent save saves the data currently stored in the instance of Utilities.
	 * <i>On error prints to logger.</i>
	 * 
	 * @see #writeData(List, List)
	 * 
	 */
	public void silentSave() {
		int bus = writeBusinessData(this.businesses);
		int cus = writeCustomerData(this.customers);
		if ((bus != 0) || (cus != 0)) {
			logger.log(Level.WARNING, "SilentSave Failed - Bus error code: " + bus + " Cus error code: " + cus);
		}
	}

	/**
	 * search customer list by email
	 * 
	 * @param email
	 *            the customers email
	 * @return customer object
	 */
	public User searchCustomers(String email) {
		User found = null;

		if (customers == null || customers.size() == 0) {
			return found;
		}

		for (User customer : customers) {
			if (customer.getEmail().compareTo(email) == 0) {
				/** then this is the customer */
				found = customer;
			}
		}

		return found;
	}

	public Employee searchEmplyees(String name, List<Employee> emps) {
		Employee found = null;
		List<Employee> staff = emps;
		if (staff.isEmpty()) {
			return found;
		}

		for (Employee emp : staff) {
			if (emp.getName().compareTo(name) == 0) {
				/** then this is the customer */
				found = emp;
			}
		}

		return found;
	}

	public Business findBusiness(Object business) {
		Business b = null;

		// search the business list and compare business names
		for (int i = 0; i < businesses.size(); i++) {
			if (businesses.get(i).getName().compareTo((String) business) == 0) {
				b = businesses.get(i);
			}
		}

		return b;
	}

	public boolean addBusiness(Business business) {

		this.businesses.add(business);

		// also add the business to the owner
		business.getOwner().addBusiness(business);

		return true;
	}

}