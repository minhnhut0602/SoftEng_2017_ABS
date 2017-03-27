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
	private int phone;

	/**
	 * 
	 */

	public Customer(String name, String email, String address, int phone, String password) {
		super(name, email, password);
		this.address = address;
		this.phone = phone;
	}

	@Override
	public String toString() {

		return super.toString() + "Customer [address=" + address + ", phone=" + phone + "]";
	}

}
