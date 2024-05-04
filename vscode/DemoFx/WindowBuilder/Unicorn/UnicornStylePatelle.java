package Unicorn;

import java.util.function.Function;
import javax.swing.JSlider;

import ThemeSetup.Palettes.StylePalette;

public class UnicornStylePatelle implements StylePalette {
    private static final UnicornStylePatelle instance = new UnicornStylePatelle();

    private UnicornStylePatelle() {}

    public static UnicornStylePatelle getInstance() {
        return instance;
    }

    public int getBorderRadius() {
        return 5;
    }

    public int getBorderThickness() {
        return 2;
    }

    public int getLeftRightPadding() {
        return 2;
    }

    public int getTopBottomPadding() {
        return 2;
    }

    public int getSideTextAreaPadding() {
        return 4;
    }

    public int getSliderTrackFillingRadius() {
        return 10;
    }

    public int getScrollBarThumbBorderRadius() {
        return 5;
    }

    public Function<JSlider, Integer> getTrackHeight() {
        return slider -> slider.getHeight() / 4;
    }

    public Function<JSlider, Integer> getThumbHeight() {
        return slider -> slider.getHeight() / 2;
    }

    private int scrollBarThickness = 20;

    public int getScrollBarThickness() {
        return scrollBarThickness;
    }

    public void updateScrollBarThickness(int value) {
        scrollBarThickness = value;
    }

    public int getListElementsSeparatorThickness() {
        return 1;
    }
}