package abs.view.customer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import abs.controller.CustomerController;
import abs.model.Booking;
import abs.view.GUIComponents.AvButton;
import abs.view.factory.BookingFactory;
import abs.view.style.AppStyle;

/**
 * TODO Javadocs
 */
public class RemoveBookingPanel extends JPanel {

	private static final long serialVersionUID = 311702363942509643L;

	public RemoveBookingPanel(List<Booking> bookings) {

		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);
		this.setLayout(new BorderLayout());

		JPanel cancelPanel = new JPanel();
		cancelPanel.setLayout(new GridLayout(0, 1));
		cancelPanel.setBorder(AppStyle.margin);
		cancelPanel.setBackground(AppStyle.mainForgroundColor);

		List<AvButton> aButts = new ArrayList<AvButton>();
		for (Booking booking : bookings) {
			JPanel bookingPanel = new JPanel();
			bookingPanel.setLayout(new FlowLayout());
			JLabel busName = new JLabel(booking.getBusiness().getName() + ":");
			JTextPane field = BookingFactory.bookingPane(booking);
			AvButton buttonRem = new AvButton("Remove");
			buttonRem.setBooking(booking);
			aButts.add(buttonRem);
			bookingPanel.add(busName);
			bookingPanel.add(field);
			bookingPanel.add(buttonRem);
			cancelPanel.add(bookingPanel);
		}

		this.add(cancelPanel, BorderLayout.CENTER);

		JButton back = new JButton("Back");
		this.add(back, BorderLayout.SOUTH);

		/* Listeners */
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerController.dashboard();
			}

		});

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

	}

}
