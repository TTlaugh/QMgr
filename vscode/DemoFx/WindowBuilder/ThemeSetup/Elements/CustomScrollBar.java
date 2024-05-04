package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class CustomScrollBar extends BasicScrollBarUI {
    private static final CustomScrollBar scrollBarUI = new CustomScrollBar();
    private static Theme theme;

    private static final int MIN_DIMENSION_PX = 8;

    public static CustomScrollBar createUI(JComponent c) {
        return scrollBarUI;
    }

    public static void setTheme(Theme theme) {
        CustomScrollBar.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        
        JScrollBar sb = (JScrollBar) c;
        Graphics2D g2d = (Graphics2D) g;

        adjustSize(c, sb.getOrientation() == JScrollBar.VERTICAL);

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        c.setBackground(theme.getColorPalette().getBackgroundColor());

        // Draw
        drawTrack(g2d, c, 
                    theme.getColorPalette().getSliderTrackEmptyColor(), 
                    theme.getColorPalette().getBorderColor(),
                    theme.getStylePalette().getBorderThickness(),
                    theme.getStylePalette().getSliderTrackFillingRadius() );
        drawThumb(g2d, sb, 
                    theme.getColorPalette().getSliderThumbColor(), 
                    theme.getColorPalette().getBorderColor(),
                    theme.getStylePalette().getBorderThickness(),
                    theme.getStylePalette().getScrollBarThumbBorderRadius(),
                    sb.getOrientation() == JScrollBar.VERTICAL );
    }

    private void adjustSize(JComponent c, boolean isVertical) {
        if (isVertical) {
            int newWidth = theme.getStylePalette().getScrollBarThickness();
            if(c.getWidth() > newWidth) {
                c.setLocation(c.getX() + c.getWidth() - newWidth, c.getY());
                c.setPreferredSize(new Dimension(newWidth, c.getHeight()));
            }
            else if(c.getWidth() < newWidth) {
                theme.getStylePalette().updateScrollBarThickness(c.getWidth());
            }
        }
        else {
            int newHeight = theme.getStylePalette().getScrollBarThickness();
            if(c.getHeight() > newHeight) {
                c.setLocation(c.getX(), c.getY() + c.getHeight() - newHeight);
                c.setPreferredSize(new Dimension(c.getWidth(), newHeight));
            }
            else if(c.getHeight() < newHeight) {
                theme.getStylePalette().updateScrollBarThickness(c.getHeight());
            }
        }
    }

    private void drawTrack(Graphics2D graphics2d, JComponent component, Color color, Color borderColor, int borderThickness, int trackFillingRadius) {
        Rectangle trackBounds = component.getVisibleRect();
        graphics2d.setColor(color);
        graphics2d.fillRoundRect(trackBounds.x + borderThickness, trackBounds.y + borderThickness, trackBounds.width - borderThickness * 2, trackBounds.height - borderThickness * 2, trackFillingRadius, trackFillingRadius);

        // Draw the border around the entire track
        graphics2d.setStroke(new BasicStroke(borderThickness));
        graphics2d.setColor(borderColor);
        graphics2d.drawRoundRect(trackBounds.x + borderThickness, trackBounds.y + borderThickness, trackBounds.width - borderThickness * 2, trackBounds.height - borderThickness * 2, trackFillingRadius, trackFillingRadius);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        button.setBackground(new Color(0, 0, 0, 0));
        return button;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        button.setBackground(new Color(0, 0, 0, 0));
        return button;
    }

    private void drawThumb(Graphics2D graphics2d, JScrollBar sb, Color thumbColor, Color thumbBorderColor, int thumbBorderThickness, int thumbFillingRadius, boolean isVertical) {
        if (isVertical) {
            drawVerticalThumb(graphics2d, sb, thumbColor, thumbBorderColor, thumbBorderThickness, thumbFillingRadius);
        }
        else {
            drawHorizontalThumb(graphics2d, sb, thumbColor, thumbBorderColor, thumbBorderThickness, thumbFillingRadius);
        }
        sb.repaint();        
    }

    private void drawHorizontalThumb(Graphics2D graphics2d, JScrollBar sb, Color thumbColor, Color thumbBorderColor, int thumbBorderThickness,
            int thumbFillingRadius) {
        int thumbWidth = calcThumbWidth(sb);
        int thumbX = calcThumbX(thumbWidth, sb);
        
        // Draw the thumb
        graphics2d.setColor(thumbColor);
        graphics2d.fillRoundRect(thumbX, thumbBorderThickness, thumbWidth, sb.getHeight() - thumbBorderThickness * 2, thumbFillingRadius, thumbFillingRadius);
        
        // Draw the border of the thumb
        graphics2d.setColor(thumbBorderColor);
        graphics2d.setStroke(new BasicStroke(thumbBorderThickness));
        graphics2d.drawRoundRect(thumbX, thumbBorderThickness, thumbWidth, sb.getHeight() - thumbBorderThickness * 2, thumbFillingRadius, thumbFillingRadius);
        graphics2d.setStroke(new BasicStroke(1));
    }

    private int calcThumbX(int thumbWidth, JScrollBar sb) {
        // Calculate the position of the thumb
        double thumbRange = sb.getWidth() - thumbWidth;
        double thumbValueProportion = (double)(sb.getValue() - sb.getMinimum()) / (sb.getMaximum() - sb.getMinimum() - sb.getVisibleAmount());
        int thumbX = sb.getInsets().left + (int)(thumbValueProportion * thumbRange);
        return thumbX;
    }

    private int calcThumbWidth(JScrollBar sb) {
        // Calculate the proportion of the thumb that must be visible
        double thumbWidthProportion = (double)sb.getVisibleAmount() / (sb.getMaximum() - sb.getMinimum());
        
        // Calculate the actual width of the thumb
        int thumbWidth = (int)(thumbWidthProportion * sb.getWidth());
        
        // Ensure the thumb is not smaller than a minimum width (e.g., 8 pixels)
        thumbWidth = Math.max(thumbWidth, 8);
        return thumbWidth;
    }

    private void drawVerticalThumb(Graphics2D graphics2d, JScrollBar sb, Color thumbColor, Color thumbBorderColor, int thumbBorderThickness,
            int thumbFillingRadius) {
        int thumbHeight = calcThumbHeight(sb);
        int thumbY = calcThumbY(thumbHeight, sb);
        
        // Draw the thumb
        graphics2d.setColor(thumbColor);
        graphics2d.fillRoundRect(thumbBorderThickness, thumbY, sb.getWidth() - thumbBorderThickness * 2, thumbHeight, thumbFillingRadius, thumbFillingRadius);
        
        // Draw the border of the thumb
        graphics2d.setColor(thumbBorderColor);
        graphics2d.setStroke(new BasicStroke(thumbBorderThickness));
        graphics2d.drawRoundRect(thumbBorderThickness, thumbY, sb.getWidth() - thumbBorderThickness * 2, thumbHeight, thumbFillingRadius, thumbFillingRadius);
        graphics2d.setStroke(new BasicStroke(1));
    }

    private int calcThumbY(int thumbHeight, JScrollBar sb) {
        // Calculate the position of the thumb
        double thumbRange = sb.getHeight() - thumbHeight;
        double thumbValueProportion = (double)(sb.getValue() - sb.getMinimum()) / (sb.getMaximum() - sb.getMinimum() - sb.getVisibleAmount());
        int thumbY = sb.getInsets().top + (int)(thumbValueProportion * thumbRange);
        return thumbY;
    }

    private int calcThumbHeight(JScrollBar sb) {
        // Calculate the proportion of the thumb that must be visible
        double thumbHeightProportion = (double)sb.getVisibleAmount() / (sb.getMaximum() - sb.getMinimum());
        
        // Calculate the actual height of the thumb
        int thumbHeight = (int)(thumbHeightProportion * sb.getHeight());
        
        // Ensure the thumb is not smaller than a minimum height
        thumbHeight = Math.max(thumbHeight, MIN_DIMENSION_PX);
        return thumbHeight;
    }

}