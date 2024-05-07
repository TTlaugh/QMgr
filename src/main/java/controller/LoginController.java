package controller;

import java.io.IOException;

import business.model.Teacher;
import business.services.WelcomeFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DisplayDialog_Notification;

public class LoginController {
	private Parent root = null;
	@FXML
	private AnchorPane Anchor_Regester;
	@FXML
	private AnchorPane Anchor_SignIn;
	@FXML
	private TextField textField_Email_SignUp_Login;

	@FXML
	private TextField textField_FirstName_SignUp_Login;

	@FXML
	private TextField textField_LastName_SignUp_Login;

	@FXML
	private PasswordField textField_Password_SignUp_Login;

	@FXML
	private TextField textField_Phone_SignUp_Login;

	@FXML
	private TextField textField_USer_SignUp_Login;

	@FXML
	private TextField textField_User_Login;

	@FXML
	private PasswordField textField_Password_Login;

	public static Teacher teacher_Current;

	private WelcomeFunction welcom_Login = new WelcomeFunction();

	public void Sign_Up_Tranfer(ActionEvent event) throws IOException {
		(Anchor_SignIn).setVisible(false);
		(Anchor_Regester).setVisible(true);
	}

	public void Sign_In_Tranfer(ActionEvent event) throws IOException {
		(Anchor_Regester).setVisible(false);
		(Anchor_SignIn).setVisible(true);
	}

	public void Sign_In(ActionEvent event) throws IOException {	
			if (textField_User_Login.getText() == null || textField_User_Login.getText() == ""
					|| textField_Password_Login.getText() == null || textField_Password_Login.getText() == "") {
				DisplayDialog_Notification.Dialog_Error("Error", " Please enter complete information", " Error");
			} else {
				try {
					teacher_Current = welcom_Login.signIn(textField_User_Login.getText(),
							textField_Password_Login.getText());
					
					if (teacher_Current != null  ) {
						root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
						((Node) event.getSource()).getScene().setRoot(root);
					} else {
						DisplayDialog_Notification.Dialog_Error(" Error", "Username or password is incorrect", " Error");
					}
					
				}catch(Exception e) {
					DisplayDialog_Notification.Dialog_Error("Error", "Tai khoan khong co trong du lieu", null);
				}
			}
	}

	@FXML
	void SignUp_Login(ActionEvent event) throws IOException {
		if (textField_Email_SignUp_Login.getText() != null && textField_Email_SignUp_Login.getText() != ""
				&& textField_FirstName_SignUp_Login.getText() != null
				&& textField_FirstName_SignUp_Login.getText() != "" && textField_LastName_SignUp_Login.getText() != null
				&& textField_LastName_SignUp_Login.getText() != "" && textField_Phone_SignUp_Login.getText() != null
				&& textField_Phone_SignUp_Login.getText() != "" && textField_USer_SignUp_Login.getText() != null
				&& textField_USer_SignUp_Login.getText() != "" && textField_Password_SignUp_Login.getText() != null
				&& textField_Password_SignUp_Login.getText() != "") {
			Teacher teacher_SignUp = new Teacher(
					textField_USer_SignUp_Login.getText(), 
					textField_FirstName_SignUp_Login.getText(), 
					textField_LastName_SignUp_Login.getText(),
					textField_Phone_SignUp_Login.getText(),
					textField_Email_SignUp_Login.getText());
			if (welcom_Login.signUp(teacher_SignUp, textField_Password_SignUp_Login.getText())
					&& DisplayDialog_Notification
							.Dialog_Comfrim("Notification", "Congratulations, you have successfully registered",
									"Do you want to switch to the login page now?")
							.getResult() == ButtonType.YES) 
				Sign_In_Tranfer(event);
		} else {
			DisplayDialog_Notification.Dialog_Error(" Notice filled in completely", "Please enter complete information",
					"Error");
		}
	}

}
