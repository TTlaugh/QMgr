package ThemeSetup;

import ThemeSetup.Elements.CustomButton;
import ThemeSetup.Elements.CustomCheckBox;
import ThemeSetup.Elements.CustomLabel;
import ThemeSetup.Elements.CustomList;
import ThemeSetup.Elements.CustomPasswordField;
import ThemeSetup.Elements.CustomProgressBar;
import ThemeSetup.Elements.CustomRadioButton;
import ThemeSetup.Elements.CustomScrollBar;
import ThemeSetup.Elements.CustomScrollPane;
import ThemeSetup.Elements.CustomSlider;
import ThemeSetup.Elements.CustomTextArea;
import ThemeSetup.Elements.CustomTextField;
import ThemeSetup.Palettes.ColorPalette;
import ThemeSetup.Palettes.FontPalette;
import ThemeSetup.Palettes.StylePalette;

import javax.swing.UIDefaults;

public interface Theme {
    ColorPalette getColorPalette();

    FontPalette getFontPalette();

    StylePalette getStylePalette();

    //IconFactory getIconFactory();

    static void setupElements(Theme theme, UIDefaults defaults) {
        // TODO: set up icon, use commented line #postponed
        //defaults.put("JFrame.icon", theme.getIconFactory().getDefaultIcon());
        defaults.put("Panel.background", theme.getColorPalette().getBackgroundColor());

        CustomButton.setTheme(theme);
        defaults.put("ButtonUI", CustomButton.class.getName());

        CustomLabel.setTheme(theme);
        defaults.put("LabelUI", CustomLabel.class.getName());
        
        CustomTextField.setTheme(theme);
        defaults.put("TextFieldUI", CustomTextField.class.getName());
        
        CustomPasswordField.setTheme(theme);
        defaults.put("PasswordFieldUI", CustomPasswordField.class.getName());

        CustomTextArea.setTheme(theme);
        defaults.put("TextAreaUI", CustomTextArea.class.getName());
    
        CustomScrollPane.setTheme(theme);
        defaults.put("ScrollPaneUI", CustomScrollPane.class.getName());
    
        CustomSlider.setTheme(theme);
        defaults.put("SliderUI", CustomSlider.class.getName());
        
        CustomScrollBar.setTheme(theme);
        defaults.put("ScrollBarUI", CustomScrollBar.class.getName());

        CustomCheckBox.setTheme(theme);
        defaults.put("CheckBoxUI", CustomCheckBox.class.getName());
        
        CustomRadioButton.setTheme(theme);
        defaults.put("RadioButtonUI", CustomRadioButton.class.getName());

        CustomList.setTheme(theme);
        defaults.put("ListUI", CustomList.class.getName());

        CustomProgressBar.setTheme(theme);
        defaults.put("ProgressBarUI", CustomProgressBar.class.getName());
    }
}