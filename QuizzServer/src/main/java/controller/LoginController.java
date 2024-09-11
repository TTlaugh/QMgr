package controller;

import java.io.IOException;

import business.model.Teacher;
import business.services.WelcomeFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import utils.Notification;

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

	public boolean checkInfomationLogin() {
		return textField_User_Login.getText() == null || textField_User_Login.getText() == ""
				|| textField_Password_Login.getText() == null || textField_Password_Login.getText() == "";
	}

	@FXML
	public void Sign_In(ActionEvent event) throws IOException {
		if (checkInfomationLogin()) {
			Notification.Error(Notification.Error, " Nhập đầy đủ thông tin");
			return;
		}

		try {
			teacher_Current = welcom_Login.signIn(textField_User_Login.getText(),
					textField_Password_Login.getText());
			if (teacher_Current == null) {
				Notification.Error(Notification.Error, "Tài khoản hoặc mật khẩu không đúng");
				return;
			}
			root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
			((Node) event.getSource()).getScene().setRoot(root);

		} catch (Exception e) {
			Notification.Error(Notification.Error, "Tài khoản chưa được tạo");
		}

	}

	public boolean checkInfomationSignUp() {
		return textField_Email_SignUp_Login.getText() != null && textField_Email_SignUp_Login.getText() != ""
				&& textField_FirstName_SignUp_Login.getText() != null
				&& textField_FirstName_SignUp_Login.getText() != "" && textField_LastName_SignUp_Login.getText() != null
				&& textField_LastName_SignUp_Login.getText() != "" && textField_Phone_SignUp_Login.getText() != null
				&& textField_Phone_SignUp_Login.getText() != "" && textField_USer_SignUp_Login.getText() != null
				&& textField_USer_SignUp_Login.getText() != "" && textField_Password_SignUp_Login.getText() != null
				&& textField_Password_SignUp_Login.getText() != "";
	}

	@FXML
	void SignUp_Login(ActionEvent event) throws IOException {

		if (!checkInfomationSignUp()) {
			Notification.Error(Notification.Error, "Nhập đầy đủ thông tin");
			return;
		}

		Teacher teacher_SignUp = new Teacher(
				textField_USer_SignUp_Login.getText(),
				textField_FirstName_SignUp_Login.getText(),
				textField_LastName_SignUp_Login.getText(),
				textField_Phone_SignUp_Login.getText(),
				textField_Email_SignUp_Login.getText());

		Boolean create_Account = welcom_Login.signUp(teacher_SignUp, textField_Password_SignUp_Login.getText());

		if (!create_Account) {
			Notification.Error(Notification.Error, "Tạo tài khoản thất bại do");
			return;
		}
		Boolean signIn_Account = Notification
				.Comfrim(Notification.Default,
						"Chúc mừng, bạn đã tạo tài khoản thành công \n"
								+ "Bạn có muốn đăng nhập ngay bây giờ?")
				.getResult() == ButtonType.YES;

		if (signIn_Account)
			Sign_In_Tranfer(event);

	}

}
