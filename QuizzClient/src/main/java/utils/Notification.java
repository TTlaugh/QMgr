package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Notification {

	public static void Error(String title, String content, String header) {
		Alert typeAlert = new Alert(AlertType.ERROR);
		Button okButton = new Button("Cancel");
		okButton.setOnAction(event -> {
			typeAlert.hide();
		});
		typeAlert.setContentText(content);
		typeAlert.setHeaderText(header);
		typeAlert.setTitle(title);
		typeAlert.showAndWait();
	}

	public static Alert Comfrim(String title, String content, String header) {
		Alert alert = new Alert(AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.CANCEL);
		alert.setContentText(content);
		alert.setHeaderText(header);
		alert.setTitle(title);
		alert.showAndWait();
		return alert;
	}

	public static void Infomation(String title, String content, String header) {
		Alert typeAlert = new Alert(AlertType.INFORMATION);
		Button okButton = new Button("Cancel");
		okButton.setOnAction(event -> {
			typeAlert.hide();
		});
		typeAlert.setContentText(content);
		typeAlert.setHeaderText(header);
		typeAlert.setTitle(title);
		typeAlert.showAndWait();
	}
}
