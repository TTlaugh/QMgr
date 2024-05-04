package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicPasswordFieldUI;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomPasswordField extends BasicPasswordFieldUI {
    private static final CustomPasswordField passwordFieldUI = new CustomPasswordField();
    private static Theme theme;

    public static CustomPasswordField createUI(JComponent c) {
        return passwordFieldUI;
    }

    public static void setTheme(Theme theme) {
        CustomPasswordField.theme = theme;
    }

    @Override
    protected void paintSafely(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintSafely(g);
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);

        setBorder(c);

        // Set font and text color
        c.setForeground(theme.getColorPalette().getDefaultTextColor());
        c.setFont(theme.getFontPalette().getDefaultFont());

        // Set background
        c.setBackground(theme.getColorPalette().getBackgroundColor());
    }

    private void setBorder(JComponent c) {
        Border realBorder = new RoundedBorder(
                            theme.getColorPalette().getBorderColor(),
                            theme.getStylePalette().getBorderThickness(),
                            theme.getStylePalette().getBorderRadius());

        Border paddingBorder = BorderFactory.createEmptyBorder(
                            theme.getStylePalette().getTopBottomPadding(),
                            theme.getStylePalette().getLeftRightPadding(),
                            theme.getStylePalette().getTopBottomPadding(),
                            theme.getStylePalette().getLeftRightPadding());

        Border compoundBorder = BorderFactory.createCompoundBorder(realBorder, paddingBorder);
        c.setBorder(compoundBorder);
    }
}