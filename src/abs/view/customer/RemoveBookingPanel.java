package abs.view.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import abs.controller.CustomerController;
import abs.model.Booking;

/**
 *
 */
public class RemoveBookingPanel extends JPanel {

	private static final long serialVersionUID = 311702363942509643L;

	public RemoveBookingPanel(List<Booking> bookings) {
		// TODO Auto-generated constructor stub

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
