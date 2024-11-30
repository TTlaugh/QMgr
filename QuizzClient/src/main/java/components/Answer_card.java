package components;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Answer_card {
    HBox hbox = new HBox();
    CheckBox checkBox = new CheckBox();
    TextField textField = new TextField();

    public Answer_card(String content, boolean isCorrect) {
        checkBox.setSelected(isCorrect);
        textField.setText(content);
        hbox.getChildren().addAll(checkBox, textField);
    }

    public HBox getHbox() {
        return hbox;
    }
}
