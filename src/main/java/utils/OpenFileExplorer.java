package utils;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

public class OpenFileExplorer {
    public static File Open(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File ");
		File file = fileChooser.showOpenDialog(null);
		
		if(file != null)
			return file;
		return null ;
    }
    
    public static File Save(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File ");
		File file = fileChooser.showSaveDialog(null);
		
		if(file != null)
			return file;
		return null ;
    }
}
