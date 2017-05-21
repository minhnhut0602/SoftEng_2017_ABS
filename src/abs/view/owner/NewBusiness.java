package abs.view.owner;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abs.controller.OwnerController;
import abs.view.style.AppStyle;

public class NewBusiness extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6588274112916489787L;

	/**
	 * Very similar to the register as a new user page window with appropriate
	 * text fields to register a new business
	 */
	public NewBusiness() {
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Add a new Business");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel businessInfo = new JPanel();
		businessInfo.setBorder(AppStyle.margin);
		businessInfo.setBackground(AppStyle.mainForgroundColor);
		businessInfo.setLayout(new GridBagLayout());
		// registerInfo.setLayout(new GridLayout(0, 2));
		
		JPanel registerBusiness = new JPanel();
		registerBusiness.setLayout(new GridLayout(0, 1));
		registerBusiness.setBorder(AppStyle.margin);
		registerBusiness.setBackground(AppStyle.mainForgroundColor);

		JLabel name = new JLabel("Name:");
		JTextField nameField = new JTextField(15);
		registerBusiness.add(name);
		registerBusiness.add(nameField);

		JLabel desc = new JLabel("Description:");
		JTextField descField = new JTextField(15);
		registerBusiness.add(desc);
		registerBusiness.add(descField);

		JLabel address = new JLabel("Address:");
		JTextField addressField = new JTextField(15);
		registerBusiness.add(address);
		registerBusiness.add(addressField);

		JLabel phone = new JLabel("Phone Number:");
		JTextField phoneField = new JTextField(15);
		registerBusiness.add(phone);
		registerBusiness.add(phoneField);

		JButton back = new JButton("Back");
		JButton register = new JButton("Register");
		registerBusiness.add(register);
		registerBusiness.add(back);
		
		businessInfo.add(registerBusiness);

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(businessInfo, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.reloadDashboard();
			}

		});

		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// make the business object
				OwnerController.registerBusiness(nameField.getText(), descField.getText(), addressField.getText(),
						phoneField.getText());
			}

		});
	}

}
