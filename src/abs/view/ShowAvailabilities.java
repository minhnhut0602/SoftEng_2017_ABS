package abs.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
		
		List<JButton> aButts = new ArrayList<JButton>();
		
		//create a button for each availability
		for(int i = 0; i < b.getAvBookings().size(); i++){
			aButts.add(i, new JButton(b.getAvBookings().get(i).getSlot().getDate() + " -> " + b.getAvBookings().get(i).getSlot().getTime()));
			selectAvailabilities.add(aButts.get(i));
		}
		
		//create the action listener for all of them
		for(int i = 0; i < aButts.size(); i++){
			aButts.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					//pass back to controller
					OwnerController.
				}
			});
		}
		
	}

}
