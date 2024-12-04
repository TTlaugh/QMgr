package components;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Answer;
import model.Answer_Select;

public class Answer_card {
    String url_Button = "/imgs/Screenshot_2024-10-30_173217-removebg-preview.png";
    Image image_Button = new Image(url_Button);
    ImageView image_view_Button = new ImageView(image_Button);

    private HBox hBox = new HBox();
    private CheckBox checkBox = new CheckBox();
    private TextField textField = new TextField();
    private Button button = new Button();

    public Answer_card(Answer answer) {
        checkBox.setSelected(answer.isCorrect());

        textField.setText(answer.getContent());

        hBox.getChildren().addAll(checkBox, textField, button);

        setUp();
    }

    public Answer_card(Answer_Select answer_Select) {

        // Correct -> choice
        // Choice -> correct

        boolean isCorrect = answer_Select.isChoice();

        boolean isChoice = answer_Select.isCorrect();

        if (isCorrect == isChoice) {

            checkBox.setSelected(true);

            checkBox.getStyleClass().add("check-box .box");

        } else if (isCorrect && !isChoice) {

            checkBox.setSelected(false);

            checkBox.getStyleClass().add("check-box .box");

        } else if (!isCorrect && isChoice) {

            checkBox.setSelected(true);

            checkBox.getStyleClass().add("check-box .red");
        }

        textField.setText(answer_Select.getContent());

        hBox.getChildren().addAll(checkBox, textField, button);

        setUp();
    }

    void setUp() {
        // hBox
        hBox.setSpacing(15);
        hBox.setPrefHeight(38);
        hBox.setPrefWidth(438);
        hBox.setScaleX(1);
        hBox.setScaleY(1);
        hBox.setScaleZ(1);
        String styleButton = "-fx-font-size: 14px; -fx-font-family:System; ";

        // Image
        image_view_Button.setFitWidth(22);
        image_view_Button.setFitHeight(22);
        image_view_Button.setScaleX(1);
        image_view_Button.setScaleY(1);
        image_view_Button.setScaleZ(1);

        // Button
        button.setGraphic(image_view_Button);
        button.setStyle(styleButton);
        button.setGraphicTextGap(4);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        button.getStyleClass().add("button-donhat");
        button.setBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);
        button.setDepthTest(javafx.scene.DepthTest.INHERIT);
        button.setPickOnBounds(true);
        button.setPrefHeight(38);
        button.setPrefWidth(38);
        button.setLayoutX(0);
        button.setLayoutY(0);
        button.setScaleX(1);
        button.setScaleY(1);
        button.setScaleZ(1);
        // TextField
        String styleTextField = "-fx-font-size: 15px; -fx-font-family:System; ";
        textField.getStyleClass().add("round-layout");
        textField.setStyle(styleTextField);
        textField.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        textField.setFocusTraversable(true);
        textField.setCacheShape(true);
        textField.setCenterShape(true);
        textField.setScaleShape(true);
        textField.setBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);
        textField.setDepthTest(javafx.scene.DepthTest.INHERIT);
        textField.setPickOnBounds(true);
        textField.setMinHeight(38);
        textField.setPrefWidth(347);
        textField.setPrefHeight(38);
        textField.setLayoutX(0);
        textField.setLayoutY(0);
        textField.setScaleX(1);
        textField.setScaleY(1);
        textField.setScaleZ(1);
        // CheckBox
        String styleCheckBox = "-fx-font-size: 14px; -fx-font-family:System; ";
        checkBox.setStyle(styleCheckBox);
        checkBox.setFocusTraversable(true);
        checkBox.setCacheShape(true);
        checkBox.setCenterShape(true);
        checkBox.setScaleShape(true);
        checkBox.setBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);
        checkBox.setDepthTest(javafx.scene.DepthTest.INHERIT);
        checkBox.setPickOnBounds(true);
        checkBox.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        checkBox.setGraphicTextGap(4);
        checkBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        checkBox.setMinHeight(38);
        checkBox.setPrefWidth(27);
        checkBox.setPrefHeight(38);
        checkBox.setLayoutX(0);
        checkBox.setLayoutY(0);
        checkBox.setScaleX(1);
        checkBox.setScaleY(1);
        checkBox.setScaleZ(1);
    }

    public HBox getHBox() {
        return hBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextField getTextField() {
        return textField;
    }

    public Button getButton() {
        return button;
    }
}
