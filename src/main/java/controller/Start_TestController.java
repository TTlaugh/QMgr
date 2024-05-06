package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import business.model.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;


public class Start_TestController implements Initializable{
	private Parent root = null;
	
	private static Exam exam_StartTest;
	
	  @FXML
	    private Label exam_ID_StartTest;

	public void Back_Test(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
		((Node) event.getSource()).getScene().setRoot(root); 
	}
	public void receiveExam(Exam exam)
	{	
		exam_StartTest=exam;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(exam_StartTest!=null)
			exam_ID_StartTest=new Label(exam_StartTest.getExamID().toString());
		
	}
    @FXML
    void button_StartTest(ActionEvent event) {
    
    }
}
