package src.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UITheme {

    // Colors
    public static final Color BACKGROUND = new Color(245, 245, 245);
    public static final Color FONT_COLOR = new Color(53, 56, 57);
    public static final Color INPUT_BACKGROUND = new Color(234, 234, 234);
    public static final Color BUTTON_COLOR = new Color(113, 186, 225);
    public static final Color BUTTON_FONT = Color.WHITE;
    public static final Color BORDER_COLOR = new Color(192, 192, 192);

    // Fonts
    public static final Font FONT_TITLE = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    public static final Font FONT_NORMAL = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    public static final Font FONT_ITALIC = new Font(Font.SANS_SERIF, Font.ITALIC, 12);
    public static final Font FONT_INPUT = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    // Borders
    public static final Border INPUT_BORDER = BorderFactory.createLineBorder(BORDER_COLOR);

    // Images — loaded once via static initializer
    public static final ImageIcon LOGO_ICON;
    public static final ImageIcon WINDOW_ICON;

    static {
        ImageIcon raw = new ImageIcon(UITheme.class.getResource("/assets/img/EMS_logo.png"));
        Image scaled = raw.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        LOGO_ICON = new ImageIcon(scaled);
        WINDOW_ICON = new ImageIcon(UITheme.class.getResource("/assets/img/EMS_logo_icon_48x48.png"));
    }

    private UITheme() {}
}