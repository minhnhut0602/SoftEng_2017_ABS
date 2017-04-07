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
		
		
		/* VALIDATE EMAIL */
		int counter, emailLength;
		
		if (email.length() == 0 || Password.length() == 0 || email.matches(".*[ \\(),:;<>[]\"].*")){
			return false;
			/* ERROR: password or email is empty */

		}else{
			
			emailLength = email.length();
			int counterAt = 0;
			int counterPeriod = 0;
			
			/* loop through individual characters in the string 'email' and compares to '@' and '.com', there should only be one of each example in the string of each, and they must be in order '@' followed by '.'*/
			for(counter = 0; counter <= emailLength-1; counter++){
				
				if(counterAt > 1 || counterPeriod > 1){
					return false;
					/* ERROR: too many '@' or '.com' */

				}else{
					
					if(email.charAt(counter) == '@'){
						counterAt += 1;
					}
					
					if(email.toLowerCase().contains(".com") && counterAt < 1){
						return false;
						/* ERROR: '.com' found before '@' */

						
					}else if(email.toLowerCase().contains(".com")){
						counterPeriod += 1;

					}
					if(counterPeriod > 1){
						return false;
						/* ERROR: more than one '.com' found  */

					}
				}
				

				
			}
			
			
		}
		
		/* no validation for passwords, anything at this point that is not an empty string is accepted */
		
		
		/* ***NEEDS TO BE LOOKED AT*** this will check with current email accounts to ensure there are no already existing emails accounts by the same name */
		if(email.equals(customers.get(0).getEmail())){
			return false;
			/* ERROR: email already exists */

		}
		

	}

	public boolean registerUser(String name, String email, String password, String address, String phone) {
		
		/* VALIDATE NAME */
		if(name == null){
			return false;
			/* ERROR: name is empty */

		}
		
		/* VVALIDATE EMAIL AND PASSWORD */
		if(authUser(email, password) == false){
			return false;
			/* ERROR: email or password did not validate */

		}
		
		/* VALIDATE ADDRESS */
		ArrayList<String> addressToks = new ArrayList<String>();
		StringTokenizer addressTokeniser = new StringTokenizer(address);
		int index = 0;
		
		/* tokenises adress */
		while (addressTokeniser.hasMoreTokens())
		{
		    addressToks.add(index, addressTokeniser.nextToken());
		    index++;
		}
		
		
		/* checks the first entry for a number */
		int currentFocus = 0;
		if(addressToks.get(currentFocus).contains("[0-9]+")){
			currentFocus++;
		}
		
		/* loops through all tokens checking for illegal characters, stops on last two */
		while(currentFocus < addressToks.size()-3){
			if(addressToks.get(currentFocus).contains("[a-zA-Z,]+")){
				currentFocus++;
			}else{
				return false;
				/* ERROR: line in address is not in correct format */
			}

		}
		
		/* checks last two tokens for postcode and state */
		while(currentFocus < addressToks.size()-1){
			
		
		if(addressToks.get(currentFocus).matches("[0-9]+")){
			if(addressToks.get(currentFocus).length() == 4){
				currentFocus++;
			}else{
				return false;
				/* ERROR: Post code not 4 digits */

			}
		}
		if(addressToks.get(currentFocus).matches("[a-zA-Z]+")){
			if(addressToks.get(currentFocus).toUpperCase().matches("VIC|NSW|ACT|WA|SA|TAS")){
				currentFocus++;
			}else{
				return false;
				/* ERROR: invalid state provided */
				
			}
		}
		
		}/* While loop end */
		
		
		/* VALIDATE PHONE */
		if(phone.replaceAll("\\s+","").matches("[0-9]+")){
			if(phone.length() == 10 || phone.length() == 8)){
			/* Do nothing */
			}else{
				return false;
				/* ERROR: phone number incorrect length */
			}

		}else{
			return false;
			/* ERROR: phone number has illegal characters*/

		}
		
		/* REGISTER NEW USER */
		customers customer = new customers(name, email, password, address, phone);
		
		
	return false;	
	}
		
	

}
