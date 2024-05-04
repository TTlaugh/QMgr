package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomScrollPane extends BasicScrollPaneUI {
    private static final CustomScrollPane scrollPaneUI = new CustomScrollPane();
    private static Theme theme;

    public static CustomScrollPane createUI(JComponent c) {
        return scrollPaneUI;
    }

    public static void setTheme(Theme theme) {
        CustomScrollPane.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill with background color and remove the border
        c.setBackground(theme.getColorPalette().getBackgroundColor());
        c.setBorder(null);
    }    
}