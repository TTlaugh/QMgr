package components;

import javafx.scene.control.Button;

public class Button_Question {
    Button button;

    public Button_Question(String text) {
        button = new Button(text);

        setUp();
    }

    void setUp() {
        // button
    }

    public Button getButton() {
        return button;
    }

}
