package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import abs.model.Availability;
import abs.model.Booking;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Employee;
import abs.model.Owner;
import abs.model.User;
import abs.view.AppFrame;
import abs.view.GUIComponents.ABSMenuBar;
import abs.view.owner.AddAvEmployee;
import abs.view.owner.AddEmployee;
import abs.view.owner.BookingForCustomer;
import abs.view.owner.NewBusiness;
import abs.view.owner.OwnerDashboard;
import abs.view.owner.RemoveAvailabilities;
import abs.view.owner.ShowAvailabilities;
import static java.lang.Integer.parseInt;

/**
 * The Owner Controller, controls the owners functions and views.
 * 
 * @version 1.0
 * @since 1.0
 * @see UserController
 * @see CustomerController
 */
public class OwnerController {

	/** The data access logger */
	private static final Logger logger = Logger.getLogger("ABSLogger");

	private static AppFrame appFrame;

	/**
	 * Instantiates a new owner controller.
	 *
	 * @param appFrame
	 *            the app frame
	 */
	public OwnerController(AppFrame appFrame) {
		OwnerController.appFrame = appFrame;
	}

	/**
	 * displays the dahsboard
	 */
	public static void dashboard() {
		// Remove all content and load the dashboard
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new OwnerDashboard());

		// Update available buttons
		ABSMenuBar.toggleButton("login", false);
		ABSMenuBar.toggleButton("register", false);
		ABSMenuBar.toggleButton("logout", true);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}
	
	/**
	 * displays select customer screen
	 */
	public static void makeCustBooking() {

		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new BookingForCustomer());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	/**
	 * displays the add availability screen
	 */
	public static void addAvBooking() {
		// boot up the screen
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new AddAvEmployee());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	/**
	 * takes inputs from the add booking screen and creates the new booking. Reloads the dashboard
	 * @param time
	 * @param date
	 * @param employee
	 * @param business
	 */
	public static void addAvBooking(String time, String date, String blockcount, String employee, String business) {
		Business b = Registry.getUtils().findBusiness(business);
		Employee e = b.findStaff(employee);

		Availability a = new Availability(date, time, parseInt(blockcount));
		// turn time, date, employee and Business into Booking
		// add this booking to the business
		b.addBookingTime(new Booking(a, e, "Available", b));
		e.addAvailabilities(a);
		logger.info("Booking added successfully");

		// return to the dashboard

		reloadDashboard();

	}

	/**
	 * grabs all the bookings from a business and displays them
	 * @param businessName
	 */
	public static void removeBooking(Object businessName) {

		List<Booking> bookings = Registry.getUtils().findBusiness(businessName).getAvBookings();

		appFrame.getContent().removeAll();
		appFrame.getContent().add(new RemoveAvailabilities(bookings));

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	/**
	 * displays the add employee screen
	 */
	public static void addEmployee() {

		// load up window
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new AddEmployee());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	/**
	 * displays the add business screen
	 */
	public static void newBusiness() {

		// load up window
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new NewBusiness());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	/**
	 * logout the owner
	 */
	public static void logout() {
		UserController.logout();

	}

	/**
	 * reload the dashboard
	 */
	public static void reloadDashboard() {
		// go back to dashboard
		appFrame.getContent().removeAll();
		appFrame.getContent().add(AppFrame.getOwnerDashboard());
		logger.info("Reloading dashboard");

		// Update available buttons
		ABSMenuBar.toggleButton("login", false);
		ABSMenuBar.toggleButton("register", false);
		ABSMenuBar.toggleButton("logout", true);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	/**
	 * might need to add extra fields for the booking selection.
	 *
	 * @param email
	 *            the owners email
	 * @param business
	 *            the business
	 */

	/**
	 * checks to see if the email entered is a valid customer email
	 * @param email
	 * @param business
	 */
	public static void checkEmail(String email, Object business) {

		// have to convert business from Object to Business
		Business b = Registry.getUtils().findBusiness(business);

		// make sure the customer exists, then change screens
		Customer cust = (Customer) Registry.getUtils().searchCustomers(email);

		if (cust != null) {
			// change screens to show availabilities
			appFrame.getContent().removeAll();
			appFrame.getContent().add(new ShowAvailabilities(cust, b));

			// Refresh frame
			appFrame.repaint();
			appFrame.revalidate();
		}

		logger.warning("That email does not exist");

	}

	/**
	 * grabs all the business names
	 * @return arraylist of names
	 */
	public static List<String> getBusinessNames() {
		List<String> bNames = new ArrayList<String>();

		List<Business> businesses = Registry.getUtils().getBusiness();

		if (businesses.size() == 0) {
			logger.warning("There are no businesses in the system");
			return null;
		}

		for (int i = 0; i < businesses.size(); i++) {
			bNames.add(businesses.get(i).getName());
		}
		logger.info("Business names found");
		return bNames;
	}

	/**
	 * gets business names for a specific user
	 * @param user
	 * @return
	 */
	public static List<String> getBusinessNames(User user) {

		// convert user to owner
		Owner o = (Owner) user;

		List<String> bNames = new ArrayList<String>();

		List<Business> businesses = Registry.getUtils().getBusiness();

		if (businesses.size() == 0) {
			logger.warning("No businesses found. How are you even an owner then " + o.getName() + "?");
			return null;
		}

		for (int i = 0; i < businesses.size(); i++) {
			if (businesses.get(i).getOwner().equals(o)) {
				bNames.add(businesses.get(i).getName());
			}
		}
		logger.info("Business names found");
		return bNames;
	}

	/**
	 * creates the customer booking
	 * @param c
	 * is the customer
	 * @param b
	 * is the business
	 * @param booking
	 * is the booking
	 */
	public static void createCustBooking(Customer c, Business b, Booking booking) {
		// pass to owner method already written
		Owner o = (Owner) Registry.getUserAuth().getActiveUser();
		if (o.bookForCustomer(c, b, booking) != true) {
			// log something i suppose
			logger.warning("Booking Unsuccessfull");
		} else {
			// go back to dashboard
			logger.info("Booking Successfull");
			reloadDashboard();
		}

	}

	/**
	 * registers a business with the fields given
	 * @param name
	 * @param desc
	 * @param address
	 * @param phone
	 */
	public static void registerBusiness(String name, String desc, String address, String phone) {

		// remove spaces for phone number
		phone = phone.replaceAll("\\s", "");

		try {
			Integer.parseInt(phone);

			// get the owner
			Owner owner = (Owner) Registry.getUserAuth().getActiveUser();

			// make a business object
			Business b = new Business(name, desc, address, Integer.parseInt(phone), owner);

			// pass to utilities class
			Registry.getUtils().addBusiness(b);

			logger.info("New business registered: " + b.getName());

			// then move back to the dashboard
			reloadDashboard();

		} catch (NumberFormatException e) {
			// log error or something
			logger.warning(e + "Thats not a number, This is a number -> 1234567890");
		}

	}

	/**
	 * adds an employee
	 * @param name
	 * of the employee
	 * @param business
	 * which business it's for
	 */
	public static void addEmployee(String name, Object business) {

		// have to convert business from Object to Business
		Business b = Registry.getUtils().findBusiness(business);

		// now create the employee

		Employee staff = new Employee(name);

		b.addStaff(staff);

		logger.info("Employee: " + staff.getName() + " added to " + b.getName());
		// go back to the dashboard
		reloadDashboard();

	}

	/**
	 * gets all the employees from a business
	 * @param business
	 * @return
	 */
	public static Object getEmployees(Object business) {
		// grab a list of employees from the
		return null;
	}

	/**
	 * deletes a booking from the system
	 * @param booking
	 */
	public static void deleteBooking(Booking booking) {

		// check to see if it is booked, don't let them delete if it is booked
		if (booking.getStatus().compareTo("Booked") == 0) {
			JOptionPane.showMessageDialog(null,
					"Someone has booked that already, you can't just pretend it never existed");
			logger.warning("Cannot delete availability that is already booked");
		} else {
			// they can remove it
			booking.getStaff().removeAvailability(booking.getSlot());

			booking.getBusiness().removeBooking(booking);
			logger.info("Booking removed");

			// Refresh frame
			appFrame.repaint();
			appFrame.revalidate();
		}

	}

}
