package abs.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Business;
import abs.model.Owner;
import abs.view.AppFrame;
import abs.view.LoginPanel;
import abs.view.RegisterPanel;
import abs.view.GUIComponents.ABSMenuBar;

/**
 * The user controller handles the login/registration and logout functions.
 * 
 * @version 1.0
 * @see OwnerController
 * @see CustomerController
 *
 */
public class UserController {

	/** The user controller logger */
	private static final Logger logger = Logger.getLogger("Controller:User Logger");

	/** The app frame. */
	private static AppFrame appFrame;

	/** The active business. */
	private static Business activeBusiness = null;

	/**
	 * Initialise the UserController
	 * 
	 * @param appFrame
	 *            for easy reference
	 */
	public UserController(AppFrame appFrame) {
		UserController.appFrame = appFrame;
	}

	/**
	 * Load/reload login screen.
	 */
	public static void loadLoginScreen() {
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new LoginPanel(appFrame));

		// Update available buttons
		ABSMenuBar.toggleButton("Register", true);
		ABSMenuBar.toggleButton("login", false);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	/**
	 * Load/reload register screen.
	 */
	public static void loadRegisterScreen() {
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new RegisterPanel(appFrame));

		// Update available buttons
		ABSMenuBar.toggleButton("Register", false);
		ABSMenuBar.toggleButton("login", true);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	/**
	 * Reload welcome screen.
	 */
	public static void reloadWelcomeScreen() {
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(AppFrame.getWelcomePanel());

		// Update available buttons
		ABSMenuBar.toggleButton("login", true);
		ABSMenuBar.toggleButton("register", true);
		ABSMenuBar.toggleButton("logout", false);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	/**
	 * Login a user.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @throws CredentialsInvalidException
	 *             if the users credentials are invalid or null
	 */
	public static void login(String email, String password) throws CredentialsInvalidException {

		Registry.getUserAuth().authUser(email, password);
		appFrame.repaint();
		appFrame.revalidate();
		// Update display and move to appropriate dash

		if (Registry.getUserAuth().getActiveUser().getClass().getName().equals(Owner.class.getName())) {
			new OwnerController(appFrame);
			OwnerController.dashboard();
			logger.log(Level.INFO, "Owner Login Successful ID: " + Registry.getUserAuth().getActiveUser().getEmail());
			LoginPanel.getStatus().setText("Owner Login Successful");

		} else {
			new CustomerController(appFrame);
			CustomerController.dashboard();
			logger.log(Level.INFO,
					"Customer Login Successful ID: " + Registry.getUserAuth().getActiveUser().getEmail());
			LoginPanel.getStatus().setText("Customer Login Successful");

		}

	}

	/**
	 * Register a new customer or owner
	 *
	 * @param name
	 *            the users name
	 * @param email
	 *            the users email
	 * @param password
	 *            the users password
	 * @param address
	 *            the users address
	 * @param phone
	 *            the users phone
	 * @param isOwner
	 *            is the user is an owner
	 * @throws RegistrationValidationException
	 *             if the registration details are invalid or null
	 * @throws RegistrationNonUniqueException
	 *             is the user is pre existing
	 */
	public static void register(String name, String email, String password, String address, String phone,
			Boolean isOwner) throws RegistrationValidationException, RegistrationNonUniqueException {
		boolean success = false;
		if (isOwner) {
			Registry.getUserAuth().registerOwner(name, email, password);
			success = true;
			logger.log(Level.INFO,
					"Owner Registration Successful ID: " + Registry.getUserAuth().getActiveUser().getEmail());

			RegisterPanel.getStatus().setText("Owner Registation Successful");
		} else {
			Registry.getUserAuth().registerUser(name, email, password, address, phone);
			success = true;
			logger.log(Level.INFO,
					"Customer Registration Successful ID: " + Registry.getUserAuth().getActiveUser().getEmail());

			RegisterPanel.getStatus().setText("Customer Registation Successful");
		}

		if (success) {
			Registry.getUtils().silentSave();
			reloadWelcomeScreen();
		}
	}

	/**
	 * Logout the user, save and reload welcome screen.
	 */
	public static void logout() {
		// Reset active user and business
		Registry.getUserAuth().setActiveUser(null);
		setActiveBusiness(null);

		// Save
		Registry.getUtils().silentSave();

		reloadWelcomeScreen();
	}

	/**
	 * Gets the active business.
	 *
	 * @return the active business
	 */
	public static Business getActiveBusiness() {
		if (activeBusiness == null) {// If none set yet
			// And if some exist in the system
			if (!(Registry.getUtils().getBusiness().isEmpty())) {
				// Set the first bus as active
				setActiveBusiness(Registry.getUtils().getBusiness().get(0));
			} else {
				logger.log(Level.WARNING, "System has no business registered");
			}
		}
		return activeBusiness;
	}

	/**
	 * Sets the active business.
	 *
	 * @param activeBusiness
	 *            the new active business
	 */
	public static void setActiveBusiness(Business activeBusiness) {
		UserController.activeBusiness = activeBusiness;
	}
}
