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

	private String phone;

	/**
	 * 
	 */

	public Customer(String name, String email, String address, String phone, String password) {
		super(name, email, password);
		this.address = address;
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {

		return super.toString() + "Customer [address=" + address + ", phone=" + phone + "]";
	}

}
