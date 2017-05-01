package abs.view.owner;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abs.controller.OwnerController;
import abs.view.style.AppStyle;

public class OwnerDashboard extends JPanel {
	
	private static final long serialVersionUID = -6219278878422560477L;

	/**
	 * 
	 */
	public OwnerDashboard() {

		this.setBorder(AppStyle.margin);
		setBackground(AppStyle.mainBackgroundColor);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		//makes the buttons
		JButton addBusiness = new JButton("Add Business");
		JButton addEmployee = new JButton("Add Employee");
		JButton addAvail = new JButton("Add Availability");
		JButton deleteAvail = new JButton("Delete Availability");
		JButton makeCustBooking = new JButton("Make Booking For Customer");
		JButton logout = new JButton("Save & Logout");

		//adds the buttons to the frame
		buttonPanel.add(addBusiness);
		buttonPanel.add(addEmployee);
		buttonPanel.add(addAvail);
		buttonPanel.add(deleteAvail);
		buttonPanel.add(makeCustBooking);
		buttonPanel.add(logout);

		buttonPanel.setBorder(AppStyle.margin);
		buttonPanel.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Owner Dashboard");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel info = new JPanel();
		info.setBorder(AppStyle.margin);
		info.setBackground(AppStyle.mainForgroundColor);
		info.add(title);

		this.setLayout(new GridLayout(0, 1));
		this.add(info);
		this.add(buttonPanel);

		addBusiness.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.newBusiness();
			}
		});

		addEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.addEmployee();
			}
		});
		
		addAvail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.addAvBooking();
			}
		});
		
		deleteAvail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.removeBooking();
			}
		});
		
		makeCustBooking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.makeCustBooking();
			}
		});
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.logout();
			}
		});
	}

}
