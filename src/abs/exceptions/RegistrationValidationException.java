package abs.exceptions;

/**
 * RegistrationValidationException throws when the user enters an invalid input
 * on the registration form.
 *
 * @since Alpha
 * @version 1.0
 */
public class RegistrationValidationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3L;

	/** The field that was invalid */
	private String field;

	/** The value entered in the field. */
	private String value;

	/**
	 * Instantiates a new registration validation exception.F
	 *
	 * @param field
	 *            The invalid field
	 * @param value
	 *            The value entered
	 */
	public RegistrationValidationException(String field, String value) {
		super("The input of: " + value + " for: " + field + " is invalid.");
		this.field = field;
		this.value = value;
	}

	/**
	 * Gets the field.
	 *
	 * @return field The offending field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Gets the value.
	 *
	 * @return value the value entered
	 */
	public String getValue() {
		return value;
	}

}
