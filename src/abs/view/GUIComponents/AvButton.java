package abs.view.GUIComponents;

import javax.swing.JButton;

import abs.model.Booking;

public class AvButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6924951867221607392L;
	
	private Booking booking;
	
	public AvButton(String s){
		super(s);
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	

}
