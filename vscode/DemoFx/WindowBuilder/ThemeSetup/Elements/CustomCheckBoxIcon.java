package ThemeSetup.Elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import java.awt.BasicStroke;

public class CustomCheckBoxIcon implements Icon {
    private Color borderColor;
    private int width;
    private int height;
    private int borderThickness;
    private int borderRadius;
    private boolean isFilled;

    public CustomCheckBoxIcon(Color borderColor, int borderThickness, int borderRadius, int width, int height, boolean isFilled) {
        this.borderColor = borderColor;
        this.width = width;
        this.height = height;
        this.borderThickness = borderThickness;
        this.borderRadius = borderRadius;
        this.isFilled = isFilled;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        setOuterBorder(g2d);

        if(isFilled) {
            drawTick(g2d);
        }
    }

    private void setOuterBorder(Graphics2D g2d) {
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness / 2));
        g2d.drawRoundRect(borderThickness, borderThickness, 
                            width - borderThickness,
                            height - borderThickness, 
                            borderRadius, 
                            borderRadius);
    }

    private void drawTick(Graphics2D g2d) {
        int tickInset = width / 7; // Inset for the tick to position it inside the checkbox
        int tickSize = width - tickInset * 2; // Size of the tick

        // Coordinates for the checkmark
        int x1 = borderThickness + width / 5;
        int y1 = borderThickness + tickSize / 2 - width / 12;
        int x2 = x1 + tickSize / 3;
        int y2 = y1 + tickSize / 3;
        int x3 = borderThickness + width - width / 5;
        int y3 = borderThickness + width / 4 - width / 3;

        g2d.setColor(borderColor);

        // Draw the tick
        g2d.drawLine(x1, y1, x2, y2); // First part of the tick
        g2d.drawLine(x2, y2, x3, y3); // Second part of the tick
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
