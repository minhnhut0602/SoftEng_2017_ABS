package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Employee;
import abs.model.Owner;
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
	private Utilities utils;

	/** The active user for the login session. */
	private User activeUser;

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
		List<User> customers = utils.getCustomers();
		List<User> owners = utils.getOwners();

		/* check if the list is empty */
		if (customers.isEmpty() && owners.isEmpty()) {
			return false;
		} else if (!(customers.isEmpty())) {
			/* step through the customer list */
			for (int i = 0; i < customers.size(); i++) {

				/* check the email is the same */
				if (customers.get(i).getEmail().equals(email)) {

					/* check the password is the same */
					if (customers.get(i).getPassword().equals(password)) {

						/* set this user as the active user */
						this.activeUser = customers.get(i);

						/* end method */
						return true;
					}
				}
			}
		}
		// Both aren't empty, wasn't found in customer so must be a owner or
		// invalid

		/* step through the owner list */
		for (int i = 0; i < owners.size(); i++) {

			/* check the email is the same */
			if (owners.get(i).getEmail().equals(email)) {

				/* check the password is the same */
				if (owners.get(i).getPassword().equals(password)) {

					/* set this user as the active user */
					this.activeUser = owners.get(i);

					/* end method */
					return true;
				}
			}
		}

		/* went through both whole lists and didn't find the user */
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

		List<User> customers = utils.getCustomers();
		// List<User> owners = utils.getOwners();

		/* VALIDATE NAME */
		if (name == null || name == "") {
			throw new RegistrationValidationException("Name", name);
			/* ERROR: name is empty */

		}

		/* VALIDATE EMAIL AND PASSWORD */
		if (email == null || email == "" || validateEmail(email) == false) {
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
			for (int i = 0; i < customers.size(); i++) {
				if (customers.get(i).getEmail().compareTo(email) == 0) {
					throw new RegistrationNonUniqueException(email);
				}
			}
		}
		/* REGISTER NEW USER */
		Customer customer = new Customer(name, email, address, phone, password);
		utils.addCustomers(customer);
		this.setActiveUser(customer);

		return true;

	}

	/**
	 * Registering an Employee
	 * 
	 * @param name
	 *            is the name of the employee
	 * 
	 * @param business
	 *            is the business it is linked
	 * 
	 */
	public boolean registerEmployee(String name, Business business) {
		/** no need to validate name */

		/** validate if the business exists */
		for (Business b : utils.getBusiness()) {
			if (b.equals(business)) {
				/** then register the Employee */
				business.addStaff(new Employee(name));
				return true;
			}
		}

		return false;

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

		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Boolean emailValid = email.matches(regex);
		System.out.println("is e-mail: " + email + " :Valid = " + emailValid);

		return emailValid;

	}

	/**
	 * Validate a phone number.
	 *
	 * @param phone
	 *            the phone
	 * @return true, if successful
	 */

	public boolean validatePhone(String phone) {

		/* VALIDATE PHONE */

		String regex = "[0-9]+";
		phone = phone.replaceAll(" ", "");

		/* check is either 8 or 10 digit */
		if (phone.length() == 8 || phone.length() == 10) {

			/* check is numeric */
			if (phone.matches(regex)) {
				System.out.println("phone: " + phone + " :Valid = true");
				return true;

			} else {
				System.out.println("phone(numeric): " + phone + " :Valid = false");
				return false;
			}

		} else {
			System.out.println("phone(8/10): " + phone + " :Valid = false");
			return false;
		}

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
		if (index < 6) {
			System.out.println("address too short: false");
			return false;
		}
		// for(int counter = 0; counter < addressToks.size(); counter++){
		// System.out.println(addressToks.get(counter));
		// }

		/* checks the first entry for a number */
		int currentFocus = 0;
		if (addressToks.get(currentFocus).matches("[0-9]+")) {
			currentFocus++;
		}

		/*
		 * loops through all tokens checking for illegal characters, stops on
		 * last two
		 */
		while (currentFocus < addressToks.size() - 2) {
			if (addressToks.get(currentFocus).matches("[a-zA-Z,]+")) {
				currentFocus++;
			} else {
				System.out.println("address invalid characters: false");
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
					System.out.println("address postcode invalid: false");
					return false;
					/* ERROR: Post code not 4 digits */

				}
			}
			if (addressToks.get(currentFocus).matches("[a-zA-Z]+")) {

				String stateArray[] = new String[] { "VIC", "NSW", "ACT", "WA", "SA", "TAS" };

				for (int counter = 0; counter < stateArray.length; counter++) {

					if (addressToks.get(currentFocus).toUpperCase().equals(stateArray[counter]) == true) {
						currentFocus++;
						return true;
					}
				}
				System.out.println("address invalid state: false");
				return false;
				/* ERROR: invalid state provided */

			}
		}
		System.out.println("address is valid: true");
		return true;
	} /* While loop end */

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

	public boolean registerOwner(String name, String email, String password)
			throws RegistrationValidationException, RegistrationNonUniqueException {

		List<User> owners = utils.getOwners();

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

		/*
		 * check to see if the email exists, meaning the customer must already
		 * be registered
		 */
		if (owners != null) {
			for (int i = 0; i < owners.size(); i++) {
				if (owners.get(i).getEmail().compareTo(email) == 0) {
					throw new RegistrationNonUniqueException(email);
				}
			}
		}
		/* REGISTER NEW USER */
		Owner owner = new Owner(name, email, password);
		utils.addOwner(owner);
		this.setActiveUser(owner);

		return true;

	}
}
