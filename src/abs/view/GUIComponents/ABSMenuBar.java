package abs.view.GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import abs.controller.UserController;
import abs.view.AppFrame;

/**
 * The ABS MenuBar.
 * 
 * @version 1.0
 * @since 1.0
 * @see javax.swing.JMenuBar JMenuBar
 */
public class ABSMenuBar extends JMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8937570503113435406L;

	/** The login JMenuItem. */
	private static JMenuItem login;

	/** The register JMenuItem. */
	private static JMenuItem register;

	/** The logout JMenuItem. */
	private static JMenuItem logout;

	/**
	 * Instantiates a new ABS menu bar.
	 *
	 * @param appFrame
	 *            the app frame
	 */
	public ABSMenuBar(AppFrame appFrame) {

		// Options drop down
		JMenu optionsMenu = new JMenu("Options");

		optionsMenu.setMnemonic(KeyEvent.VK_O);
		add(optionsMenu);

		login = new JMenuItem("Login", KeyEvent.VK_A);
		register = new JMenuItem("Register", KeyEvent.VK_B);
		logout = new JMenuItem("Logout", KeyEvent.VK_B);
		JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));

		optionsMenu.add(login);
		optionsMenu.add(register);
		optionsMenu.add(logout);
		optionsMenu.addSeparator();
		optionsMenu.add(exitItem);

		logout.setEnabled(false);
		add(optionsMenu);

		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.loadLoginScreen();
			}
		});

		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.loadRegisterScreen();
			}
		});

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.logout();
			}
		});

	}

	/**
	 * Toggle button.
	 *
	 * @param button
	 *            the button to toggle
	 * @param state
	 *            the state to set it to (t/f)
	 */
	public static void toggleButton(String button, boolean state) {
		if (button.contains("login")) {
			login.setEnabled(state);
		} else if (button.contains("r")) {
			register.setEnabled(state);
		} else if (button.contains("logout")) {
			logout.setEnabled(state);
		}

	}

}
