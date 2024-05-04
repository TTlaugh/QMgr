package Unicorn;

import java.awt.Font;

import ThemeSetup.Palettes.FontPalette;
import ThemeSetup.FontLoader;

public class UnicornFontFactory implements FontPalette {
    private static final UnicornFontFactory instance = new UnicornFontFactory();

    private final String defaultFontPath = "WindowBuilder\\Unicorn\\Fonts\\nevermind_rounded\\NeverMindRounded\\NeverMindRounded-Regular.ttf";
    private final int defaultFontSize = 12;

    private Font defaultFont;

    private UnicornFontFactory() {}

    public static UnicornFontFactory getInstance() {
        return instance;
    }

    public int getMinFontSize() {
        return 5;
    }

    // default size is 14, but is usually dynamically changed
    public Font getDefaultFont() {
        if(defaultFont == null) {
            defaultFont = FontLoader.loadFont(defaultFontPath, defaultFontSize);
            //defaultFont = new Font("Calibri", Font.PLAIN, defaultFontSize); - if some other font should be used
        }
        return defaultFont;
    }

    public int getFontSize() {
        return defaultFontSize;
    }
}
