package Unicorn;

import ThemeSetup.Theme;
import ThemeSetup.Palettes.ColorPalette;
import ThemeSetup.Palettes.FontPalette;
import ThemeSetup.Palettes.IconFactory;
import ThemeSetup.Palettes.StylePalette;

public class UnicornThemeFactory implements Theme {
    public ColorPalette getColorPalette() {
        return UnicornColorPalette.getInstance();
    }

    public FontPalette getFontPalette() {
        return UnicornFontFactory.getInstance();
    }

    public StylePalette getStylePalette() {
        return UnicornStylePatelle.getInstance();
    }

    public IconFactory getIconFactory() {
        return UnicornIconFactory.getInstance();
    }
}
