package abs.controller;

/**
 * The Registry class maintains static references to single objects for UserAuth
 * and Utilities.
 * 
 * @version 1.0
 * 
 * @see UserAuth
 * @see Utilities
 */
public class Registry {

	/** The Utilities object. */
	private static Utilities utils;

	/** The UserAuth object. */
	private static UserAuth userAuth;

	/**
	 * Instantiates a new registry.
	 * 
	 * Creates the static objects
	 */
	private Registry() {
		utils = new Utilities();
		userAuth = new UserAuth(utils);
	}

	/**
	 * Gets the Utilities object.
	 *
	 * @return the Utilities object
	 */
	public static Utilities getUtils() {
		if (utils == null) {
			new Registry();
		}
		return utils;
	}

	/**
	 * Gets the UserAuth object.
	 *
	 * @return the UserAuth object.
	 */
	public static UserAuth getUserAuth() {
		if (userAuth == null) {
			new Registry();
		}
		return userAuth;
	}
}
