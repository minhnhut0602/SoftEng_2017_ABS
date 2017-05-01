/**
 * 
 */
package abs.view.GUIComponents;

import javax.swing.JButton;

import abs.model.Business;

/**
 *
 */
public class BsButton extends JButton {

	private static final long serialVersionUID = 4977825788220922261L;
	private Business business;

	public BsButton(String label, Business business) {
		super(label);
		this.setBusiness(business);
	}

	/**
	 * @return the business
	 */
	public Business getBusiness() {
		return business;
	}

	/**
	 * @param business
	 *            the business to set
	 */
	public void setBusiness(Business business) {
		this.business = business;
	}
}
