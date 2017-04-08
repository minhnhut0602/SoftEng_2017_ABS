package abs.exceptions;

/**
 * MenuInputException is thrown when an inputed value is invalid. This value can
 * be of type int or String.
 * 
 * This exception stores the users inputed value.
 *
 */
public class MenuInputException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The int input from calling class. */
	private int input;

	/** The String input from calling class. */
	private String inputS;

	/**
	 * f Instantiates a new menu input exception.
	 *
	 * @param input
	 *            The users input on the menu. An int.
	 */
	public MenuInputException(int input) {
		super("The value inputed is invalid, must be a whole number.");
		this.input = input;
	}

	/**
	 * Instantiates a new menu input exception.
	 *
	 * @param inputS
	 *            tThe users input on the menu. A String.
	 */
	public MenuInputException(String inputS) {
		super("The inputed option is invlaid.");
		this.inputS = inputS;

	}

	/**
	 * Gets the input.
	 *
	 * @return input The int value the user entered.
	 */
	public int getInput() {
		return input;
	}

	/**
	 * Gets the input String.
	 *
	 * @return inputS The string the user entered.
	 */
	public String getInputS() {
		return inputS;
	}
}
