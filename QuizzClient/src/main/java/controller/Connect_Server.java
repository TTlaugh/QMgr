package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import services.StartClient;
import utils.Notification;

import java.io.IOException;

import javafx.event.ActionEvent;

public class Connect_Server {

    public static StartClient client;

    private Parent root = null;

    @FXML
    private TextField tf_StudentID_Connect;

    @FXML
    private TextField tf_IPAdresss_Connect;

    @FXML
    private TextField tf_Port_Connect;

    @FXML
    void btn_StartExam_Connect(ActionEvent event) throws IOException {
        String studentID = tf_StudentID_Connect.getText();

        if (studentID.isEmpty() || studentID == null || studentID.equals("")) {
            Notification.Error("Error", "Please enter your student ID");
            return;
        }

        String ip = tf_IPAdresss_Connect.getText();

        if (ip.isEmpty() || ip == null || ip.equals("")) {
            Notification.Error("Error", "Please enter server IP");
            return;
        }

        String port = tf_Port_Connect.getText();

        if (port.isEmpty() || port == null || port.equals("")) {
            Notification.Error("Error", "Please enter server port");
            return;
        }

        client = new StartClient(studentID, ip, Integer.parseInt(port));

        while (client.getHostExam() == null) {
            if (Notification.Comfrim("Notify", "Waiting for server").getResult() == ButtonType.YES)
                continue;
        }

        root = (Parent) FXMLLoader.load(getClass().getResource("/ui/take_the_exam_submit.fxml"));
        ((Node) event.getSource()).getScene().setRoot(root);

    }
}
