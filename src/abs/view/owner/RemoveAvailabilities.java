package abs.view.owner;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import abs.controller.OwnerController;
import abs.model.Booking;
import abs.view.GUIComponents.AvButton;
import abs.view.factory.BookingFactory;

public class RemoveAvailabilities extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5640176172325603956L;
	
	private List<Booking> bookingsList = null;

	public RemoveAvailabilities(List<Booking> bookings) {
		
		this.bookingsList = bookings;
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));

		ButtonGroup group = new ButtonGroup();
		List<AvButton> aButts = new ArrayList<AvButton>();

		for (Booking booking : bookingsList) {
			AvButton button = BookingFactory.bookingButton(booking);
			group.add(button);
			aButts.add(button);
			buttonPanel.add(button);

		}

		// Add listener for the button, or link to private method.
		for (int i = 0; i < aButts.size(); i++) {
			aButts.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					AvButton butt = (AvButton) e.getSource();
					butt.setVisible(false);
					OwnerController.deleteBooking(butt.getBooking());
					refresh();
					
				}
			});
		}
		this.add(buttonPanel);

		JButton back = new JButton("Back");

		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.reloadDashboard();
			}

		});

	}
	
	public void refresh(){
		if(bookingsList.size() > 0){
			this.bookingsList = bookingsList.get(0).getBusiness().getAvBookings();
		}
	}

	public List<Booking> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(List<Booking> bookingsList) {
		this.bookingsList = bookingsList;
	}
}