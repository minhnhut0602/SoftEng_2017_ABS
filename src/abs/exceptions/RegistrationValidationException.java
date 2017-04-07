/**
 * 
 */
package abs.exceptions;

/**
 * RegistrationValidationException throws when the user enters an invalid input
 * on the registration form.
 *
 */
public class RegistrationValidationException extends Exception {

	private static final long serialVersionUID = 3L;
	private String field;
	private String value;

	/**
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
	 * @return field The offending field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return value the value entered
	 */
	public String getValue() {
		return value;
	}

}
