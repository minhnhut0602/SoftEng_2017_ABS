package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Booking;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Owner;
import abs.model.User;
import abs.view.ABSMenuBar;
import abs.view.AppFrame;
import abs.view.BookingForCustomer;
import abs.view.OwnerDashboard;
import abs.view.RegisterPanel;
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


	public static void addBooking() {
		//text field for time, date
		
		//add booking

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
		//text field for time, date
		
		//create new availability for the business
		

	}

	public static void removeBooking() {
		
		//select a booking
		
		//click remove button

	}

	public static void addEmployee() {
		
		//enter details very much like the register thing
		
		//create the employee and link to business

	}

	public static void newBusiness() {
		
		//get business details
		
		//make new business type, add to business array

	}
	
	public static void logout(){
		Registry.getUtils().silentSave();
		UserController.reloadWelcomeScreen();

	}

	public static void reloadDashboard() {
		
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
		
	}

}
