package abs.controller;

import static org.junit.Assert.fail;

import java.util.List;

import abs.model.Business;
import abs.model.Customer;
import abs.model.Owner;
import abs.model.User;

public class UserAuth {

	private Utilities utils;
	private int mUserCount;
	private int dataRead;
	private List<Business> dataBus;
	private Owner ownerData;
	private List<User> customerData;
	private UserAuth auth;
	private User m_User;
	private String loginUserName = "";
	
	public UserAuth(){
		utils = new Utilities();// look for default data
		dataRead = utils.readData(); // import data

		if (dataRead != 0) { // checks for read error
			fail("Data import error code: " + dataRead);
		}

		// Stores business data from file to compare with menu output
		dataBus = utils.getBusiness();

		// Owner data for login tests
		ownerData = dataBus.get(0).getOwner();

		// Customer data for login test
		customerData = utils.getCustomers();
	}

	public boolean authUser(String email, String Password) {
		
		List<User> mcustomers=utils.getCustomers();
		mUserCount=mcustomers.size();
		boolean validUser = false;
		String errorMessage = "";
		if (email == "") 
		{
			//System.out.println("The email is empty.");
			return false;
		}
		for (int index = 0; index < mUserCount; index++)
		{
			User mCustomer= mcustomers.get(index);
			
			if(mCustomer.getEmail() == email)
			{
				if (mCustomer == null) continue;
				if(mCustomer.getPassword() != Password)
				{
					errorMessage = "Invalid Password.";
					System.out.println(errorMessage);
					return false;
				}
				return true;
			}
		}
		
		System.out.println("Invalid Email.");
		return false;
	}

	public boolean registerUser(String name, String email, String password, String address, String phone) 
	
	{
		List<User> mcustomers=utils.getCustomers();
		mUserCount=mcustomers.size();
		if (email == null || email == "") 
		{
			System.out.println("The email is empty.");
			return false;
		}
		if (password == null || password == "") 
		{
			System.out.println("The password is empty.");
			return false;
		}
		if (name == null || name=="") 
		{
			System.out.println("The name is empty.");
			return false;
		}
		Boolean isEmailUnique=true;
		for (int index = 0; index < mUserCount; index++)
		{
			User mCustomer= mcustomers.get(index);
			
			if(mCustomer.getEmail() == email)
			{
				isEmailUnique = false;
				break;
			}
		}
		
		if(isEmailUnique == true)
		{
			Customer mCustomer= new Customer(name, email, address, phone, password);
			utils.addCustomers(mCustomer);
			return true;
		}
		return false;

	}
}
