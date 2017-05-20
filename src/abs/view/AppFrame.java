package abs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import abs.view.GUIComponents.ABSMenuBar;
import abs.view.GUIComponents.StatusBar;
import abs.view.owner.OwnerDashboard;

/**
 * The ABS AppFrame.
 * 
 * @see javax.swing.JFrame JFrame
 * @serial 8091808348699216867
 * @since 1.0
 * @version 1.5
 */
public class AppFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8091808348699216867L;

	/** The welcome panel. */
	private static JPanel welcomePanel = new WelcomePanel();

	/** The content panel. */
	private static JPanel content = new JPanel();

	/** The owner dashboard. */
	private static JPanel ownerDashboard = new OwnerDashboard();

	/**
	 * Instantiates a new app frame.
	 */
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
	 * Sets the dialog location.
	 *
	 * @param frame
	 *            the JFrame
	 * @param dialog
	 *            the JDialog
	 * @see javax.swing.JFrame JFrame
	 * @see javax.swing.JDialog JDialog
	 */
	public static void setDialogLocation(JFrame frame, JDialog dialog) {
		Dimension dialogDimension = dialog.getSize();
		Dimension frameDimension = frame.getSize();
		Point frameLocation = frame.getLocation();
		dialog.setLocation(frameLocation.x + ((int) (frameDimension.getWidth() - dialogDimension.getWidth())) / 2,
				frameLocation.y + ((int) (frameDimension.getHeight() - dialogDimension.getHeight())) / 2);
	}

	/**
	 * Gets the welcome panel.
	 *
	 * @return the welcome panel
	 * 
	 * @see abs.view.WelcomePanel WelcomePanel
	 */
	public static JPanel getWelcomePanel() {

		return welcomePanel;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public JPanel getContent() {
		return content;
	}

	/**
	 * Gets the owner dashboard.
	 *
	 * @return the owner dashboard
	 * 
	 * @see abs.view.owner.OwnerDashboard OwnerDashboard
	 */
	public static JPanel getOwnerDashboard() {
		return ownerDashboard;
	}

	/**
	 * Sets the owner dashboard.
	 *
	 * @param ownerDashboard
	 *            the new owner dashboard
	 * 
	 * @see abs.view.owner.OwnerDashboard OwnerDashboard
	 */
	public static void setOwnerDashboard(JPanel ownerDashboard) {
		AppFrame.ownerDashboard = ownerDashboard;
	}

}
