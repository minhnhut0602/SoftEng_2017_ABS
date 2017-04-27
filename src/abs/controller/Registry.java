/**
 * 
 */
package abs.controller;

/**
 *
 */
public class Registry {

	private static Utilities utils;
	private static UserAuth userAuth;

	/**
	 * 
	 */
	private Registry() {
		utils = new Utilities();
		userAuth = new UserAuth(utils);
	}

	public static Utilities getUtils() {
		if (utils == null) {
			new Registry();
		}
		return utils;
	}

	public static UserAuth getUserAuth() {
		if (userAuth == null) {
			new Registry();
		}
		return userAuth;
	}
}
