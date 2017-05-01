package abs.view.customer;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abs.controller.CustomerController;
import abs.controller.UserController;
import abs.view.style.AppStyle;

/**
 *
 */
public class CustomerDashboard extends JPanel {

	private static final long serialVersionUID = 5508017813109824688L;

	public CustomerDashboard() {

		this.setBorder(AppStyle.margin);
		setBackground(AppStyle.mainBackgroundColor);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		// TODO add bus selection bar

		// TODO Show user's name and number of current bookings

		// TODO show users bookings on screen

		// makes the buttons
		JButton addBooking = new JButton("Make a Booking");
		JButton removeBooking = new JButton("Remove a Booking");
		JButton viewBusInfo = new JButton("View Business Info");

		JButton logout = new JButton("Save & Logout");

		// adds the buttons to the frame
		buttonPanel.add(addBooking);
		buttonPanel.add(removeBooking);
		buttonPanel.add(viewBusInfo);

		buttonPanel.add(logout);

		buttonPanel.setBorder(AppStyle.margin);
		buttonPanel.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Customer Dashboard");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel info = new JPanel();
		info.setBorder(AppStyle.margin);
		info.setBackground(AppStyle.mainForgroundColor);
		info.add(title);

		this.setLayout(new GridLayout(0, 1));
		this.add(info);
		this.add(buttonPanel);

		addBooking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerController.addBooking(null);
			}
		});

		removeBooking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerController.removeBooking(null);
			}
		});

		viewBusInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO add dialog
			}
		});

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.logout();
			}
		});

	}
}
