package abs.controller;

import java.util.List;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Business;
import abs.model.User;

/**
 * The UserAuth class.
 * 
 * <p>
 * This class validates and authenticates login and register requests.stores the
 * active user.
 * </p>
 * 
 * @see #getActiveUser()
 * @see #authUser(String, String)
 * @see #registerUser(String, String, String, String, String)
 */
public class UserAuth {

	/** The active/logged in user. */
	private User activeUser;
	private Utilities utils;
	private List<User> owners;
	private List<User> customers;

	/**
	 * Instantiates a new user auth.
	 * 
	 * <p>
	 * Initializes the customer and owner list.
	 * </p>
	 * 
	 * @param utils
	 *            the utils the utilities object
	 */
	public UserAuth(Utilities utils) {
		this.activeUser = null;
		this.utils = utils;
		this.customers = utils.getCustomers();

		for (Business business : utils.getBusiness()) {
			owners.add(business.getOwner());
		}
	}

	/**
	 * Auth user.
	 *
	 * @param email
	 *            the email
	 * @param Password
	 *            the password
	 * @return true, if successful
	 * @throws CredentialsInvalidException
	 *             the password invalid exception
	 */
	public boolean authUser(String email, String Password) throws CredentialsInvalidException {
		throw new CredentialsInvalidException();

	}

	/**
	 * Register user.
	 *
	 * @param name
	 *            the name
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @param address
	 *            the address
	 * @param phone
	 *            the phone
	 * @return true, if successful
	 * @throws RegistrationNonUniqueException
	 *             the registration non unique exception
	 * @throws RegistrationValidationException
	 *             the registration validation exception
	 */
	public boolean registerUser(String name, String email, String password, String address, String phone)
			throws RegistrationNonUniqueException, RegistrationValidationException {
		throw new RegistrationValidationException("Email", email);

	}

	/**
	 * Gets the active user/logged in user.
	 * 
	 * Null if there is none logged in.
	 *
	 * @return the active user
	 */
	public User getActiveUser() {

		return this.activeUser;
	}

	/**
	 * Sets the active user.
	 *
	 * @param activeUser
	 *            the new active user
	 */
	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;

	}

}
