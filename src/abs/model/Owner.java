package abs.model;
// Owner Class (implements User)

public class Owner extends AbstractUser {
	
	private Business business;

	public Owner(String name, String email, String password) {
		super(name, email, password);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Owner [name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + "]";
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

}