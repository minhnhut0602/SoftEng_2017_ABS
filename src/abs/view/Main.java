package abs.view;

public class Main {

	static Menu menu = new Menu();

	public static void main(String[] args) {

		boolean exit = false;

		while (exit == false) {
			// runs the main menu until exit == true
			// each sub-menu is called by the switch statements inside each
			// static method in this class
			mainMenu();
		}
	}

	private static String mainMenu() {
		return menu.mainMenu();

		// switch statement for the main menu selections

	}

	// i'll need a new method for each of the main menu sub menus

}
