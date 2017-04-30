package abs.view.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import abs.controller.CustomerController;
import abs.model.Booking;
import abs.view.GUIComponents.AvButton;
import abs.view.factory.BookingFactory;

public class BookingPanel extends JPanel {

	private static final long serialVersionUID = 9167183798502593801L;

	public BookingPanel(List<Booking> bookings) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));

		ButtonGroup group = new ButtonGroup();
		List<AvButton> aButts = new ArrayList<AvButton>();

		for (Booking booking : bookings) {
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
					CustomerController.addBooking(butt.getBooking());
				}
			});
		}
		this.add(buttonPanel);

		JButton back = new JButton("Back");

		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerController.dashboard();
			}

		});

	}

}
