package abs.view;

import java.awt.BorderLayout;
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
import abs.controller.UserController;
import abs.model.Business;
import abs.model.Employee;
import abs.model.Owner;
import abs.view.style.AppStyle;

public class AddAvEmployee extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2882510959605155735L;
	
	
	public AddAvEmployee(){
		
		
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Add an Availability!");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel availInfo = new JPanel();

		//fields
		
		JLabel time = new JLabel("Time:");
		final JTextField timeField = new JTextField(15);
		availInfo.add(time);
		availInfo.add(timeField);

		JLabel date = new JLabel("Date:");
		final JTextField dateField = new JTextField(15);
		availInfo.add(date);
		availInfo.add(dateField);
		

		
		
		//cast active user to owner
		Owner o = (Owner) Registry.getUserAuth().getActiveUser();
		
		List<Business> businesses = o.getBusinesses();
		
		if(businesses.size() == 0 || businesses == null){
			//print a message
			JLabel message = new JLabel("You don't own a business, how will you have employees?");
			message.setFont(AppStyle.boldMedFont);
			message.setHorizontalAlignment(JLabel.CENTER);
		}else{
			
			
			List<String> displayEmployees = new ArrayList<String>();
			
			//get rid of businesses that you aren't the owner of
			for(int i = 0; i < businesses.size(); i++){
				for(int j = 0; j < o.getBusinesses().size(); j++){
					if(businesses.get(i).getName().compareTo(o.getBusinesses().get(j).getName()) == 0){
						//then add the employees of that business
						for(Employee e: businesses.get(i).getStaff()){
							displayEmployees.add(e.getName());
						}
					}
				}
			}
			
			List<String> displayBusinesses = new ArrayList<String>();
			
			//get rid of businesses that you aren't the owner of
			for(int i = 0; i < businesses.size(); i++){
				for(int j = 0; j < o.getBusinesses().size(); j++){
					if(businesses.get(i).getName().compareTo(o.getBusinesses().get(j).getName()) == 0){
						//then get rid of them off the list
						displayBusinesses.add(businesses.get(i).getName());
					}
				}
			}
			
			final JComboBox<Object> businessSelect = new JComboBox<>(displayBusinesses.toArray());
			availInfo.add(businessSelect);
			
			final JComboBox<Object> employeeSelect = new JComboBox<>(displayEmployees.toArray());
			availInfo.add(employeeSelect);
			
			
			JButton addAv = new JButton("Add");
			availInfo.add(addAv);
			
			
			addAv.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					OwnerController.addAvBooking(timeField.getText(), dateField.getText(), (String)employeeSelect.getSelectedItem(), (String)businessSelect.getSelectedItem());
				}

			});
		}
		
		

		JButton back = new JButton("Back");
		availInfo.add(back);
		

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(availInfo, BorderLayout.CENTER);
		

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.reloadWelcomeScreen();
			}

		});
	}

}
