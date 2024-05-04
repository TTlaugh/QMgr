package Unicorn;

import java.awt.Color;

import ThemeSetup.Palettes.ColorPalette;

public class UnicornColorPalette implements ColorPalette {
    private static final UnicornColorPalette instance = new UnicornColorPalette();

    private final Color rose = new Color(254, 231, 230);
    private final Color mint = new Color(236, 253, 241);
    private final Color yellow = new Color(249, 234, 194);
    private final Color brown = new Color(69, 0, 0);
    private final Color orange = new Color(249, 234, 194);

    private UnicornColorPalette() {}

    public static UnicornColorPalette getInstance() {
        return instance;
    }

    public Color getDefaultButtonColor() {
        return mint; // Color is immutable
    }

    public Color getPressedButtonColor() {
        return yellow;
    }

    public Color getBackgroundColor() {
        return Color.WHITE;
    }

    public Color getSelectedListItemColor() {
        return orange;
    }

    public Color getDefaultTextColor() {
        return brown;
    }

    public Color getBorderColor() {
        return brown;
    }

    public Color getTextAreaColor() {
        return Color.WHITE;
    }


    public Color getSliderThumbColor() {
        return mint;
    }

    public Color getSliderTrackFilledColor() {
        return yellow;
    }

    public Color getSliderTrackEmptyColor() {
        return rose;
    }

    public Color getListElementsSeparatorColor() {
        return Color.BLACK;
    }
}
