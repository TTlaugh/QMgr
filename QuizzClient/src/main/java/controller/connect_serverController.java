package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.Notification;
import utils.CheckTextField;
import javafx.event.ActionEvent;

public class connect_serverController {
    @FXML
    private TextField tfStuID;
    @FXML
    private Button btnStartEx;
    @FXML
    private TextField tfIP;
    @FXML
    private TextField tfPort;

    // Event Listener on Button[#btnStartEx].onAction
    @FXML
    public void btnStartClicked(ActionEvent event) {
        String stuID = tfStuID.getText();
        String ipVal = tfIP.getText();
        String portVal = tfPort.getText();

        if (stuID.length() == 0 || stuID == null || stuID == "") {
            Notification.Error("Student ID cannot be empty", "Please enter Student ID!", null);
            return;
        }
        if (stuID.length() > 10) {
            Notification.Error("Error", "Student ID is invalid!", null);
            return;
        }

        if (ipVal.length() == 0 || ipVal == null || ipVal == "") {
            Notification.Error("Server IP cannot be empty", "Please enter Server IP!", null);
            return;
        }
        if (ipVal.length() > 13) {
            Notification.Error("Error", "Server IP is invalid!", null);
            return;
        }

        if (portVal.length() == 0 || portVal == null || portVal == "") {
            Notification.Error("Port cannot be empty", "Please enter Port!", null);
            return;
        }
        if (!CheckTextField.check_String_Number(portVal) || portVal.length() > 4) {
            Notification.Error("Error", "Port is invalid!", null);
            return;
        }

    }
}
