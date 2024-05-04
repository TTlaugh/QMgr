package Unicorn;
import javax.swing.plaf.metal.MetalLookAndFeel;

import javax.swing.UIDefaults;

import ThemeSetup.Theme;

public class UnicornLookAndFeel extends MetalLookAndFeel {
    @Override
    protected void initComponentDefaults(UIDefaults defaults) {
        super.initComponentDefaults(defaults);

        Theme theme = new UnicornThemeFactory();
        Theme.setupElements(theme, defaults);
    }

    @Override
    public String getName() {
        return "Unicorn Look and Feel";
    }

    @Override
    public String getID() {
        return "Unicorn";
    }

    @Override
    public String getDescription() {
        return "A sweet theme created with the Window Builder Extension for VS Code!";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
}
