package abs.view.style;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * 
 * The App Style class stores all the GUI colours, borders, fonts etc.
 * <p>
 * Allows for easier updating or changes to look and feel.
 * </p>
 *
 */
public class AppStyle {
	/* Colours */
	public static final Color mainBackgroundColor = Color.DARK_GRAY;
	public static final Color mainForgroundColor = Color.LIGHT_GRAY;
	public static final Color mainFontColor = Color.GRAY;
	public static Color successColor = new Color(111, 195, 102);
	public static Color warningColor = new Color(238, 125, 125);

	/* Fonts */
	public static final Font boldLargeFont = new Font((new JLabel()).getFont().getName(), Font.BOLD, 25);
	public static final Font boldMedFont = new Font((new JLabel()).getFont().getName(), Font.BOLD, 15);

	/* Borders */
	public static final Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);

}