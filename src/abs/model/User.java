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

	public String getEmail();

	public String getName();

	public String getPassword();

	@Override
	public String toString();
}