package abs.view.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import abs.controller.CustomerController;
import abs.model.Booking;
import abs.view.GUIComponents.AvButton;
import abs.view.factory.BookingFactory;

/**
 *
 */
public class RemoveBookingPanel extends JPanel {

	private static final long serialVersionUID = 311702363942509643L;

	public RemoveBookingPanel(List<Booking> bookings) {
		JPanel cancelPanel = new JPanel();
		cancelPanel.setLayout(new GridLayout(0, 2));

		List<AvButton> aButts = new ArrayList<AvButton>();

		for (Booking booking : bookings) {

			JTextPane field = BookingFactory.bookingPane(booking);
			AvButton buttonRem = new AvButton("Remove");
			buttonRem.setBooking(booking);
			aButts.add(buttonRem);
			cancelPanel.add(field);
			cancelPanel.add(buttonRem);

		}

		// Add listener for the button, or link to private method.
		for (int i = 0; i < aButts.size(); i++) {
			aButts.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AvButton butt = (AvButton) e.getSource();
					CustomerController.removeBooking(butt.getBooking());
				}
			});
		}

		this.add(cancelPanel);

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
