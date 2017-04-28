package abs.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abs.controller.UserController;
import abs.exceptions.CredentialsInvalidException;
import abs.view.style.AppStyle;

/**
 *
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 9010219234482904288L;

	public LoginPanel() {

		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Login");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JButton back = new JButton("Back");
		JButton login = new JButton("Login");

		JPanel loginInfo = new JPanel();
		JLabel email = new JLabel("Email:");
		JTextField emailField = new JTextField(15);
		JLabel pass = new JLabel("Password:");
		JTextField passField = new JTextField(15);

		loginInfo.add(email);
		loginInfo.add(emailField);
		loginInfo.add(pass);
		loginInfo.add(passField);
		loginInfo.add(login);
		loginInfo.add(back);

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(loginInfo, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

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
					UserController.login(emailField.getText(), passField.getText());
				} catch (CredentialsInvalidException e1) {
					// TODO Auto-generated catch block

				}
			}

		});
	}

}
