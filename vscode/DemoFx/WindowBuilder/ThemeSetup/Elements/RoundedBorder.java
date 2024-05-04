package ThemeSetup.Elements;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private int radius;
    private Color color;
    private int thickness;

    public RoundedBorder(Color color, int thickness, int radius) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        int adjust = thickness / 2;
        g2d.drawRoundRect(x + adjust, y + adjust, width - thickness * 2, height - thickness * 2, radius, radius);
        
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.thickness, this.thickness, this.thickness, this.thickness);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = this.thickness;
        return insets;
    }
}

