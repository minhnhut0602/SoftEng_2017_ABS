package abs.exceptions;

/**
 * The password invalid exception is thrown when a user tried to login with a
 * valid email but invalid password.
 *
 */
public class CredentialsInvalidException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4L;

	/**
	 * Instantiates a new password invalid exception with default message.
	 */
	public CredentialsInvalidException() {
		super("Im sorry but the password you entered is invalid. Please try again.");
	}

}
