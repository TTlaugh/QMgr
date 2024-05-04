package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.BasicStroke;

public class CustomProgressBar extends BasicProgressBarUI {
    private static final CustomProgressBar progressBarUI = new CustomProgressBar();
    
    private static Theme theme;

    public static CustomProgressBar createUI(JComponent c) {
        return progressBarUI;
    }

    public static void setTheme(Theme theme) {
        CustomProgressBar.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill with background color
        c.setBorder(null);
        c.setBackground(theme.getColorPalette().getBackgroundColor());

        // Draw figure
        drawTrack(g2d, c, 
                    theme.getColorPalette().getSliderTrackEmptyColor(),
                    theme.getColorPalette().getBorderColor(),
                    theme.getStylePalette().getBorderThickness(),
                    theme.getStylePalette().getScrollBarThumbBorderRadius() );

        drawFilling(g2d, c,
                    theme.getColorPalette().getSliderTrackFilledColor(),
                    theme.getColorPalette().getBorderColor(),
                    theme.getStylePalette().getBorderThickness(),
                    theme.getStylePalette().getScrollBarThumbBorderRadius() );
        
    }

    private void drawTrack(Graphics2D g2d, JComponent c, Color color, Color borderColor, int borderThickness, int trackFillingRadius) {
        Rectangle trackBounds = c.getVisibleRect();
        g2d.setColor(color);
        g2d.fillRoundRect(trackBounds.x + borderThickness, trackBounds.y + borderThickness, trackBounds.width - borderThickness * 2, trackBounds.height - borderThickness * 2, trackFillingRadius, trackFillingRadius);

        // Draw the border around the entire track
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.setColor(borderColor);
        g2d.drawRoundRect(trackBounds.x + borderThickness, trackBounds.y + borderThickness, trackBounds.width - borderThickness * 2, trackBounds.height - borderThickness * 2, trackFillingRadius, trackFillingRadius);
    }

    private void drawFilling(Graphics2D graphics2d, JComponent component, Color fillingColor, Color borderColor, int borderThickness, int radius) {
        Rectangle trackBounds = progressBar.getVisibleRect();
        int adjustedValue = progressBar.getValue() - progressBar.getMinimum();
        int progressBarRange = progressBar.getMaximum() - progressBar.getMinimum();
        int filledWidth = (int) (trackBounds.getWidth() * adjustedValue / (double) progressBarRange);
        graphics2d.setColor(fillingColor);
        graphics2d.fillRoundRect(trackBounds.x + borderThickness, trackBounds.y + borderThickness, filledWidth - borderThickness * 2, trackBounds.height - borderThickness * 2, radius, radius);

        // Draw the border around the entire track
        graphics2d.setStroke(new BasicStroke(borderThickness));
        graphics2d.setColor(borderColor);
        graphics2d.drawRoundRect(trackBounds.x + borderThickness, trackBounds.y + borderThickness, filledWidth - borderThickness * 2, trackBounds.height - borderThickness * 2, radius, radius);
    }
}