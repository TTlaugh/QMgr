package ThemeSetup.Elements;

import ThemeSetup.Theme;

import java.util.function.Function;

import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.JComponent;
import javax.swing.JSlider;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class CustomSlider extends BasicSliderUI {
    private static final CustomSlider sliderUI = new CustomSlider();
    private static Theme theme;

    public static CustomSlider createUI(JComponent c) {
        return sliderUI;
    }

    public static void setTheme(Theme theme) {
        CustomSlider.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        JSlider s = (JSlider) c;
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill with background color and remove border
        c.setBorder(null);
        c.setBackground(theme.getColorPalette().getBackgroundColor());

        // Draw 
        drawTrack(g2d, s,
                    theme.getColorPalette().getSliderTrackEmptyColor(),
                    theme.getColorPalette().getSliderTrackFilledColor(),
                    theme.getColorPalette().getBorderColor(),
                    theme.getStylePalette().getLeftRightPadding(),
                    theme.getStylePalette().getBorderThickness(),
                    theme.getStylePalette().getSliderTrackFillingRadius(),
                    theme.getStylePalette().getTrackHeight() );
        drawThumb(g2d, s,
                    theme.getColorPalette().getSliderThumbColor(),
                    theme.getColorPalette().getBorderColor(),
                    theme.getStylePalette().getBorderThickness(),
                    theme.getStylePalette().getThumbHeight() );
    }

    private void drawTrack(Graphics2D g2d, JSlider slider, Color emptyTrackColor, Color filledTrackColor, Color borderColor, int leftRightPadding, int borderThickness, int trackFillingRadius, Function<JSlider, Integer> getHeightFunction) {
        Rectangle trackBounds = slider.getVisibleRect();

        // Calculate the width of the filled part
        int filledWidth = (int) (trackBounds.width * ((double) slider.getValue() / slider.getMaximum()));

        // Set the height to a third of the visible rect's height
        int height = getHeightFunction.apply(slider);
        int y = trackBounds.y + trackBounds.height / 2 - height / 2;

        // Set the stroke for the border
        g2d.setStroke(new BasicStroke(borderThickness));

        // Draw the filled part with a gap for the border
        g2d.setColor(filledTrackColor);
        g2d.fillRoundRect(trackBounds.x + leftRightPadding + borderThickness, y, filledWidth - borderThickness, height, trackFillingRadius, trackFillingRadius);

        // Draw the remaining part with a gap for the border
        g2d.setColor(emptyTrackColor);
        g2d.fillRoundRect(trackBounds.x + filledWidth - borderThickness, y, trackBounds.width - filledWidth - leftRightPadding, height, trackFillingRadius, trackFillingRadius);

        // Draw the border around the entire track
        g2d.setColor(borderColor);
        g2d.drawRoundRect(trackBounds.x + leftRightPadding + borderThickness, y, trackBounds.width - leftRightPadding - borderThickness * 3, height, trackFillingRadius, trackFillingRadius);

        // Reset stroke to default if needed elsewhere
        g2d.setStroke(new BasicStroke(1));
        slider.repaint();
    }

    private void drawThumb(Graphics2D graphics2d, JSlider slider, Color thumbColor, Color borderColor, int borderThickness, Function<JSlider, Integer> getHeightFunction) {
        int thumbSize = getHeightFunction.apply(slider); // The diameter of the thumb
        int thumbX = slider.getValue() * (slider.getWidth() - thumbSize) / slider.getMaximum(); // Calculate the X position of the thumb
        int thumbY = slider.getHeight() / 2 - thumbSize / 2; // Center the thumb vertically
        
        // Fill the thumb
        graphics2d.setColor(thumbColor);
        graphics2d.fillOval(thumbX, thumbY, thumbSize, thumbSize); // Adjust for border

        // Draw the thumb border
        graphics2d.setColor(borderColor);
        graphics2d.setStroke(new BasicStroke(borderThickness));
        graphics2d.drawOval(thumbX, thumbY, thumbSize, thumbSize); // Subtract borderThickness to fit the border inside
    }
}