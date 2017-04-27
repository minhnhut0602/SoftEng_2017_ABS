package abs.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.model.Business;


public class EmployeeTest {

	/** variables for testing */
	private Utilities utils;
	private UserAuth auth;
	
	
	@Before
	public void setUp(){
		utils = new Utilities();
		auth = new UserAuth(utils);
		
	}
	
	@Test
	public void registerNoBusinessMatch(){
		String name = "freddy";
		Business b = new Business();
		assertFalse(auth.registerEmployee(name, b));
	}
	
	@Test
	public void registerGoodBusiness(){
		String name = "Delilah";
		Business b = utils.getBusiness().get(0);
		assertTrue(auth.registerEmployee(name, b));
	}
}
