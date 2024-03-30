package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class ExamManagerController implements Initializable{
	
	@FXML
	private TableView<Test> table;
	@FXML
	private TableColumn<Test, String> idCol;
	@FXML
	private TableColumn<Test, String> subjectCol;
	@FXML
	private TableColumn<Test, String> dateCol;
	@FXML
	private TableColumn<Test, String> timeCol;
	@FXML
	private TableColumn<Test, String> nameCol;
	
	private ObservableList<Test> testList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		testList = FXCollections.observableArrayList(
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("123456789", "Phan tich thiet ke he thong thong tin", "31/12/2020", 120, "Bai kiem tra giua ki hehe"),
				new Test("12345678", "Co so du lieu", "31/12/2020 22:22", 120, "Thi rot roi")
				);
		idCol.setCellValueFactory(new PropertyValueFactory<Test, String>("id"));
		idCol.setCellFactory(TextFieldTableCell.forTableColumn());
		subjectCol.setCellValueFactory(new PropertyValueFactory<Test, String>("subject"));
		subjectCol.setCellFactory(TextFieldTableCell.forTableColumn());
		dateCol.setCellValueFactory(new PropertyValueFactory<Test, String>("date"));
//		dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		timeCol.setCellValueFactory(new PropertyValueFactory<Test, String>("time"));
//		timeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setCellValueFactory(new PropertyValueFactory<Test, String>("name"));
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		table.setItems(testList);
	}

	public void backToMain(ActionEvent event) throws IOException {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
		((Node) event.getSource()).getScene().setRoot(root);;
	}

}
