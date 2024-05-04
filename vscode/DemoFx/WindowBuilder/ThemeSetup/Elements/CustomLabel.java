package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomLabel extends BasicLabelUI {
    private static final CustomLabel labelUI = new CustomLabel();
    private static Theme theme;

    public static CustomLabel createUI(JComponent c) {
        return labelUI;
    }

    public static void setTheme(Theme theme) {
        CustomLabel.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        JLabel l = (JLabel) c;
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Set font and text color
        l.setForeground(theme.getColorPalette().getDefaultTextColor());
        l.setFont(theme.getFontPalette().getDefaultFont());
       
        // Painting using the parent method
        super.paint(g, c);  

        g2d.dispose();
    }
}