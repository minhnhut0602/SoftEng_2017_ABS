package abs.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Owner;
import abs.view.ABSMenuBar;
import abs.view.AppFrame;
import abs.view.OwnerDashboard;

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
		//grab the customers email
		
		//select a booking
		
		//pass to customer 
		
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

}
