package utils;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckCheckBox {

    public static boolean checkTextBox(VBox vbox) {
        int numberCheckBoxisSelected = 0;

        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox) {

                HBox hBox = (HBox) node;

                Boolean isCorrect = ((CheckBox) hBox.getChildren().get(0)).isSelected();

                if (isCorrect) {
                    numberCheckBoxisSelected++;

                    if (numberCheckBoxisSelected > 1) {
                        return false; // choice > 1 checkbox
                    }
                }
            }
        }

        return true; // choice 1 or no check box
    }

    public static boolean checkChoiceNoOrOne(VBox vbox) {
        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox) {

                HBox hBox = (HBox) node;

                Boolean isCorrect = ((CheckBox) hBox.getChildren().get(0)).isSelected();

                if (isCorrect) {
                    return false; // choice == 1 checkbox
                }
            }
        }

        return true; // choice 0 checkbox
    }

    public static void checkMultipleCheckBox(VBox vbox, CheckBox checkBox) {
        if (!checkBox.isSelected() && !CheckCheckBox.checkTextBox(vbox)) {
            for (Node node : vbox.getChildren()) {
                if (node instanceof HBox) {
                    HBox hBox = (HBox) node;
                    ((CheckBox) hBox.getChildren().get(0)).setSelected(false);
                }
            }
        }
    }
}
