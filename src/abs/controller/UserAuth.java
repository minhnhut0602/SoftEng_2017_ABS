package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import abs.exceptions.PasswordInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Customer;
import abs.model.User;

public class UserAuth {

	/* Utilities object */
	private Utilities util;

	/* list of registered customer */
	private List<User> customers;

	/* active user for the login session */
	private User activeUser;

	/* Constructor that takes a utilities object */
	public UserAuth(Utilities utility) {
		this.util = utility;
		this.customers = util.getCustomers();
	}

	/* constructor that creates it's own utilities object if needed */
	public UserAuth() {
		util = new Utilities();
		customers = new ArrayList<User>();
		this.customers = util.getCustomers();
		if (customers == null) {
			customers = new ArrayList<User>();
		}
	}

	public boolean authUser(String email, String password) throws PasswordInvalidException {

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
		throw new PasswordInvalidException();

	}

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

	/* email validation */
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

	/* validate the phone number */
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

	/* validate address */
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

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}
