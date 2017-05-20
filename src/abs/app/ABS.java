package abs.app;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import abs.controller.Registry;
import abs.controller.UserController;
import abs.view.AppFrame;

/**
 * The ABS GUI App.
 * 
 * @since 1.0
 * @version 1.1
 */
public class ABS {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger("ABSLogger");
		FileHandler fh;

		try {

			// This block configure the logger with handler and formatter
			fh = new FileHandler("ABS.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// logger.info("#ABS Log File");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Registry.getUtils().readData();

		// Load GUI
		AppFrame appFrame = new AppFrame();
		appFrame.revalidate();

		// Set up controller
		new UserController(appFrame);

	}

}
