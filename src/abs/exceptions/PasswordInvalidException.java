/**
 * 
 */
package abs.exceptions;

/**
 * The password invalid exception is thrown when a user tried to login with a
 * valid email but invalid password.
 *
 */
public class PasswordInvalidException extends Exception {

	private static final long serialVersionUID = 4L;

	public PasswordInvalidException() {
		super("Im sorry but the password you entered is invalid. Please try again.");
	}

}
