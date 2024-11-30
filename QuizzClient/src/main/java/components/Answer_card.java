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
        textField.setEditable(false);
        hbox.getChildren().addAll(checkBox, textField);

        setUp();
    }

    void setUp() {
        checkBox.setStyle("-fx-font-size: 14px; -fx-font-family:System; ");
        checkBox.setPrefSize(27, 38);
        checkBox.setScaleX(1);
        checkBox.setScaleY(1);
        checkBox.setScaleZ(1);

        textField.getStyleClass().add("round-layout");
        textField.setStyle("-fx-font-size: 14px; -fx-font-family:System; ");
        textField.setPrefSize(382, 38);
        textField.setScaleX(1);
        textField.setScaleY(1);
        textField.setScaleZ(1);
    }

    public HBox getHbox() {
        return hbox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
