package abs.app;

import abs.controller.Registry;
import abs.controller.UserController;
import abs.view.AppFrame;

/**
 * The ABS GUI App.
 */
public class ABS {

	public static void main(String[] args) {

		Registry.getUtils().readData();

		// Load GUI
		AppFrame appFrame = new AppFrame();
		appFrame.revalidate();

		// Set up controller
		new UserController(appFrame);

	}

}
