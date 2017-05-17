package abs.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import abs.controller.UserController;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.view.style.AppStyle;

/**
 * TODO Javadocs
 */
public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 7012676670912272410L;

	private static JLabel status;

	public RegisterPanel(AppFrame appFrame) {
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Register");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel registerInfo = new JPanel();
		registerInfo.setBorder(AppStyle.margin);
		registerInfo.setBackground(AppStyle.mainForgroundColor);
		registerInfo.setLayout(new GridBagLayout());

		JPanel registerForm = new JPanel();
		registerForm.setLayout(new GridLayout(0, 1));
		registerForm.setBorder(AppStyle.margin);
		registerForm.setBackground(AppStyle.mainForgroundColor);

		JLabel name = new JLabel("Name:");
		final JTextField nameField = new JTextField(15);
		registerForm.add(name);
		registerForm.add(nameField);

		JLabel email = new JLabel("Email:");
		final JTextField emailField = new JTextField(15);
		registerForm.add(email);
		registerForm.add(emailField);

		JLabel pass = new JLabel("Password:");
		final JPasswordField passField = new JPasswordField(15);
		registerForm.add(pass);
		registerForm.add(passField);

		JLabel address = new JLabel("Address:");
		final JTextField addressField = new JTextField(15);
		registerForm.add(address);
		registerForm.add(addressField);

		JLabel phone = new JLabel("Phone Number:");
		final JTextField phoneField = new JTextField(15);
		registerForm.add(phone);
		registerForm.add(phoneField);

		JPanel ownerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ownerPanel.setBackground(AppStyle.mainForgroundColor);
		JLabel owner = new JLabel("Are you a bussiness owner?");
		final JCheckBox ownerField = new JCheckBox();
		ownerPanel.add(owner);
		ownerPanel.add(ownerField);
		registerForm.add(ownerPanel);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(AppStyle.mainForgroundColor);
		JButton back = new JButton("Back");
		JButton register = new JButton("Register");
		buttonPanel.add(register);
		buttonPanel.add(back);
		registerForm.add(buttonPanel);

		registerInfo.add(registerForm);
		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(registerInfo, BorderLayout.CENTER);

		status = new JLabel("");
		status.setHorizontalAlignment(JLabel.CENTER);
		content.add(status, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		appFrame.getRootPane().setDefaultButton(register);

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
					UserController.register(nameField.getText(), emailField.getText(),
							String.valueOf(passField.getPassword()), addressField.getText(), phoneField.getText(),
							ownerField.isSelected());

				} catch (RegistrationValidationException e1) {
					getStatus().setText(e1.getMessage());

				} catch (RegistrationNonUniqueException e1) {
					getStatus().setText(e1.getMessage());

				} catch (NumberFormatException e1) {
					getStatus().setText("Sorry but the Phone Number can only contain digits");
				}
			}
		});
	}

	public static JLabel getStatus() {
		return status;
	}

}
