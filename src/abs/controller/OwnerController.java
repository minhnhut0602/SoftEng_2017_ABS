package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import abs.model.Availability;
import abs.model.Booking;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Employee;
import abs.model.Owner;
import abs.view.ABSMenuBar;
import abs.view.AddAvEmployee;
import abs.view.AddEmployee;
import abs.view.AppFrame;
import abs.view.BookingForCustomer;
import abs.view.NewBusiness;
import abs.view.OwnerDashboard;
import abs.view.ShowAvailabilities;

/**
 *
 */
public class OwnerController {
	
	/** The data access logger */
	private static final Logger logger = Logger.getLogger("OwnerController Logger");
	
	private static AppFrame appFrame;
	
	
	/**
	 * 
	 */
	public OwnerController(AppFrame appFrame) {
		OwnerController.appFrame = appFrame;
	}
	
	public static void dashboard(){
		// Remove all content and load the dashboard
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new OwnerDashboard());
		
		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}
	
	public static void makeCustBooking(){
		
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new BookingForCustomer());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
		
		
	}

	public static void addAvBooking() {
		//boot up the screen
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new AddAvEmployee());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
		

	}
	
	public static void addAvBooking(String time, String date, String employee, String business){
		Business b = Registry.getUtils().findBusiness(business);
		Employee e = b.findStaff(employee);
		
		//turn time, date, employee and Business into Booking
		 new Booking(new Availability(date, time), e, "Available", b);
		
		
	}

	public static void removeBooking() {
		
		//select a booking
		
		//click remove button

	}

	public static void addEmployee() {
		
		//load up window
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new AddEmployee());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();

	}

	public static void newBusiness() {
		
		//load up window
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new NewBusiness());

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
		
		//get business details
		
		
		//make new business type, add to business array

	}
	
	public static void logout(){
		Registry.getUtils().silentSave();
		UserController.reloadWelcomeScreen();

	}

	public static void reloadDashboard() {
		//go back to dashboard 
		appFrame.getContent().removeAll();
		appFrame.getContent().add(AppFrame.getOwnerDashboard());

		// Update available buttons
		ABSMenuBar.toggleButton("login", true);
		ABSMenuBar.toggleButton("register", true);
		ABSMenuBar.toggleButton("logout", false);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}
	
	/**
	 * might need to add extra fields for the booking selection
	 * @param email
	 */
	
	public static void checkEmail(String email, Object business){
		
		//have to convert business from Object to Business
		Business b = Registry.getUtils().findBusiness(business);
		
		//make sure the customer exists, then change screens
		Customer cust = (Customer)Registry.getUtils().searchCustomers(email);
		
		if(cust != null){
			//change screens to show availabilities
			appFrame.getContent().removeAll();
			appFrame.getContent().add(new ShowAvailabilities(cust, b));

			// Refresh frame
			appFrame.repaint();
			appFrame.revalidate();
		}
		
	}

	public static List<String> getBusinessNames() {
		List<String> bNames = new ArrayList<String>();
		
		List<Business> businesses = Registry.getUtils().getBusiness();
		
		if(businesses.size() == 0){
			return null;
		}
		
		for(int i = 0; i < businesses.size(); i++){
			bNames.add(businesses.get(i).getName());
		}
		
		return bNames;
	}
	
	public static void createCustBooking(Customer c, Business b, Booking booking){
		//pass to owner method already written
		Owner o = (Owner) Registry.getUserAuth().getActiveUser();
		if(o.bookForCustomer(c, b, booking) != true){
			//log something i suppose
			System.out.println("didn't book");
		}else{
			//go back to dashboard
			reloadDashboard();
		}
		
	}

	public static void registerBusiness(String name, String desc, String address, String phone) {
		
		//remove spaces for phone number
		phone = phone.replaceAll("\\s","");
		
		try{
			Integer.parseInt(phone);
			
		}catch(NumberFormatException e){
			//log error or something
		}
		
		//get the owner
		Owner owner = (Owner) Registry.getUserAuth().getActiveUser();
		
		//make a business object
		Business b = new Business(name, desc, address, Integer.parseInt(phone), owner);
		
		//pass to utilities class
		Registry.getUtils().addBusiness(b);
		
		//then move back to the dashboard
		reloadDashboard();
		
	}

	public static void addEmployee(String name, Object business) {
		
		//have to convert business from Object to Business
		Business b = Registry.getUtils().findBusiness(business);
		
		//now create the employee
		
		Employee staff = new Employee(name);
		
		b.addStaff(staff);
		
		//go back to the dashboard
		reloadDashboard();
		
	}

}
