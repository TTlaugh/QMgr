package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicCheckBoxUI;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomCheckBox extends BasicCheckBoxUI {
    private static final CustomCheckBox checkBoxUI = new CustomCheckBox();
    private static Theme theme;

    public static CustomCheckBox createUI(JComponent c) {
        return checkBoxUI;
    }

    public static void setTheme(Theme theme) {
        CustomCheckBox.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        
        JCheckBox cb = (JCheckBox) c;
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill with background color
        cb.setBackground(theme.getColorPalette().getBackgroundColor());

        // Draw Check Box square and tick if selected
        cb.setIcon(new CustomCheckBoxIcon(
                        theme.getColorPalette().getBorderColor(),
                        theme.getStylePalette().getBorderThickness(),
                        theme.getStylePalette().getBorderRadius(),
                        theme.getFontPalette().getFontSize(),
                        theme.getFontPalette().getFontSize(),
                        cb.isSelected() ));

        // Set font and text color
        cb.setForeground(theme.getColorPalette().getDefaultTextColor());
        cb.setFont(theme.getFontPalette().getDefaultFont());
       
        // Painting using the parent method
        super.paint(g, c);  

        g2d.dispose();
    }
}