package abs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import abs.model.User;

public class UserAuth {
	
	Utilities util = new Utilities();
	
	List<User> customers = util.getCustomers();
	
	/*  */
	
	public boolean authUser(String email, String Password) {
		
		/* Import customer list */
		//this.customers = getCustomers();
		

		
		/* Validate Email*/
		int counter, emailLength;
		
		/* checks if either the email or password was left empty, exit if empty */
		if (email.length() == 0 || Password.length() == 0 || email.matches(".*[ \\(),:;<>[]\"].*")){
			return false;
		}else{
			
			emailLength = email.length();
			int counterAt = 0;
			int counterPeriod = 0;
			
			/* loop through individual characters in the string 'email' and compares to '@' and '.com', there should only be one of each example in the string of each, and they must be in order '@' followed by '.'*/
			for(counter = 0; counter <= emailLength-1; counter++){
				
				/* if there are more than one '@' or '.', exit fail */
				if(counterAt > 1 || counterPeriod > 1){
					return false;
				}else{
					
					/* if there is a @ found, +1 to counterAt */
					if(email.charAt(counter) == '@'){
						counterAt += 1;
					}
					/* if there doesnt exist an '@', but a '.com' has been found, exit fail */
					if(email.toLowerCase().contains(".com") && counterAt < 1){
						return false;
						
					/* check again if there is an '.com', +1 to counterPeriod */
					}else if(email.toLowerCase().contains(".com")){
						counterPeriod += 1;

					}
					if(counterPeriod > 1){
						return false;
					}
				}
				

				
			}
			
			
		}
		
		/* no validation for passwords, anything at this point that is not an empty string is accepted */
		
		
		/* ***NEEDS TO BE LOOKED AT*** this will check with current email accounts to ensure there are no already existing emails accounts by the same name */
		if(email.equals(customers.get(0).getEmail())){
			return false;
		}
		

	}

	public boolean registerUser(String name, String email, String password, String address, String phone) {
		
		/* Validate Name */
		if(name == null){
			return false;
		}
		
		/* Validate Email & Password */
		if(authUser(email, password) == false){
			return false;
		}
		
		/* Validate Address */
		ArrayList<String> addressToks = new ArrayList<String>();
		StringTokenizer addressTokeniser = new StringTokenizer(address);
		int index = 0;
		
		while (addressTokeniser.hasMoreTokens())
		{
		    addressToks.add(index, addressTokeniser.nextToken());
		    index++;
		}
		
		
		
		/* Validate Phone */
		
		
		
		
		
		
	return false;	
	}
		
		/*
		int apartmentNumber, streetNumber, postcode;
		String streetName, suburbName, state, tempString;
		
		if(authUser(email, password) == false){
			return false;
		}
		
		if(name == null){
			return false;
		}
		
		if(address != null){
			
			String delim1 = "/";
			String delim2 = " ";
			
			if(address.contains("/") && isDigit(address.charAt(0));){
				StringTokenizer st1  = new StringTokenizer(address, delim1);
				
				apartmentNumber = address.nextToken();
				streetNumber = address.nextToken();

			}else{
				return false;
			}
			*/
		
			/* Redo this rubbish */
		
		/*
			StringTokenizer st2  = new StringTokenizer(address, delim2);
			
			address.nextToken();
			streetName = address.nextToken();
			suburbName = address.nextToken();
			
			if(address.hasMoreTokens()){
			tempString = address.nextToken();
			if(tempString.matches(".*[0-9].*")){
				postcode = tempString;
			}else{
				suburbName = tempString;
			}
			}else{
				return false;
			}
			
			if(address.hasMoreTokens()){
			tempString = address.nextToken();
			if(tempString.matches(".*[0-9].*")){
				postcode = tempString;
			}else{
				state = tempString;
			}
			}else{
				return false;
			}
				
			if(address.hasMoreTokens()){
			tempString = address.nextToken();
			if(tempString.matches(".*[0-9].*")){
				postcode = tempString;
			}else{
				return false;
			}
			}else{
				return false;
			}
		}
		
		if(phone.matches(".*[a-z].*")){
			return false;
		}
		
		return false;

	
	*/
	

}
