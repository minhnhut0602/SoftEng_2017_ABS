package abs.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abs.controller.UserController;
import abs.view.style.AppStyle;

/**
 * The Class WelcomePanel.
 * 
 * @since 1.0
 * @version 1.0
 * @see javax.swing.JPanel JPanel
 */
public class WelcomePanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3600797565957836983L;

	/**
	 * Instantiates a new welcome panel.
	 */
	public WelcomePanel() {

		this.setBorder(AppStyle.margin);
		setBackground(AppStyle.mainBackgroundColor);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton login = new JButton("Login");
		JButton register = new JButton("Register");

		buttonPanel.add(login);
		buttonPanel.add(register);

		buttonPanel.setBorder(AppStyle.margin);
		buttonPanel.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Welcome to the Appointment Booking System");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel info = new JPanel();
		info.setBorder(AppStyle.margin);
		info.setBackground(AppStyle.mainForgroundColor);
		info.add(title);

		this.setLayout(new GridLayout(0, 1));
		this.add(info);
		this.add(buttonPanel);

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
	}

}
