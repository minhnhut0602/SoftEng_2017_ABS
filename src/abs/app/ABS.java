package abs.app;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.view.AppFrame;

/**
 * The GUI ABS Application.
 *
 */
public class ABS {

	/** The Utilities object. */
	private final static Utilities utils = new Utilities();

	/** The user Auth object. */
	private static UserAuth userAuth;

	public static void main(String[] args) {

		utils.readData();
		userAuth = new UserAuth(utils);

		// Load GUI
		AppFrame appFrame = new AppFrame();
		appFrame.revalidate();

	}

}
