/**
 * 
 */
package abs.model;

/**
 * @author Daniel Caddaye
 *
 */
public class Customer extends AbstractUser {

	private String address;
	private int phone;

	/**
	 * 
	 */

	public Customer(String name, String email, String address, int phone, String password) {
		super(name, email, address);
		this.address = address;
		this.phone = phone;
	}

}
