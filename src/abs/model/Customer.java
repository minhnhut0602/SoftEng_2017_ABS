/**
 * 
 */
package abs.model;

/**
 * 
 *
 */
public class Customer extends AbstractUser {

	private String address;
	private double phone;

	/**
	 * 
	 */

	public Customer(String name, String email, String address, double phone, String password) {
		super(name, email, address);
		this.address = address;
		this.phone = phone;
	}

}
