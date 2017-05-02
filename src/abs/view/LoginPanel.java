package abs.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import abs.controller.UserController;
import abs.exceptions.CredentialsInvalidException;
import abs.view.style.AppStyle;

/**
 * The LoginPanel.
 * 
 * @since 1.0
 * @version 1.1
 * @see javax.swing.JPanel JPanel
 */
public class LoginPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9010219234482904288L;

	/** The status JLabel */
	private static JLabel status;

	/**
	 * Instantiates a new login panel.
	 *
	 * @param appFrame
	 *            the app frame for default button
	 */
	public LoginPanel(AppFrame appFrame) {

		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Login");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel loginInfo = new JPanel();
		loginInfo.setBorder(AppStyle.margin);
		loginInfo.setBackground(AppStyle.mainForgroundColor);
		loginInfo.setLayout(new GridBagLayout());

		JPanel loginForm = new JPanel();
		loginForm.setLayout(new GridLayout(0, 1));
		loginForm.setBackground(AppStyle.mainForgroundColor);

		JLabel email = new JLabel("Email:");
		final JTextField emailField = new JTextField(15);
		loginForm.add(email);
		loginForm.add(emailField);

		JLabel pass = new JLabel("Password:");
		final JPasswordField passField = new JPasswordField(15);
		loginForm.add(pass);
		loginForm.add(passField);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(AppStyle.mainForgroundColor);
		JButton back = new JButton("Back");
		JButton login = new JButton("Login");
		buttonPanel.add(login);
		buttonPanel.add(back);
		loginForm.add(buttonPanel);

		loginInfo.add(loginForm);
		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(loginInfo, BorderLayout.CENTER);

		status = new JLabel("");
		status.setHorizontalAlignment(JLabel.CENTER);
		content.add(status, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		appFrame.getRootPane().setDefaultButton(login);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.reloadWelcomeScreen();
			}

		});

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserController.login(emailField.getText(), String.valueOf(passField.getPassword()));
				} catch (CredentialsInvalidException e1) {
					getStatus().setText(e1.getMessage());
				}
			}

		});
	}

	/**
	 * Gets the status JLabel object.
	 *
	 * @return the status
	 */
	public static JLabel getStatus() {
		return status;
	}
}
