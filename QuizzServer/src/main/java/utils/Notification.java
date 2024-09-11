package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Notification {
	public static String Unsuccessfully = "Thông báo thất bại";
	public static String Successfully = "Thông báo thành công";
	public static String Error = "Thông báo lỗi";
	public static String Success_DeleteStudent = "Học sinh đã được xóa";
	public static String Unsuccess_DeleteStudent = "Học sinh không được xóa";
	public static String Success_AddStudent = "Học sinh được thêm";
	public static String Unsuccess_AddStudent = "Học sinh không được thêm";
	public static String Success_AddGroup = "Nhóm đã được thêm";
	public static String Unsuccess_AddGroup = "Nhóm không được thêm bởi vì nhóm đã tồn tại";
	public static String Success_ExelFile = "File Excel tạo thành công nhấn đồng ý để mở file";
	public static String Unsuccess_ExelFile = "=File Excel không được tạo ";
	public static String Unsuccess_ImportFile = "Bạn chưa nhập tệp tin";
	public static String Success_ImportFile = "Nhập tệp tin thành công";
	public static String SameData_Override = "File dữ liệu có khả năng trung với hệ thống bạn có muốn dùng thông tin hệ thống có sẵn nếu gặp trong trường hợp này";
	public static String SameData_NotOverride = "Danh sách từ Excel không thể nhập toàn bộ do phát hiện ID sinh viên trùng lặp với hệ thống dữ liệu, vui lòng kiểm tra và bổ sung các công cụ sinh viên này";
	public static String FullFill = "Điền đầy đủ thông tin";
	public static String Default = "Thông báo";
	public static String notFound = "Không tìm thấy";
	public static String notQuestion = "Không có câu hỏi";
	public static String piskToTest = "Bạn đã chọn bài kiểm tra này hãy nhấn bắt đầu để phát bài kiểm tra";
	public static String sameQuestion = "Câu hỏi đã có trong danh sách";
	public static String selectQuestion = "Mời bạn chọn câu hỏi";
	public static String selectQuestion_Delete = "Một chọn cấu hỏi để xóa";
	public static String Success_DeleteExam = "Bài kiểm tra đã bị xóa";
	public static String Unsuccess_DeleteExam = "Bài kiểm tra không bị xóa";

	public static void Error(String title, String content) {
		Alert typeAlert = new Alert(AlertType.ERROR);
		Button okButton = new Button("Cancel");
		okButton.setOnAction(event -> {
			typeAlert.hide();
		});
		typeAlert.setContentText(content);
		// typeAlert.setHeaderText(Successfully);
		typeAlert.setTitle(title);
		typeAlert.showAndWait();
	}

	public static Alert Comfrim(String title, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.CANCEL);
		alert.setContentText(content);
		alert.setHeaderText("Thông báo");
		alert.setTitle(title);
		alert.showAndWait();
		return alert;
	}

	public static void Infomation(String title, String content) {
		Alert typeAlert = new Alert(AlertType.INFORMATION);
		Button okButton = new Button("Cancel");
		okButton.setOnAction(event -> {
			typeAlert.hide();
		});
		typeAlert.setContentText(content);
		// typeAlert.setHeaderText(Successfully);
		typeAlert.setTitle(title);
		typeAlert.showAndWait();
	}
}
