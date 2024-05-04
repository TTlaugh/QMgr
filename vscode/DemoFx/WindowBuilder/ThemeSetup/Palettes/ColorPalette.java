package ThemeSetup.Palettes;
import java.awt.Color;

public interface ColorPalette {

    // Button
    Color getDefaultButtonColor();
    Color getPressedButtonColor();

    // Background
    Color getBackgroundColor();

    // List
    Color getSelectedListItemColor();

    // Text
    Color getDefaultTextColor();
    Color getTextAreaColor();

    // Border
    Color getBorderColor();

    // Slider
    Color getSliderTrackFilledColor();
    Color getSliderTrackEmptyColor();
    Color getSliderThumbColor();

    // List
    Color getListElementsSeparatorColor();
}
