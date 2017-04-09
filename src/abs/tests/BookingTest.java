package abs.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.exceptions.CredentialsInvalidException;
import abs.exceptions.RegistrationNonUniqueException;
import abs.exceptions.RegistrationValidationException;
import abs.model.Booking;
import abs.model.Customer;
import abs.model.User;

/**
 * Tests the functions of the Bookings class
 * 
 * <p>
 * Tests: viewing all bookings, view customers bookings, cancel a booking, make
 * a booking.
 * <p>
 * 
 * @see abs.model.Booking Booking
 *
 */
public class BookingTest {

	@Test
	public void makeBooking() {

		Utilities utils = new Utilities();
		UserAuth auth = new UserAuth(utils);

		try {
			auth.registerUser("Test", "valid@validemail.com", "validpassword", "Test123", "1231231");
		} catch (RegistrationValidationException | RegistrationNonUniqueException e1) {
			fail("Register fail");
		}

		try {
			auth.authUser("valid@validemail.com", "validpassword");
		} catch (CredentialsInvalidException e) {
			fail("Invalid credentials");
		}
		User customer = auth.getActiveUser();
		Booking booking = utils.getBusiness().get(0).getAvBookings().get(0);
		Boolean test1 = ((Customer) customer).addBooking(booking);

		// tests if you can take a valid booking.
		assertTrue(test1);

		// checks if the booking is in customer array
		assertTrue(((Customer) customer).getBookings().contains(booking));

		// Checks if that booking is marked as taken
		// assertFalse(utils.getBusiness().get(0).getAvBookings().contains(booking));

		// Checks if it stops you adding an existing or taken booking
		assertFalse(((Customer) customer).addBooking(booking));

	}

	@Test
	public void cancelBooking() {
		Utilities utils = new Utilities();
		UserAuth auth = new UserAuth(utils);

		try {
			auth.registerUser("Test", "valid@validemail.com", "validpassword", "Test123", "1231231");
		} catch (RegistrationValidationException | RegistrationNonUniqueException e1) {
			fail("Register fail");
		}

		try {
			auth.authUser("valid@validemail.com", "validpassword");
		} catch (CredentialsInvalidException e) {
			fail("Invalid credentials");
		}
		User customer = auth.getActiveUser();
		Booking booking = utils.getBusiness().get(0).getAvBookings().get(0);
		Booking booking2 = utils.getBusiness().get(0).getAvBookings().get(1);
		@SuppressWarnings("unused")
		Boolean add = ((Customer) customer).addBooking(booking);
		Boolean test1 = ((Customer) customer).cancelBooking(booking);

		// cancel an existing booking
		assertTrue(test1);

		// checks if the booking is removed
		assertFalse(((Customer) customer).getBookings().contains(booking));

		// cancel a non existing booking
		assertFalse(((Customer) customer).cancelBooking(booking2));
	}
}
