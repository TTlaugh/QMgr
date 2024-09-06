package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import business.services.StartClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import utils.DisplayDialog_Notification;

public class Student_Start implements Initializable{
	private Parent root = null;
	
	public static StartClient start_Client;

    @FXML
    private TextField fullName_Start=new TextField();

    @FXML
    private TextField ip_Start=new TextField();

    @FXML
    private TextField port_Start=new TextField();

    @FXML
    public TextField student_ID_Start=new TextField();
    
    public static String id;

    @FXML
    void Start_Exam_Minequizz(ActionEvent event) throws IOException {
		try {
    	id=student_ID_Start.getText();
		String name=fullName_Start.getText();
		String ip=ip_Start.getText();
		int port=Integer.valueOf(port_Start.getText());
		
		if(id!=null && id !="" &&
				name!=null && name !="" &&
				ip!=null && ip !="") {
			start_Client = new StartClient(id,name,ip,port);
			
			boolean check =true;
			while(start_Client.getExam() ==null)
			{
				if(check)
				{
					if(DisplayDialog_Notification.Dialog_Comfrim("Waitting", "hehhe", ip).getResult() == ButtonType.YES)
					 check=false;					
				}	
				
			}
				root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Student_Exam.fxml"));
				((Node) event.getSource()).getScene().setRoot(root);		
		}
		else 
			DisplayDialog_Notification.Dialog_Error("hehhe", "nhap day du thong tin", ip);
		}catch(Exception e) {
			DisplayDialog_Notification.Dialog_Error("", e.getMessage(), null);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		port_Start.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	port_Start.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
}
