package components;

import javafx.scene.control.Button;

public class Button_Question {

    private Button button;

    public Button_Question(String text) {

        this.button = new Button(text);

        setUp();
    }

    void setUp() {
        this.button.setStyle("-fx-font-size:10px; -fx-font-family:System;");

        this.button.getStyleClass().add("button-xanhnhat");

        this.button.setPrefSize(24, 24);

    }

    public Button getButton() {
        return this.button;
    }

}
