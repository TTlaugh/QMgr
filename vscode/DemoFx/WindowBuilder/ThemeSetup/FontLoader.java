package ThemeSetup;

import java.io.File;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontLoader {
    public static Font loadFont(String path, float size) {
        try {
            // create the font
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // register the font
            ge.registerFont(customFont);
            return customFont;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
