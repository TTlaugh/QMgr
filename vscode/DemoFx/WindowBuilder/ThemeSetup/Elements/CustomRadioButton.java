package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomRadioButton extends BasicRadioButtonUI {
    private static final CustomRadioButton radioButtonUI = new CustomRadioButton();    
    private static Theme theme;

    public static CustomRadioButton createUI(JComponent c) {
        return radioButtonUI;
    }

    public static void setTheme(Theme theme) {
        CustomRadioButton.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        
        JRadioButton rb = (JRadioButton) c;
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill with background color
        rb.setBackground(theme.getColorPalette().getBackgroundColor());

        // Draw Radio Button circle
        rb.setIcon(new CustomRadioButtonIcon(
                        theme.getColorPalette().getBorderColor(),
                        theme.getColorPalette().getBackgroundColor(),
                        theme.getStylePalette().getBorderThickness(),
                        theme.getFontPalette().getFontSize(),
                        theme.getFontPalette().getFontSize(),
                        rb.isSelected()
                        ));

        // Set font and text color
        rb.setForeground(theme.getColorPalette().getDefaultTextColor());
        rb.setFont(theme.getFontPalette().getDefaultFont());

        super.paint(g, c);
    }
}