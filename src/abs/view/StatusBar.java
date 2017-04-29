package abs.view;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 */
public class StatusBar extends JPanel {

	private static final long serialVersionUID = -6575923166424062757L;

	/**
	 * @param appFrame
	 *            the parent frame, for easy reference
	 */
	public StatusBar(AppFrame appFrame) {
		setLayout(new BorderLayout());

		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JLabel statusEast = new JLabel(DateFormat.getDateInstance().format(new Date()));
		add(statusEast, BorderLayout.EAST);
	}

}
