package abs.exceptions;

/**
 * The credentials invalid exception is thrown when a user tried to login with
 * an invalid email or invalid password.
 *
 */
public class CredentialsInvalidException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4L;

	/**
	 * Instantiates a new password invalid exception with default message.
	 */
	public CredentialsInvalidException() {
		super("Im sorry but the credentials you entered is invalid. Please try again.");
	}

}
