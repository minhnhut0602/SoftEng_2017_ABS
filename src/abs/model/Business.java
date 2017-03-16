package abs.model;
//Business Class

import java.util.ArrayList;
import java.util.List;

public class Business {
	private String name;
	private List<Employee> staff = new ArrayList<Employee>();

	public Business(String name, List<Employee> staff) {
		this.name = name;
		this.staff = staff;
	}

}