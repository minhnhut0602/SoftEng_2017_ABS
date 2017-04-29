package abs.controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abs.controller.OwnerController;
import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.view.style.AppStyle;

public class BookingForCustomer extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5926808669248494165L;
	
	public BookingForCustomer(){
		
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Book for a Customer");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel customerBookingInfo = new JPanel();

		JLabel email = new JLabel("Email:");
		JTextField emailField = new JTextField(15);
		customerBookingInfo.add(email);
		customerBookingInfo.add(emailField);
		
		//need to add some sort of way to select availabilities
		

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(customerBookingInfo, BorderLayout.CENTER);
		
		JButton back = new JButton("Back");
		JButton book = new JButton("Book");
		customerBookingInfo.add(book);
		customerBookingInfo.add(back);
		

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.reloadDashboard();
			}

		});
		
		book.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.bookForCust(emailField.getText());
			}

		});
		
	}
	

}
