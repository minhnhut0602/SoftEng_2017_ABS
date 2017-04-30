package abs.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import abs.controller.CustomerController;
import abs.controller.UserController;
import abs.model.Booking;
import abs.view.factory.BookingFactory;

public class BookingPanel extends JPanel {

	private static final long serialVersionUID = 9167183798502593801L;

	public BookingPanel(List<Booking> bookings) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));

		ButtonGroup group = new ButtonGroup();

		for (Booking booking : bookings) {
			JButton button = BookingFactory.bookingButton(booking);
			group.add(button);
			buttonPanel.add(button);

			// Add listener for the button, or link to private method.
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Add bookings

					// Refresh screen
					CustomerController.addBooking(booking);
				}
			});
		}

		JButton back = new JButton("Back");

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerController.dashboard();
			}

		});

	}

}
