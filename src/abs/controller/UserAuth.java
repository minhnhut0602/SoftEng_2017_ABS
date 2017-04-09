package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Business;
import abs.model.Customer;
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

	/** The Utilities object. */
	@SuppressWarnings("unused")
	private Utilities utils;

	/** The list of registered customers. */
	private List<User> customers;

	/** The active user for the login session. */
	private User activeUser;

	/** The owners. */
	private List<User> owners;

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

	/* constructor that creates it's own utilities object if needed */
	// public UserAuth() {
	// util = new Utilities();
	// customers = new ArrayList<User>();
	// this.customers = util.getCustomers();
	// if (customers == null) {
	// customers = new ArrayList<User>();
	// }
	// }

	/**
	 * Auth user.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return true, if successful
	 * @throws CredentialsInvalidException
	 *             the password invalid exception
	 */
	public boolean authUser(String email, String password) throws CredentialsInvalidException {

		/* check if the list is empty */

		if (this.customers == null) {
			return false;
		}

		/* step through the customer list */
		for (int i = 0; i < customers.size(); i++) {

			/* check the email is the same */
			if (this.customers.get(i).getEmail().equals(email)) {

				/* check the password is the same */
				if (this.customers.get(i).getPassword().equals(password)) {

					/* set this user as the active user */
					this.activeUser = this.customers.get(i);

					/* end method */
					return true;
				}
			}
		}

		/* went through the whole list and didn't find the user */
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
	 * @throws RegistrationValidationException
	 *             the registration validation exception
	 * @throws RegistrationNonUniqueException
	 *             the registration non unique exception
	 */
	public boolean registerUser(String name, String email, String password, String address, String phone)
			throws RegistrationValidationException, RegistrationNonUniqueException {

		/* VALIDATE NAME */
		if (name == null || name == "") {
			throw new RegistrationValidationException("Name", name);
			/* ERROR: name is empty */

		}

		/* VALIDATE EMAIL AND PASSWORD */
		if (email == null || email == "") {
			throw new RegistrationValidationException("Email", email);
		}

		if (password == null || password == "") {
			throw new RegistrationValidationException("Email", email);
		}

		/* VALIDATE ADDRESS */
		if (address == null || address == "") {
			throw new RegistrationValidationException("Address", address);
		}

		/* VALIDATE PHONE */
		if (phone == null || phone == "") {
			throw new RegistrationValidationException("Phone", phone);
		}

		/*
		 * check to see if the email exists, meaning the customer must already
		 * be registered
		 */
		if (customers != null) {
			for (int i = 0; i < this.customers.size(); i++) {
				if (this.customers.get(i).getEmail().compareTo(email) == 0) {
					throw new RegistrationNonUniqueException(email);
				}
			}
		}
		/* REGISTER NEW USER */
		Customer customer = new Customer(name, email, address, phone, password);
		this.customers.add(customer);
		this.setActiveUser(customer);
		return true;

	}

	/**
	 * Validate an email.
	 *
	 * @param email
	 *            the email
	 * @return true, if successful
	 */
	public boolean validateEmail(String email) {

		/* VALIDATE EMAIL */
		int counter, emailLength;

		if (email.length() == 0 /* || email.matches(".*[ \\(),:;<>[]\"].*") */) {
			return false;
			/* ERROR: password or email is empty */

		} else {

			emailLength = email.length();
			int counterAt = 0;
			int counterPeriod = 0;

			/*
			 * loop through individual characters in the string 'email' and
			 * compares to '@' and '.com', there should only be one of each
			 * example in the string of each, and they must be in order '@'
			 * followed by '.'
			 */
			for (counter = 0; counter <= emailLength - 1; counter++) {

				if (counterAt > 1 || counterPeriod > 1) {
					return false;
					/* ERROR: too many '@' or '.com' */

				} else {

					if (email.charAt(counter) == '@') {
						counterAt += 1;
					}

					if (email.toLowerCase().contains(".com") && counterAt < 1) {
						return false;
						/* ERROR: '.com' found before '@' */

					} else if (email.toLowerCase().contains(".com")) {
						counterPeriod += 1;

					}
					if (counterPeriod > 1) {
						return false;
						/* ERROR: more than one '.com' found */

					}
				}
			}
		}

		return false;
	}

	/**
	 * Validate a phone number.
	 *
	 * @param phone
	 *            the phone
	 * @return true, if successful
	 */
	public boolean validatePhone(String phone) {
		if (phone.replaceAll(" ", "").matches("[0-9]+")) {
			if (phone.length() == 10 || phone.length() == 8) {
				/* Do nothing */
			} else {
				return false;
				/* ERROR: phone number incorrect length */
			}

		} else {
			return false;
			/* ERROR: phone number has illegal characters */

		}

		return true;
	}

	/**
	 * Validate an address.
	 *
	 * @param address
	 *            the address
	 * @return true, if successful
	 */
	public boolean validateAddress(String address) {

		ArrayList<String> addressToks = new ArrayList<String>();
		StringTokenizer addressTokeniser = new StringTokenizer(address);
		int index = 0;

		/* tokenises adress */
		while (addressTokeniser.hasMoreTokens()) {
			addressToks.add(index, addressTokeniser.nextToken());
			index++;
		}

		/* checks the first entry for a number */
		int currentFocus = 0;
		if (addressToks.get(currentFocus).contains("[0-9]+")) {
			currentFocus++;
		}

		/*
		 * loops through all tokens checking for illegal characters, stops on
		 * last two
		 */
		while (currentFocus < addressToks.size() - 3) {
			if (addressToks.get(currentFocus).contains("[a-zA-Z,]+")) {
				currentFocus++;
			} else {
				return false;
				/* ERROR: line in address is not in correct format */
			}

		}

		/* checks last two tokens for postcode and state */
		while (currentFocus < addressToks.size() - 1) {

			if (addressToks.get(currentFocus).matches("[0-9]+")) {
				if (addressToks.get(currentFocus).length() == 4) {
					currentFocus++;
				} else {
					return false;
					/* ERROR: Post code not 4 digits */

				}
			}
			if (addressToks.get(currentFocus).matches("[a-zA-Z]+")) {
				if (addressToks.get(currentFocus).toUpperCase().matches("VIC|NSW|ACT|WA|SA|TAS")) {
					currentFocus++;
				} else {
					return false;
					/* ERROR: invalid state provided */

				}
			}

		} /* While loop end */

		return true;
	}

	/**
	 * Gets the active user/logged in user.
	 * 
	 * Null if there is none logged in.
	 *
	 * @return the active user
	 */
	public User getActiveUser() {
		return activeUser;
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
