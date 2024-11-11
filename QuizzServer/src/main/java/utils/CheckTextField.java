package utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextFormatter;

public class CheckTextField {
    public static boolean check_String_Number(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void notCharTextField(javafx.scene.control.TextField textField) {

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public static void limitCharTextField(javafx.scene.control.TextField textField, int maxLength) {
        textField.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() > maxLength) {
                return null; // Không cho phép thay đổi
            }
            return change;
        }));
    }
}
