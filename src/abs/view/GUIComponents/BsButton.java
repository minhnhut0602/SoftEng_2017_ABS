/**
 * 
 */
package abs.view.GUIComponents;

import javax.swing.JButton;

import abs.model.Business;

/**
 * The Business Button.
 * 
 * @version 1.0
 * @since 1.0
 * @see abs.model.Business Business
 * @see javax.swing.JButton JButton
 */
public class BsButton extends JButton {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4977825788220922261L;

	/** The business for this button. */
	private Business business;

	/**
	 * Instantiates a new bs button.
	 *
	 * @param label
	 *            the label
	 * @param business
	 *            the business
	 */
	public BsButton(String label, Business business) {
		super(label);
		this.setBusiness(business);
	}

	/**
	 * Gets the business.
	 *
	 * @return the business
	 */
	public Business getBusiness() {
		return business;
	}

	/**
	 * Sets the business.
	 *
	 * @param business
	 *            the new business
	 */
	public void setBusiness(Business business) {
		this.business = business;
	}
}
