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

public class AddEmployee extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1128355995004422553L;
	
	public AddEmployee(){
		this.setBorder(AppStyle.margin);
		this.setBackground(AppStyle.mainBackgroundColor);

		JPanel content = new JPanel();
		content.setBorder(AppStyle.margin);
		content.setBackground(AppStyle.mainForgroundColor);

		JLabel title = new JLabel("Add an Employee");
		title.setFont(AppStyle.boldLargeFont);
		title.setHorizontalAlignment(JLabel.CENTER);

		JPanel employeeInfo = new JPanel();

		JLabel name = new JLabel("Name of Employee:");
		JTextField nameField = new JTextField(15);
		employeeInfo.add(name);
		employeeInfo.add(nameField);
		

		content.setLayout(new BorderLayout());
		content.add(title, BorderLayout.NORTH);
		content.add(employeeInfo, BorderLayout.CENTER);
		
		JButton back = new JButton("Back");
		JButton book = new JButton("Add");
		employeeInfo.add(book);
		employeeInfo.add(back);
		
		//takes an array of business names to select from
		List<String> businesses = OwnerController.getBusinessNames();
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
		
		
		JComboBox<Object> businessSelect = new JComboBox<>(displayBusinesses.toArray());
		employeeInfo.add(businessSelect);
		
		
		

		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.reloadDashboard();
			}

		});
		
		book.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnerController.addEmployee(nameField.getText(), businessSelect.getSelectedItem());
			}

		});
	}

	
}
