package ThemeSetup.Elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;

public class CustomRadioButtonIcon implements Icon {
    private Color circleColor;
    private Color backgroundColor;
    private int width;
    private int height;
    private int borderThickness;
    private boolean isFilled;

    public CustomRadioButtonIcon(Color circleColor, Color backgroundColor, int borderThickness, int width, int height, boolean isFilled) {
        this.circleColor = circleColor;
        this.backgroundColor = backgroundColor;
        this.width = width;
        this.height = height;
        this.borderThickness = borderThickness;
        this.isFilled = isFilled;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int circleRadius = Math.min(width, height) / 2 - 1;
        int circleX = x + (width - circleRadius * 2) / 2;
        int circleY = y + (height - circleRadius * 2) / 2;
        
        // Draw outer border
        g2.setColor(circleColor);
        g2.fillOval(circleX, circleY, circleRadius * 2, circleRadius * 2);

        // Draw the space inbetween
        g2.setColor(backgroundColor);
        g2.fillOval(circleX + borderThickness / 2, circleY + borderThickness / 2, circleRadius * 2 - borderThickness, circleRadius * 2 - borderThickness);

        // Draw the filling
        if (isFilled) {
            g2.setColor(circleColor);
            g2.fillOval(circleX + borderThickness, circleY + borderThickness, circleRadius * 2 - borderThickness * 2, circleRadius * 2 - borderThickness * 2);
        }
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
