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
    
    private WelcomeFunction welcom_Login=new WelcomeFunction();

	public void Sign_Up_Tranfer(ActionEvent event) throws IOException {
		(Anchor_SignIn).setVisible(false);
		(Anchor_Regester).setVisible(true);
	}

	public void Sign_In_Tranfer(ActionEvent event) throws IOException {
		(Anchor_Regester).setVisible(false);
		(Anchor_SignIn).setVisible(true);
	}

	public void Sign_In(ActionEvent event) throws IOException {
		try {
			teacher_Current =welcom_Login.signIn(
					textField_User_Login.getText().trim(),
					textField_Password_Login.getText().trim());
			
			if(textField_User_Login.getText()==null || 
					textField_User_Login.getText()=="" ||
					textField_Password_Login.getText()==null ||
					textField_Password_Login.getText()=="")
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("\r\n"
		        		+ "Notice filled in completely");
		        alert.setHeaderText("Please enter complete information");
		        alert.setContentText("You need to enter complete information");

		        alert.showAndWait();
			}
			else {
				if(teacher_Current!=null) {
					root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
					((Node) event.getSource()).getScene().setRoot(root);
				}
				else {
					 Alert alert = new Alert(Alert.AlertType.ERROR);
				        alert.setTitle("Sign In Failed");
				        alert.setHeaderText("Not found user");
				        alert.setContentText("\r\n"
				        		+ "Username or password is incorrect.");
				        // Hiển thị hộp thoại
				        alert.showAndWait();
				}
			}
			}
		catch(Exception e) {
			System.out.println("Error"+e);
		}
		
	}
	@FXML
    void SignUp_Login(ActionEvent event) {
		if(		textField_Email_SignUp_Login.getText() !=null &&
				textField_Email_SignUp_Login.getText() !="" && 
				textField_FirstName_SignUp_Login.getText() !=null &&
				textField_FirstName_SignUp_Login.getText() !="" &&
				textField_LastName_SignUp_Login.getText() !=null &&
				textField_LastName_SignUp_Login.getText() !="" &&
				textField_Phone_SignUp_Login.getText() !=null &&
				textField_Phone_SignUp_Login.getText() !="" &&
				textField_USer_SignUp_Login.getText() !=null &&
				textField_USer_SignUp_Login.getText() !="" &&
				textField_Password_SignUp_Login.getText() !=null &&
				textField_Password_SignUp_Login.getText() !=""
				) {
			Teacher teacher_SignUp=new Teacher(textField_USer_SignUp_Login.getText(),
					null,
					textField_FirstName_SignUp_Login.getText(),
					textField_LastName_SignUp_Login.getText(),
					textField_Phone_SignUp_Login.getText(),
					textField_Email_SignUp_Login.getText());
			if(welcom_Login.signUp(teacher_SignUp,textField_Password_SignUp_Login.getText())) {
				 Alert alert = new Alert(Alert.AlertType.INFORMATION);
			        alert.setTitle("Sign Up Success");
			        alert.setHeaderText("Congratulations, you have successfully registered");
			        alert.setContentText("Do you want to switch to the login page now?");

			        ButtonType confirmButtonType = new ButtonType("OK");
			        ButtonType cancelButtonType = new ButtonType("Cancel");
			        alert.getButtonTypes().addAll(confirmButtonType, cancelButtonType);
			        if(alert.showAndWait().get() == confirmButtonType) {
			        	(Anchor_Regester).setVisible(false);
			        	(Anchor_SignIn).setVisible(true);
			        }
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("\r\n"
	        		+ "Notice filled in completely");
	        alert.setHeaderText("Please enter complete information");
	        alert.setContentText("You need to enter complete information");

	        alert.showAndWait();
		}
    }

}
