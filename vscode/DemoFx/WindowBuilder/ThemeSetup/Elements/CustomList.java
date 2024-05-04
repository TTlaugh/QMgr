package ThemeSetup.Elements;

import ThemeSetup.Theme;

import javax.swing.plaf.basic.BasicListUI;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BasicStroke;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CustomList extends BasicListUI {
    public static final CustomList listUI = new CustomList();
    private static Theme theme;

    public static CustomList createUI(JComponent c) {
        return listUI;
    }

    public static void setTheme(Theme theme) {
        CustomList.theme = theme;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        JList l = (JList) c;
        Graphics2D g2d = (Graphics2D) g;

        // Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        // Fill background and draw the border
        c.setBackground(theme.getColorPalette().getBackgroundColor());

        // Paint the children
        drawListElements(g2d, l,
                            theme.getStylePalette().getBorderThickness(),
                            theme.getStylePalette().getLeftRightPadding(),
                            theme.getStylePalette().getTopBottomPadding(),
                            theme.getStylePalette().getListElementsSeparatorThickness(),
                            theme.getColorPalette().getListElementsSeparatorColor(),
                            theme.getColorPalette().getSelectedListItemColor());
    }

    private int selectedIndex;

    private void drawListElements(Graphics2D g2d, JList list, int borderThickness, int leftRightPadding, int topBottomPadding, int separatorThickness, Color separatorColor, Color selectedElementColor) {
        ListCellRenderer renderer = list.getCellRenderer();
        int sumHeight = topBottomPadding * 2 + borderThickness * 2;
        int maxWidth = findMaxWidth(renderer);
        selectedIndex = list.getSelectedIndex();

        for (int i = 0; i < list.getModel().getSize(); i++) {
            Object item = list.getModel().getElementAt(i);

            // render the child
            boolean isSelected = list.isSelectedIndex(i);
            Component rendererComponent = renderer.getListCellRendererComponent(list, item, i, isSelected, false);
            
            int x = leftRightPadding + borderThickness;
            int y = i * rendererComponent.getPreferredSize().height + topBottomPadding + borderThickness;
            int height = rendererComponent.getPreferredSize().height;

            if(selectedIndex == i) rendererComponent.setBackground(selectedElementColor);
            rendererComponent.setBounds(x, y, maxWidth, height);

            // Anti-Aliasing
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            rendererComponent.paint(g2d);
            g2d.dispose();

            if(i > 0) {
                g2d.setStroke(new BasicStroke(separatorThickness));
                g2d.setColor(separatorColor);    
                g2d.drawLine(x + borderThickness, y, maxWidth + leftRightPadding, y);
            }

            sumHeight += height;
        }
        maxWidth += leftRightPadding * 2 + borderThickness * 2;
        list.setPreferredSize(new Dimension(maxWidth, sumHeight));
        list.repaint();
    }

    private int findMaxWidth(ListCellRenderer renderer) {
        int maxWidth = -1;
        for (int i = 0; i < list.getModel().getSize(); i++) {
            Object item = list.getModel().getElementAt(i);
            Component rendererComponent = renderer.getListCellRendererComponent(list, item, i, false, false);
            int width = rendererComponent.getPreferredSize().width;
            
            maxWidth = Math.max(maxWidth, width);
        }
        return maxWidth;
    }

}