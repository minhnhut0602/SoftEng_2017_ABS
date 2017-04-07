package abs.view;
//Menu class
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import abs.controller.UserAuth;
import abs.controller.Utilities;
import abs.exceptions.MenuInputException;
import abs.model.Business;
import abs.model.Customer;
import abs.model.Owner;
import abs.model.User;

public class Menu {
	private Utilities utils;
	private int dataRead;
	private List<Business> dataBus;
	private Owner ownerData;
	private List<User> customerData;
	private UserAuth auth;
	private User m_User;
	private String loginUserName = "";
	
	public Menu(){
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
		
		auth = new UserAuth();
	}
	
	public int mainMenu(){
		String Output = "Welcome to the Appointment Booking System\n" + "Please select an option:\n"
				+ "1. Login\n" + "2. Register\n";
		System.out.print(Output);
		
		int ch = 0;
		String inputData = "";
		InputStream inStream = System.in;
		try{
			
			if ((ch = inStream.read()) != -1){
				if (ch >=66 && ch <=67 ){
					System.out.print("Wrong option/n");
					return 0;
				}
			}
		} catch(IOException io){
			System.out.print("Wrong option/n");
			return 0;
		}
		return ch;
	}
	
	public User loginMenu(){
		String Output = "Welcome to the Appointment Booking System\n"
				+ "Please enter your email and password seperated by a comma/n" + "e.g. email,password/n";
		System.out.print(Output);
		
		User customers;
		String[] arg = {""};
		InputStream inStream = System.in;
		int ch;
		String inputData = "";
		try{
			
			while((ch = inStream.read()) != -1){
				inputData += (char)ch;
			}
			arg = inputData.split(",");
			if (arg.length != 2) {
				System.out.print("Usage: email,password/n");
				return null;
			}
		} catch(IOException io){
			System.out.print("Usage: email,password/n");
			return null;
		}
		
		customers = new Customer("", arg[0], "", "", arg[1]);
		loginUserName = arg[0];
		
		return customers;
	}
	
	public User registerMenu(){
		
		String Output = "Welcome to the Appointment Booking System\n"
				+ "To Register Please enter your details seperated by a comma/n"
				+ "e.g. name,email,address,phone,password/n";
		System.out.print(Output);
		
		User customers;
		String[] arg = {""};
		InputStream inStream = System.in;
		int ch;
		String inputData = "";
		try{
			while((ch = inStream.read()) != -1){
				inputData += (char)ch;
			}
			arg = inputData.split(",");
			if (arg.length != 5) {
				System.out.print("Usage: name,email,address,phone,password/n ");
				return null;
			}
			
		} catch(IOException io){
			System.out.print("Usage: name,email,address,phone,password/n ");
			return null;
		}
		
		customers = new Customer(arg[0], arg[1], arg[2], arg[3], arg[4]);
		return customers;
	}
	
	public int customerDashboard(){
		String Output = "Welcome " + loginUserName + " to the Appointment Booking System\n"
				+ "Please select an option:\n" + "1. View avaliable bookings\n" + "2. View my bookings\n"
				+ "3. Logout\n" + "4. Exit\n";
		System.out.print(Output);
		
		int ch = 0;
		InputStream inStream = System.in;
		try{
			if((ch = inStream.read()) != -1){
				if (ch >=66 && ch <=69 ){
					System.out.print("Wrong option/n");
					return 0;
				}
			}
		} catch(IOException io){
			System.out.print("Wrong option/n");
			return 0;
		}
		return ch;
	}
	
	public int businessSelect(){
		String Output = "Welcome to the Appointment Booking System\n" + "Please select an option:\n"
				+ "1. Daniel's Dentists\n";
		System.out.print(Output);
		
		int ch = 0;
		InputStream inStream = System.in;
		try{
			if((ch = inStream.read()) != -1){
				if (ch >=66 && ch <=66 ){
					System.out.print("Wrong option/n");
					return 0;
				}
			}
		} catch(IOException io){
			System.out.print("Wrong option/n");
			return 0;
		}
		return ch;
	}
}
