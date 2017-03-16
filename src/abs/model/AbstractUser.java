package abs.model;

/**
 * @author Daniel Caddaye
 *
 */
public abstract class AbstractUser implements User {

	private String email;
	private String name;
	private String password; // Hashed??

	public AbstractUser(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
