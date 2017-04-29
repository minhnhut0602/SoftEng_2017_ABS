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

	}
	
	public static void makeCustBooking(){
		
	}

	public static void addAvBooking() {

	}

	public static void removeBooking() {

	}

	public static void addEmployee() {

	}

	public static void newBusiness() {

	}
	
	public static void logout(){
		Registry.getUtils().silentSave();
		UserController.reloadWelcomeScreen();

	}

}
