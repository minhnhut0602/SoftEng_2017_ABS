package abs.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is a test suite to run all the test cases.
 * 
 * <p>
 * <i>Note please add here any additional tests created.</i>
 * </p>
 *
 * <p>
 * <i>Menu Test is excluded as deprecated.</i>
 * </p>
 */
@RunWith(Suite.class)
@SuiteClasses({ FileTest.class, LoginTest.class, BookingTest.class })
public class AllTests {

}