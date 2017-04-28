package abs.controller;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Owner;
import abs.view.ABSMenuBar;
import abs.view.AppFrame;
import abs.view.LoginPanel;
import abs.view.RegisterPanel;

/**
 * The user controller handles the login/registration and logout functions.
 *
 *
 */
public class UserController {

	private static AppFrame appFrame;

	/**
	 * Initialise the UserController
	 * 
	 * @param appFrame
	 *            for easy reference
	 */
	public UserController(AppFrame appFrame) {
		UserController.appFrame = appFrame;
	}

	public static void loadLoginScreen() {
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new LoginPanel());

		// Update available buttons
		ABSMenuBar.toggleButton("Register", true);
		ABSMenuBar.toggleButton("login", false);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

	public static void loadRegisterScreen() {
		// Remove all content and load a login panel
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new RegisterPanel());

		// Update available buttons
		ABSMenuBar.toggleButton("Register", false);
		ABSMenuBar.toggleButton("login", true);

		// Refresh frame
		appFrame.repaint();
		appFrame.revalidate();
	}

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

	public static void login(String email, String password) throws CredentialsInvalidException {
		Registry.getUserAuth().authUser(email, password);

		// Update display and move to appropriate dash

		if (Registry.getUserAuth().getActiveUser().getClass().getName().equals(Owner.class.getName())) {
			OwnerController.dashboard();
		} else {
			CustomerController.dashboard();
		}

	}

	public static void register(String name, String email, String password, String address, String phone,
			Boolean isOwner)
			throws CredentialsInvalidException, RegistrationValidationException, RegistrationNonUniqueException {

		if (isOwner) {
			// TODO Register as an owner
		} else {
			Registry.getUserAuth().registerUser(name, email, password, address, phone);

		}

		// TODO Update display and move to login screen
		reloadWelcomeScreen();
	}

}
