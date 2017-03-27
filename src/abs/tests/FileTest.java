
package abs.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import abs.controller.Utilities;

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
		String type = null;
		int result = utils.writeData(type);
		assertTrue(result == 0);
	}

}
