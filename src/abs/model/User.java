package abs.model;

/**
 * The user interface.
 * 
 * <p>
 * Is implemented by AbstractUser
 * </p>
 * 
 * @see AbstractUser
 *
 */
public interface User {

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail();

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword();

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString();
}