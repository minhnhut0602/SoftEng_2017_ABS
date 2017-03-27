/**
 * 
 */
package abs.exceptions;

/**
 * MenuInputException is thrown when an inputed value is invalid. This value can
 * be of type int or String.
 * 
 * This exception stores the users inputed value.
 *
 */
public class MenuInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int input;

	private String inputS;

	/**
	 * @param input
	 *            The users input on the menu. An int.
	 */
	public MenuInputException(int input) {
		super("The value inputed is invalid, must be a whole number.");
		this.input = input;
	}

	/**
	 * @param inputs
	 *            The users input on the menu. An String.
	 */
	public MenuInputException(String inputS) {
		super("The inputed option is invlaid.");
		this.inputS = inputS;

	}

	/**
	 * @return input The int value the user entered.
	 */
	public int getInput() {
		return input;
	}

	/**
	 * @return inputS The string the user entered.
	 */
	public String getInputS() {
		return inputS;
	}
}
