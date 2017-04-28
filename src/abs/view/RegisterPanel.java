package abs.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abs.controller.UserController;
import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.view.style.AppStyle;

/**
 *
 */
public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 7012676670912272410L;

	private static JLabel status;

	public RegisterPanel() {
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Register");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel registerInfo = new JPanel();
		// registerInfo.setLayout(new GridLayout(0, 2));

		JLabel name = new JLabel("Name:");
		JTextField nameField = new JTextField(15);
		registerInfo.add(name);
		registerInfo.add(nameField);

		JLabel email = new JLabel("Email:");
		JTextField emailField = new JTextField(15);
		registerInfo.add(email);
		registerInfo.add(emailField);

		JLabel pass = new JLabel("Password:");
		JTextField passField = new JTextField(15);
		registerInfo.add(pass);
		registerInfo.add(passField);

		JLabel address = new JLabel("Address:");
		JTextField addressField = new JTextField(15);
		registerInfo.add(address);
		registerInfo.add(addressField);

		JLabel phone = new JLabel("Phone Number:");
		JTextField phoneField = new JTextField(15);
		registerInfo.add(phone);
		registerInfo.add(phoneField);

		JLabel owner = new JLabel("Are you a bussiness owner:");
		JCheckBox ownerField = new JCheckBox();
		registerInfo.add(owner);
		registerInfo.add(ownerField);

		JButton back = new JButton("Back");
		JButton register = new JButton("Register");
		registerInfo.add(register);
		registerInfo.add(back);

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(registerInfo, BorderLayout.CENTER);
		
		status = new JLabel("");
		status.setHorizontalAlignment(JLabel.CENTER);
		content.add(status, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.reloadWelcomeScreen();
			}

		});

		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserController.register(nameField.getText(), emailField.getText(), passField.getText(),
							addressField.getText(), phoneField.getText(), ownerField.isSelected());
				} catch (CredentialsInvalidException e1) {
					getStatus().setText(e1.getMessage());

				} catch (RegistrationValidationException e1) {
					getStatus().setText(e1.getMessage());

				} catch (RegistrationNonUniqueException e1) {
					getStatus().setText(e1.getMessage());

				}
			}

		});
	}

	public static JLabel getStatus() {
		return status;
	}

}
