package abs.view.customer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import abs.controller.CustomerController;
import abs.controller.Registry;
import abs.controller.UserController;
import abs.model.Booking;
import abs.model.Business;
import abs.model.Customer;
import abs.view.AppFrame;
import abs.view.GUIComponents.BsButton;
import abs.view.GUIComponents.BusInfoDialog;
import abs.view.factory.BookingFactory;
import abs.view.style.AppStyle;

/**
 * The customer dashboard.
 * 
 * <p>
 * the user can access all their functions from this panel.
 * </p>
 * 
 * @since 1.0
 * @version 1.5
 * @serial 5508017813109824688
 * @see javax.swing.JPanel JPanel
 * @see abs.controller.CustomerController Customer Controller
 * @see abs.model.Customer Customer
 */
public class CustomerDashboard extends JPanel {

	private static final long serialVersionUID = 5508017813109824688L;

	public CustomerDashboard(AppFrame appFrame) {

		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);
		this.setLayout(new BorderLayout());

		/* Business Selection Bar */
		JPanel busSelectBar = new JPanel();
		busSelectBar.setBorder(AppStyle.margin);
		busSelectBar.setBackground(AppStyle.mainForgroundColor);
		busSelectBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		ButtonGroup businessButtons = new ButtonGroup();
		List<BsButton> buttonArray = new ArrayList<BsButton>();

		for (Business bus : Registry.getUtils().getBusiness()) {
			BsButton busBut = new BsButton(bus.getName(), bus);
			businessButtons.add(busBut);
			busSelectBar.add(busBut);
			buttonArray.add(busBut);
			if (UserController.getActiveBusiness() == bus) {
				busBut.setEnabled(false);
			}
		}
		this.add(busSelectBar, BorderLayout.NORTH);

		/* Content */
		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);
		content.setLayout(new BorderLayout());

		JLabel title = new JLabel("Customer Dashboard");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		content.add(title, BorderLayout.NORTH);

		// Right panel, Bookings
		JPanel bookingsPanel = new JPanel();
		bookingsPanel.setLayout(new BorderLayout());
		bookingsPanel.setBorder(AppStyle.margin);
		bookingsPanel.setBackground(AppStyle.mainForgroundColor);
		JLabel subTitle = new JLabel("All Bookings");
		subTitle.setFont(AppStyle.boldMedFont);
		bookingsPanel.add(subTitle, BorderLayout.NORTH);

		JPanel bookPanel = new JPanel();
		bookPanel.setBackground(AppStyle.mainForgroundColor);
		bookPanel.setLayout(new GridLayout(0, 1));
		for (Booking booking : ((Customer) Registry.getUserAuth().getActiveUser()).getBookings()) {

			JPanel bookingSubPanel = new JPanel();
			bookingSubPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			bookingSubPanel.setBorder(AppStyle.margin);
			bookingSubPanel.setBackground(AppStyle.mainForgroundColor);

			JLabel busName = new JLabel(booking.getBusiness().getName() + ":");
			JTextPane field = BookingFactory.bookingPane(booking);
			field.setBackground(AppStyle.mainForgroundColor);
			bookingSubPanel.add(busName);
			bookingSubPanel.add(field);
			bookPanel.add(bookingSubPanel);
			bookingsPanel.add(bookPanel, BorderLayout.CENTER);
		}
		content.add(bookingsPanel, BorderLayout.EAST);

		// Left Panel ( Buttons & info)
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(AppStyle.margin);
		leftPanel.setBackground(AppStyle.mainForgroundColor);

		// Buttons Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(AppStyle.margin);
		buttonPanel.setBackground(AppStyle.mainForgroundColor);
		buttonPanel.setLayout(new GridLayout(0, 1));

		// User options buttons
		JButton addBooking = new JButton("Make a Booking");
		JButton removeBooking = new JButton("Remove a Booking");
		JButton viewBusInfo = new JButton("View Business Info");
		JButton logout = new JButton("Save & Logout");

		buttonPanel.add(addBooking);
		buttonPanel.add(removeBooking);
		buttonPanel.add(viewBusInfo);
		buttonPanel.add(logout);

		// User info panel
		JPanel info = new JPanel();
		info.setBorder(AppStyle.margin);
		info.setBackground(AppStyle.mainForgroundColor);
		info.setLayout(new GridLayout(0, 1));

		JLabel userName = new JLabel(Registry.getUserAuth().getActiveUser().getName());
		userName.setFont(AppStyle.boldMedFont);
		userName.setHorizontalAlignment(JLabel.LEFT);
		info.add(userName);

		int numBookings = ((Customer) Registry.getUserAuth().getActiveUser()).getBookings().size();
		JLabel noBookings = new JLabel("Total Current Bookings: " + String.valueOf(numBookings));
		noBookings.setFont(AppStyle.boldMedFont);
		noBookings.setHorizontalAlignment(JLabel.LEFT);
		info.add(noBookings);

		leftPanel.setLayout(new GridLayout(0, 1));
		leftPanel.add(info);
		leftPanel.add(buttonPanel);

		content.add(leftPanel, BorderLayout.WEST);
		this.add(content, BorderLayout.CENTER);

		/* Listeners */
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
				BusInfoDialog infoDoalog = new BusInfoDialog(appFrame);
				AppFrame.setDialogLocation(appFrame, infoDoalog);
				infoDoalog.setVisible(true);
			}
		});

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.logout();
			}
		});

		for (int i = 0; i < buttonArray.size(); i++) {
			buttonArray.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BsButton butt = (BsButton) e.getSource();
					UserController.setActiveBusiness(butt.getBusiness());
					CustomerController.dashboard();
				}
			});
		}

	}
}
