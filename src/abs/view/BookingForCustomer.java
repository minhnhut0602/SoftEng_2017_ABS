package abs.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abs.controller.OwnerController;
import abs.controller.Registry;
import abs.model.Owner;
import abs.view.style.AppStyle;

public class BookingForCustomer extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5926808669248494165L;
	
	public BookingForCustomer(){
		
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Book for a Customer");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel customerBookingInfo = new JPanel();

		JLabel email = new JLabel("Email:");
		final JTextField emailField = new JTextField(15);
		customerBookingInfo.add(email);
		customerBookingInfo.add(emailField);
		

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(customerBookingInfo, BorderLayout.CENTER);
		
		JButton back = new JButton("Back");
		JButton book = new JButton("Next");
		customerBookingInfo.add(book);
		customerBookingInfo.add(back);
		
		//takes an array of business names to select from
		List<String> businesses = OwnerController.getBusinessNames();
		
		if(businesses.size() == 0 || businesses == null){
			//print a message
			JLabel message = new JLabel("You don't own a business, how will you add a booking?");
			message.setFont(AppStyle.boldMedFont);
			message.setHorizontalAlignment(JLabel.CENTER);
		}else{
			//cast active user to owner
			Owner o = (Owner) Registry.getUserAuth().getActiveUser();
			
			List<String> displayBusinesses = new ArrayList<String>();
			
			//get rid of businesses that you aren't the owner of
			for(int i = 0; i < businesses.size(); i++){
				for(int j = 0; j < o.getBusinesses().size(); j++){
					if(businesses.get(i).compareTo(o.getBusinesses().get(j).getName()) == 0){
						//then get rid of them off the list
						displayBusinesses.add(businesses.get(i));
					}
				}
			}
			
			final JComboBox<Object> businessSelect = new JComboBox<>(displayBusinesses.toArray());
			customerBookingInfo.add(businessSelect);
			
			book.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					OwnerController.checkEmail(emailField.getText(), businessSelect.getSelectedItem());
				}

			});
		}
		

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.reloadDashboard();
			}

		});
		

		
	}
	

}
