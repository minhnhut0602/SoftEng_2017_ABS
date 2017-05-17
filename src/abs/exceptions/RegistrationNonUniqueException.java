package abs.exceptions;

/**
 * RegistrationNonUniqueException is thrown when the user tried to register with
 * an existing email address.
 * 
 * This exception stores the email.
 * 
 * @since Alpha
 * @version 1.0
 */
public class RegistrationNonUniqueException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;

	/** The email entered by the user. */
	private String email;

	/**
	 * Instantiates a new registration non unique exception with default
	 * message.
	 *
	 * <p>
	 * <i>The email address entered is already registered. Login or try
	 * again.</i>
	 * </p>
	 *
	 * @param emailIn
	 *            The email inputed by the user.
	 */
	public RegistrationNonUniqueException(String emailIn) {
		super("The email address entered is already registered. Login or try again.");
		this.email = emailIn;
	}

	/**
	 * Gets the email entered.
	 *
	 * @return the email The email entered by the user.
	 */
	public String getEmail() {
		return email;
	}

}
