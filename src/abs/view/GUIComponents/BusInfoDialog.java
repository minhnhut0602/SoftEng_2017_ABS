package abs.view.GUIComponents;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import abs.controller.UserController;
import abs.view.AppFrame;
import abs.view.style.AppStyle;

/**
 * The business information dialog.
 * 
 * Displays the active businesses name, description, phone number and address.
 *
 */
public class BusInfoDialog extends JDialog {

	private static final long serialVersionUID = -3772278382357317912L;

	public BusInfoDialog(AppFrame appFrame) {
		super(appFrame, true);

		this.setBackground(AppStyle.mainBackgroundColor);

		/* Content */
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));
		contentPane.setBorder(AppStyle.margin);
		contentPane.setBackground(AppStyle.mainForgroundColor);

		// Name
		JPanel namePanel = new JPanel();
		namePanel.setBackground(AppStyle.mainForgroundColor);
		namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JTextPane name = new JTextPane();
		name.setEditable(false);
		name.setText(UserController.getActiveBusiness().getName());
		namePanel.add(name);
		contentPane.add(namePanel);

		// Description
		JPanel descPanel = new JPanel();
		descPanel.setBackground(AppStyle.mainForgroundColor);
		descPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextPane desc = new JTextPane();
		desc.setEditable(false);
		desc.setText(UserController.getActiveBusiness().getDesc());
		descPanel.add(desc);
		contentPane.add(descPanel);

		// Phone number
		JPanel phonePanel = new JPanel();
		phonePanel.setBackground(AppStyle.mainForgroundColor);
		phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextPane phone = new JTextPane();
		JLabel phoneLabel = new JLabel("Phone Number: ");
		phone.setEditable(false);
		phone.setText("0" + String.valueOf(UserController.getActiveBusiness().getPhone()));
		phonePanel.add(phoneLabel);
		phonePanel.add(phone);
		contentPane.add(phonePanel);

		// Address
		JPanel addressPanel = new JPanel();
		addressPanel.setBackground(AppStyle.mainForgroundColor);
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextPane address = new JTextPane();
		JLabel addressLabel = new JLabel("Address: ");
		address.setEditable(false);
		address.setText(UserController.getActiveBusiness().getAddress());
		addressPanel.add(addressLabel);
		addressPanel.add(address);
		contentPane.add(addressPanel);

		JButton close = new JButton("Close");
		contentPane.add(close);

		pack();

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
