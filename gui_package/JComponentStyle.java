package gui_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;

import fonts.LoadCustomFonts;


public class JComponentStyle {
	
	/** Resolutions */
	// Resolution taken from an iPhone5 320x600 pixels
	public final static int rx = 340; // horizontal resolution
	public final static int ry = 600; // vertical resolution
	
	
	/** Margins */
	//public static int margin_banner = 25; 
	public static Dimension dimension_margin_banner = new Dimension(0,25); // 25 pixels from banner to next component
	public static Dimension dimension_lower_area = new Dimension(0, ry/4); // 1/4 total screen area 
	public static Dimension dimension_component_small = new Dimension(0, 10);
	public static Dimension dimension_component_medium = new Dimension(0, 20);
	public static Dimension dimension_component_large = new Dimension(0, 30);
	public static Dimension dimension_component_huge = new Dimension(0, 40);
	
	/** Fonts */
	public static Font oarkney_reg = new Font("Serif",Font.PLAIN, 25); //Change with method
	public static Font oarkney_bold = new Font("Serif",Font.PLAIN, 25); //Change with method
	public static Font qontra_reg = new Font("Serif",Font.PLAIN, 16); //Change with method
	
	/** Colors */
	/* Material UI */
	public static Color red_alizarin = new Color(231, 76, 60);
	public static Color green_emerald = new Color(46, 204, 113);
	public static Color blue_peterriver = new Color(52, 152, 219); //vivid blue
	public static Color blue_belizehole = new Color(41, 128, 185); //darker blue
	public static Color purple_amethyst = new Color(155, 89, 182);
	public static Color gray_concrete = new Color(149, 165, 166);
	public static Color black_midnight = new Color(44, 62, 80);
	public static Color white_clouds = new Color(236, 240, 241); //Whitest
	public static Color white_silver = new Color(189,195,199); //White almost gray
	
	/* Pallete1 */
	public static Color blue_yankeesblue = new Color(37,40,61); //Almost purple
	public static Color black_eerie = new Color(22,25,37); 
	public static Color gray_space = new Color(70,73,76);
	public JComponentStyle() {
	}

	/** Gray-Black Theme */
	public static void setToolTipStyle1() {
		UIManager.put("ToolTip.background", gray_space); 
		Border border = BorderFactory.createLineBorder(black_eerie);
		UIManager.put("ToolTip.border", border);
		UIManager.put("ToolTip.foreground", white_clouds);
		ToolTipManager.sharedInstance().setDismissDelay(8000); // 8 second delay
	}

	/** Initialize Custom Fonts */
	public static void setCustomFonts(){
		//Qontra.otf
		InputStream font_loc = LoadCustomFonts.class.getResourceAsStream("Qontra.otf");
		try {
			qontra_reg = Font.createFont(Font.TRUETYPE_FONT, font_loc);
			qontra_reg = qontra_reg.deriveFont(Font.PLAIN, 20);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(qontra_reg);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// Orkney.otf
		InputStream font_loc2 = LoadCustomFonts.class.getResourceAsStream("OrkneyReg.otf");
		try {
			oarkney_reg = Font.createFont(Font.TRUETYPE_FONT, font_loc2);
			oarkney_reg = oarkney_reg.deriveFont(Font.PLAIN, 20);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(oarkney_reg);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Orkney.otf
		InputStream font_loc3 = LoadCustomFonts.class.getResourceAsStream("OrkneyBold.otf");
		try {
			oarkney_bold = Font.createFont(Font.TRUETYPE_FONT, font_loc3);
			oarkney_bold = oarkney_bold.deriveFont(Font.PLAIN, 20);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(oarkney_bold);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}