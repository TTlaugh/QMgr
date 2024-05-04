package Unicorn;

import javax.swing.ImageIcon;

import ThemeSetup.Palettes.IconFactory;

public class UnicornIconFactory implements IconFactory {
    private static final UnicornIconFactory instance = new UnicornIconFactory();
    private String path = "Unicorn/Icons/8213363_campaign_marketing_advertising_digital_announce_icon.svg";

    private UnicornIconFactory() {}

    public static UnicornIconFactory getInstance() {
        return instance;
    }

    public ImageIcon getDefaultIcon() {
        return new ImageIcon(path);
    }   

    public String getPathToDefaultIcon() {
        return path;
    }
}
