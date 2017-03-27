/**
 * 
 */
package abs.exceptions;

/**
 * RegistrationNonUniqueException is thrown when the user tried to register with
 * an existing email address.
 * 
 * This exception stores the email.
 *
 */
public class RegistrationNonUniqueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private String email;

	/**
	 * @param emailIn
	 *            The email inputed by the user.
	 */
	public RegistrationNonUniqueException(String emailIn) {
		super("The email address entered is already registered. Login or try again.");
		this.email = emailIn;
	}

	/**
	 * @return the email The email entered by the user.
	 */
	public String getEmail() {
		return email;
	}

}
