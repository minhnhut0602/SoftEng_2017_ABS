package abs.view.GUIComponents;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import abs.view.AppFrame;

/**
 * The ABS Status Bar.
 * 
 * @serial 1.0
 * @version 0.8
 * @serial 6575923166424062757
 * @see javax.swing.JPanel JPanel
 */
public class StatusBar extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6575923166424062757L;

	/**
	 * Instantiates a new status bar.
	 *
	 * @param appFrame
	 *            the app frame for reference
	 */
	public StatusBar(AppFrame appFrame) {
		setLayout(new BorderLayout());

		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JLabel statusEast = new JLabel(DateFormat.getDateInstance().format(new Date()));
		add(statusEast, BorderLayout.EAST);
	}

}
