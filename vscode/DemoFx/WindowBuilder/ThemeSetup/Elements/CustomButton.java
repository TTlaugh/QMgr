package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

public class CustomButton extends BasicButtonUI {
    private static final CustomButton buttonUI = new CustomButton();
    private static Theme theme;

    public static CustomButton createUI(JComponent c) {
        return buttonUI;
    }

    public static void setTheme(Theme theme) {
        CustomButton.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        JButton b = (JButton) c;
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        b.setOpaque(true);
        setBorderAndBackground(b, g2d);

        // Set font and text color
        b.setForeground(theme.getColorPalette().getDefaultTextColor());
        b.setFont(theme.getFontPalette().getDefaultFont());

        // Painting using the parent method
        super.paint(g, c);  

        g2d.dispose();
    }

    private void setBorderAndBackground(JButton b, Graphics2D g2d) {
        int borderRadius = theme.getStylePalette().getBorderRadius();
        int thickness = theme.getStylePalette().getBorderThickness();

        b.setBorder(BorderFactory.createEmptyBorder());
        paintBorder(b, g2d, borderRadius, thickness);
        paintBackground(b, g2d, borderRadius, thickness);
    }

    private void paintBackground(JButton b, Graphics2D g2d, int borderRadius, int thickness) {
        // Paint the button's background respecting the border radius
        g2d.setColor(b.getModel().isPressed() ? 
                                    theme.getColorPalette().getPressedButtonColor() :
                                    theme.getColorPalette().getDefaultButtonColor() );
        g2d.fillRoundRect(  thickness, thickness, 
                            b.getWidth() - thickness * 2, 
                            b.getHeight() - thickness * 2, 
                            borderRadius, borderRadius);
    }

    private void paintBorder(JButton b, Graphics2D g2d, int borderRadius, int thickness) {
        // Paint border
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(theme.getColorPalette().getBorderColor());
        g2d.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), borderRadius * 2, borderRadius * 2);
    }
}