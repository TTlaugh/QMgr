package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import business.model.Exam;
import business.services.StartServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.DisplayDialog_Notification;



public class Start_TestController implements Initializable{
	private Parent root = null;
	
	private static Exam exam_StartTest;
	
	@FXML
	private Label exam_ID_StartTest=new Label();
	
	@FXML
    private Label ip_Address_StartTest=new Label();
	
    @FXML
    private TextField port_StartTest=new TextField();
    
    StartServer startServer;


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
		exam_ID_StartTest.setText(exam_StartTest.getExamID().toString());
		ip_Address_StartTest.setText(StartServer.getIPAddress());
		
		port_StartTest.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	port_StartTest.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	
		
	}
    @FXML
    void button_StartTest(ActionEvent event) throws IOException {
    	if(port_StartTest.getText()!=null && port_StartTest.getText()!="") {
    		int port =Integer.parseInt(port_StartTest.getText());
    		try {
    			startServer=new StartServer(exam_StartTest,port);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			DisplayDialog_Notification.Dialog_Error("Error", e.getMessage(),"Error");
    		}    		
    	}
    	else {
    		DisplayDialog_Notification.Dialog_Error("Error", "Port is null","Error");
    	}
    }
}
