package abs.view.owner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abs.controller.OwnerController;
import abs.model.Business;
import abs.model.Customer;
import abs.view.GUIComponents.AvButton;
import abs.view.factory.BookingFactory;
import abs.view.style.AppStyle;

public class ShowAvailabilities extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036821010971686445L;

	public ShowAvailabilities(Customer c, Business b) {

		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Book for a Customer");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		content.add(title, BorderLayout.NORTH);

		JPanel selectAvailabilities = new JPanel();
		selectAvailabilities.setLayout(new GridLayout(0,1));
		selectAvailabilities.setBorder(AppStyle.margin);
		selectAvailabilities.setBackground(AppStyle.mainForgroundColor);
		

		List<AvButton> aButts = new ArrayList<AvButton>();

		if (b.getAvBookings().size() == 0 || b.getAvBookings() == null) {
			// print a message
			JLabel message = new JLabel("There's no available booking times for this business");
			message.setFont(AppStyle.boldMedFont);
			message.setHorizontalAlignment(JLabel.CENTER);

			JPanel display = new JPanel();
			display.setBorder(AppStyle.margin);
			display.setBackground(AppStyle.mainForgroundColor);
			display.add(message);

		} else {
			
			ButtonGroup group = new ButtonGroup();
			// create a button for each availability
			for (int i = 0; i < b.getAvBookings().size(); i++) {
				// check if available or booked and add accordingly
				if (b.getAvBookings().get(i).getStatus().compareTo("Available") == 0) {
					
					AvButton butt = BookingFactory.bookingButton(b.getAvBookings().get(i));
					group.add(butt);
					aButts.add(butt);
					selectAvailabilities.add(butt);
//					aButts.add(i, new AvButton(b.getAvBookings().get(i).getSlot().getDate() + " -> "
//							+ b.getAvBookings().get(i).getSlot().getTime()));
//					aButts.get(i).setBackground(Color.GREEN);
//					aButts.get(i).setBooking(b.getAvBookings().get(i));
//					aButts.get(i).setEnabled(true);
//					selectAvailabilities.add(aButts.get(i));
				} else {
					// must be booked already
					AvButton butt = BookingFactory.bookingButton(b.getAvBookings().get(i));
					group.add(butt);
					aButts.add(butt);
					selectAvailabilities.add(butt);
//					aButts.add(i, new AvButton(b.getAvBookings().get(i).getSlot().getDate() + " -> "
//							+ b.getAvBookings().get(i).getSlot().getTime()));
//					aButts.get(i).setBackground(Color.RED);
//					aButts.get(i).setBooking(b.getAvBookings().get(i));
//					aButts.get(i).setEnabled(false);
//					selectAvailabilities.add(aButts.get(i));
				}

			}
		}

		// add back button
		JButton back = new JButton("Home");
		selectAvailabilities.add(back);

		selectAvailabilities.setBorder(AppStyle.margin);
		selectAvailabilities.setBackground(AppStyle.mainForgroundColor);

		JPanel info = new JPanel();
		info.setBorder(AppStyle.margin);
		info.setBackground(AppStyle.mainForgroundColor);
		info.add(title);

		this.setLayout(new GridLayout(0, 1));
		this.add(info);
		this.add(selectAvailabilities);

		// create the action listener for all of them
		for (int i = 0; i < aButts.size(); i++) {
			aButts.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AvButton butt = (AvButton) e.getSource();
					// pass back to controller
					OwnerController.createCustBooking(c, b, butt.getBooking());
				}
			});
		}

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.reloadDashboard();
			}

		});

	}

}
