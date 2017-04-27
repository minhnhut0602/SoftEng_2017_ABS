package abs.controller;

import java.awt.Component;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Owner;
import abs.view.ABSMenuBar;
import abs.view.AppFrame;
import abs.view.LoginPanel;
import abs.view.RegisterPanel;

/**
 *
 */
public class UserController {

	private static AppFrame appFrame;

	/**
	 * 
	 */
	public UserController(AppFrame appFrame) {
		UserController.appFrame = appFrame;
	}

	public static void loadLoginScreen(Component component) {
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new LoginPanel());
		ABSMenuBar.toggleButton("Register", true);
		ABSMenuBar.toggleButton("login", false);
		appFrame.repaint();
		appFrame.revalidate();
	}

	public static void loadRegisterScreen(Component component) {
		appFrame.getContent().removeAll();
		appFrame.getContent().add(new RegisterPanel());
		ABSMenuBar.toggleButton("Register", false);
		ABSMenuBar.toggleButton("login", true);
		appFrame.repaint();
		appFrame.revalidate();
	}

	public static void reloadWelcomeScreen() {
		appFrame.getContent().removeAll();
		appFrame.getContent().add(AppFrame.getWelcomePanel());
		ABSMenuBar.toggleButton("login", true);
		ABSMenuBar.toggleButton("register", true);
		ABSMenuBar.toggleButton("logout", false);
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

	public static void register(String name, String email, String password, String address, String phone)
			throws CredentialsInvalidException, RegistrationValidationException, RegistrationNonUniqueException {
		Registry.getUserAuth().registerUser(name, email, password, address, phone);

		// Update display and move to login screen
	}

}
