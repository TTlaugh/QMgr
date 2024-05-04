package ThemeSetup.Palettes;

import java.util.function.Function;
import javax.swing.JSlider;

public interface StylePalette {

    int getBorderThickness();

    int getBorderRadius();

    int getLeftRightPadding();
    int getTopBottomPadding();

    int getSideTextAreaPadding();

    int getSliderTrackFillingRadius();

    int getScrollBarThumbBorderRadius();

    Function<JSlider, Integer> getTrackHeight();
    Function<JSlider, Integer> getThumbHeight();

    int getScrollBarThickness();
    void updateScrollBarThickness(int value);

    int getListElementsSeparatorThickness();
}
