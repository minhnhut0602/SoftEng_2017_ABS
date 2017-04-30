package abs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abs.controller.OwnerController;
import abs.controller.Registry;
import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Business;
import abs.model.Customer;
import abs.view.style.AppStyle;

public class ShowAvailabilities extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5036821010971686445L;

	public ShowAvailabilities(Customer c, Business b){
		
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Book for a Customer");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel selectAvailabilities = new JPanel();
		selectAvailabilities.setLayout(new FlowLayout());
		
		List<AvButton> aButts = new ArrayList<AvButton>();
		
		//create a button for each availability
		for(int i = 0; i < b.getAvBookings().size(); i++){
			//check if available or booked and add accordingly
			if(b.getAvBookings().get(i).getStatus().compareTo("Available") == 0){
				aButts.add(i, new AvButton(b.getAvBookings().get(i).getSlot().getDate() + " -> " + b.getAvBookings().get(i).getSlot().getTime()));
				aButts.get(i).setBackground(Color.GREEN);
				aButts.get(i).setBooking(b.getAvBookings().get(i));
				aButts.get(i).setEnabled(true);
				selectAvailabilities.add(aButts.get(i));
			}else{
				//must be booked already
				aButts.add(i, new AvButton(b.getAvBookings().get(i).getSlot().getDate() + " -> " + b.getAvBookings().get(i).getSlot().getTime()));
				aButts.get(i).setBackground(Color.RED);
				aButts.get(i).setBooking(b.getAvBookings().get(i));
				aButts.get(i).setEnabled(false);
				selectAvailabilities.add(aButts.get(i));
			}
			
		}
		
		selectAvailabilities.setBorder(AppStyle.margin);
		selectAvailabilities.setBackground(AppStyle.mainForgroundColor);
		
		JPanel info = new JPanel();
		info.setBorder(AppStyle.margin);
		info.setBackground(AppStyle.mainForgroundColor);
		info.add(title);

		this.setLayout(new GridLayout(0, 1));
		this.add(info);
		this.add(selectAvailabilities);
		
		//create the action listener for all of them
		for(int i = 0; i < aButts.size(); i++){
			aButts.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					AvButton butt = (AvButton) e.getSource();
					//pass back to controller
					OwnerController.createCustBooking(c, b, butt.getBooking());
				}
			});
		}
		
	}

}
