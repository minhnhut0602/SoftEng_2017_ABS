
package abs.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import abs.controller.Utilities;
import abs.model.Business;
import abs.model.User;

/**
 * Tests the Utilities class. Importing and exporting data to file.
 *
 */
public class FileTest {

	private Utilities utils;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		utils = new Utilities();
	}

	/**
	 * Test method for {@link abs.controller.Utilities#readBusinessData()}.
	 */
	@Test
	public void testReadBusinessData() {
		int result = utils.readBusinessData();
		assertTrue(result == 0);
	}

	/**
	 * Test method for {@link abs.controller.Utilities#readCustomerData()}.
	 */
	@Test
	public void testReadCustomerData() {
		int result = utils.readCustomerData();
		assertTrue(result == 0);
	}

	/**
	 * Test method for {@link abs.controller.Utilities#readData()}.
	 */
	@Test
	public void testReadData() {
		int result = utils.readData();
		assertTrue(result == 0);
	}

	/**
	 * Test method for
	 * {@link abs.controller.Utilities#writeData(java.lang.String)}.
	 */
	@Test
	public void testWriteData() {
		utils.readData();
		List<User> customers = utils.getCustomers();
		List<Business> businesses = utils.getBusiness();
		int result = utils.writeData(businesses, customers);
		//System.out.println(result);
		assertTrue(result == 0);
	}

}
