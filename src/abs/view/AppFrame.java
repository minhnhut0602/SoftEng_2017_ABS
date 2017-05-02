package abs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import abs.view.GUIComponents.StatusBar;
import abs.view.owner.OwnerDashboard;

/**
 * @since Beta
 */
public class AppFrame extends JFrame {

	private static final long serialVersionUID = 8091808348699216867L;
	private static JPanel welcomePanel = new WelcomePanel();
	private static JPanel content = new JPanel();
	private static JPanel ownerDashboard = new OwnerDashboard();

	public AppFrame() {
		super("Appointment Booking System");

		setBounds(200, 200, 820, 650);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

		StatusBar statusBar = new StatusBar(this);
		ABSMenuBar menuBar = new ABSMenuBar(this);
		content.setLayout(new BorderLayout());
		content.add(welcomePanel, BorderLayout.CENTER);
		this.getContentPane().add(menuBar, BorderLayout.NORTH);
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		this.getContentPane().add(content, BorderLayout.CENTER);

	}

	/**
	 * Position a dialog in centre of Frame.
	 * 
	 * @param frame
	 *            Parent frame to centre on.
	 * @param dialog
	 *            Dialog in questions to centre.
	 */
	public static void setDialogLocation(JFrame frame, JDialog dialog) {
		Dimension dialogDimension = dialog.getSize();
		Dimension frameDimension = frame.getSize();
		Point frameLocation = frame.getLocation();
		dialog.setLocation(frameLocation.x + ((int) (frameDimension.getWidth() - dialogDimension.getWidth())) / 2,
				frameLocation.y + ((int) (frameDimension.getHeight() - dialogDimension.getHeight())) / 2);
	}

	/**
	 * @return
	 */
	public static JPanel getWelcomePanel() {

		return welcomePanel;
	}

	/**
	 * @return
	 */
	public JPanel getContent() {

		return content;
	}

	public static JPanel getOwnerDashboard() {
		return ownerDashboard;
	}

	public static void setOwnerDashboard(JPanel ownerDashboard) {
		AppFrame.ownerDashboard = ownerDashboard;
	}
	
}
