package abs.model;
// Owner Class (implements User)

import java.util.List;

public class Owner extends AbstractUser {

	/** The owners business. */
	private Business business;

	/**
	 * @param name
	 * @param email
	 * @param password
	 */
	
	//construct an owner type object
	public Owner(String name, String email, String password, List<Booking> bookings) {
		super(name, email, password, bookings);
	}

	@Override
	public String toString() {
		return "Owner [name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword()
				+ "]";
	}
	
	//returns Business
	public Business getBusiness() {
		return business;
	}
	
	//set the Business
	public void setBusiness(Business business) {
		this.business = business;
	}

}