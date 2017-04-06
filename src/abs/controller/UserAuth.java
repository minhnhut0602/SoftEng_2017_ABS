package abs.controller;

import abs.exceptions.PasswordInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.User;

public class UserAuth {
	private User activeUser;

	public void UserAuth() {
		this.activeUser = null;
	}

	/**
	 * Auth user.
	 *
	 * @param email
	 *            the email
	 * @param Password
	 *            the password
	 * @return true, if successful
	 * @throws PasswordInvalidException
	 */
	public boolean authUser(String email, String Password) throws PasswordInvalidException {
		throw new PasswordInvalidException();

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

	public User getActiveUser() {

		return this.activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;

	}

}
