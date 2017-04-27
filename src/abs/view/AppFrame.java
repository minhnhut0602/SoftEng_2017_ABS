/**
 * 
 */
package abs.view;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @since Beta
 */
public class AppFrame extends JFrame {

	private static final long serialVersionUID = 8091808348699216867L;

	public AppFrame() {
		super("Appointment Booking System");

		setBounds(200, 200, 700, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

		// TODO add in components.

	}

	/**
	 * Position a dialog in centre of Frame.
	 * 
	 * @param frame
	 *            Parent frame to centre on.
	 * @param dialog
	 *            Dialog in questions to centre.
	 */
	static void setDialogLocation(JFrame frame, JDialog dialog) {
		Dimension dialogDimension = dialog.getSize();
		Dimension frameDimension = frame.getSize();
		Point frameLocation = frame.getLocation();
		dialog.setLocation(frameLocation.x + ((int) (frameDimension.getWidth() - dialogDimension.getWidth())) / 2,
				frameLocation.y + ((int) (frameDimension.getHeight() - dialogDimension.getHeight())) / 2);
	}
}
