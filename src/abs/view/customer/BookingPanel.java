package abs.view.customer;

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

import abs.controller.CustomerController;
import abs.model.Booking;
import abs.view.GUIComponents.AvButton;
import abs.view.factory.BookingFactory;
import abs.view.style.AppStyle;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static java.lang.Integer.parseInt;

public class BookingPanel extends JPanel {

	private static final long serialVersionUID = 9167183798502593801L;

	public BookingPanel(List<Booking> bookings) {

		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);
		this.setLayout(new BorderLayout());

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBorder(AppStyle.margin);
		infoPanel.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel(bookings.get(0).getBusiness().getName());
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(title, BorderLayout.NORTH);

		JLabel subTitle = new JLabel("Add a booking");
		subTitle.setFont(AppStyle.boldMedFont);
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(subTitle, BorderLayout.CENTER);

		this.add(infoPanel, BorderLayout.NORTH);
                
                JPanel availInfo = new JPanel();
                
                JLabel lblBlockCount = new JLabel("Block Count:");
		final JTextField blockCount = new JTextField(15);
		availInfo.add(lblBlockCount);
		availInfo.add(blockCount);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));
		buttonPanel.setBorder(AppStyle.margin);
		buttonPanel.setBackground(AppStyle.mainForgroundColor);

		ButtonGroup group = new ButtonGroup();
		List<AvButton> aButts = new ArrayList<AvButton>();
                buttonPanel.add(availInfo);
		for (Booking booking : bookings) {
			AvButton button = BookingFactory.bookingButton(booking);
			group.add(button);
			aButts.add(button);
			buttonPanel.add(button);
		}

		this.add(buttonPanel, BorderLayout.CENTER);

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
                                        int nBlockCount = 0;

                                        if (blockCount.getText().compareTo("") == 0) {
                                            JOptionPane.showMessageDialog(null, "Please input Block Count.");
                                            blockCount.setText("");
                                            return;
                                        }
                                        else {
                                            //if (blockCount.getText())
                                            try{
                                                nBlockCount = parseInt(blockCount.getText());
                                            }
                                            catch(Exception ex){
                                                JOptionPane.showMessageDialog(null, "Block Count isn't number.");
                                                blockCount.setText("");
                                                return;
                                            }
                                        }
                                                                                
                                        AvButton butt = (AvButton) e.getSource();
                                        if (butt.getBooking().getSlot().getBlocks() < nBlockCount){
                                            JOptionPane.showMessageDialog(null, "There isn't enough available time.");
                                            blockCount.setText("");
                                            return;
                                        }
                                        
                                        
                                        butt.getBooking().getSlot().setBlockCount(nBlockCount);
					CustomerController.addBooking(butt.getBooking());
                                        blockCount.setText("");
				}
                         
			});
		}

	}

}
