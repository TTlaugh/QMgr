package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import data.GroupDAO;
import data.StudentDAO;
import model.Group;
import model.Student;
import utils.CheckStringFormat;
import utils.ExcelReader;
import utils.ExcelWriter;
import utils.Notification;
import utils.StringNormalization;
import utils.StringValidate;

public class GroupManager {
    private static GroupManager instance = null;

    public static GroupManager getInstance() {
        if (instance == null) {
            instance = new GroupManager();
        }
        return instance;
    }

    private GroupManager() {
    }

    public ArrayList<Group> getAllGroupInWorkSpace(int workspaceID) {
        return new GroupDAO().getAllGroupInWorkSpace(workspaceID);
    }

    public ArrayList<Group> getAllGroup() {
        return new GroupDAO().getAll();
    }

    public boolean createGroup(Group group) {
        return new GroupDAO().create(group);
    }

    public boolean updateGroup(Group group) {
        return new GroupDAO().update(group);
    }

    public boolean deleteGroup(int id) {
        return new GroupDAO().delete(id);
    }

    public Group getGroupByID(int id) {
        return new GroupDAO().getByID(id);
    }
    
    public boolean exportStudents(Group grp, String excelFilePath) {
        int grpID = grp.getGroupId();
		try {
			new StudentExcelWriter().writeExcel(
                    grpID + " | " + grp.getGroupName(),
					new StudentDAO().getAllByGroupId(grpID),
					excelFilePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

    public boolean importStudents(Group group, String excelFilePath) throws SQLException {
        StudentManager studentManager = new StudentManager();
		try {
			List<Student> students = new StudentExcelReader().readExcel(excelFilePath);
			for (Student student : students) {
                String firstName = student.getFirstName();
                String lastName = student.getLastName();
                String studentID = student.getStudentId();
                String email = student.getEmail();
                String phone = student.getPhone();

                if (studentID.length() == 0 || studentID == null || studentID == "") {
                    Notification.Error("Error", "Can import student " + firstName + " because ID is empty.");
                    continue;
                }
                if (!CheckStringFormat.isCorrectFormat(studentID)) {
                    Notification.Error("Error", "Can import student " + studentID + " because Student ID is not correct format.");
                    continue;
                }
                Student student_inGroup = studentManager.getStudentbyIdfromGroup(studentID, group.getGroupId());

                if (student_inGroup != null) {
                    Notification.Error("Error", "Student ID #" + studentID + " existed in the group, please try again.");
                    continue;
                }

                if (firstName.length() == 0 || firstName == null || firstName == "") {
                    Notification.Error("Error", "Can import student " + studentID + " because First Name is empty.");
                    continue;
                }
                firstName = StringNormalization.removeDuplicateSpaces(firstName);
                if(!StringValidate.CheckLengthInRange(firstName, 2, 50)){
                    Notification.Error("Error", "Can import student " + studentID + " because First Name out of range 2 to 50 characters.");
                    continue;
                }

                if (lastName.length() == 0 || lastName == null || lastName == "") {
                    Notification.Error("Error", "Can import student " + studentID + " because Last Name is empty.");
                    continue;
                }
                lastName = StringNormalization.removeDuplicateSpaces(lastName);
                if(!StringValidate.CheckLengthInRange(lastName, 2, 50)){
                    Notification.Error("Error", "Can import student " + studentID + " because Last Name out of range 2 to 50 characters.");
                    continue;
                }

                if (email.length() == 0 || email == null || email == "") {
                    Notification.Error("Error", "Can import student " + studentID + " because Email is empty.");
                    continue;
                }
                if(!StringValidate.CheckEmailValid(email)){
                    Notification.Error("Error", "Can import student " + studentID + " because Email is not valid.");
                    continue;
                }
                if(!StringValidate.CheckLengthInRange(email, 5, 255)){
                    Notification.Error("Error", "Can import student " + studentID + " because Email out of range 5 to 255 characters.");
                    continue;
                }

                if (phone.length() == 0 || phone == null || phone == "") {
                    Notification.Error("Error", "Can import student " + studentID + " because Phone is empty.");
                    continue;
                }
                phone = StringNormalization.convertToPhoneNumber(phone);
                if(!StringValidate.CheckPhoneValid(phone)){
                    Notification.Error("Error", "Can import student " + studentID + " because Phone number is not valid.");
                    continue;
                }

                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setPhone(phone);
                student.setEmail(email);
				student.setGroupId(group.getGroupId());
				new StudentDAO().create(student);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

class StudentExcelWriter extends ExcelWriter {
	@Override
	public void writeHeader(Sheet sheet, int rowIndex) {
		CellStyle cellStyle = createStyleForHeader(sheet);
		Row row = sheet.createRow(rowIndex);
		Cell cell = null;
		cell = row.createCell(0); cell.setCellStyle(cellStyle); cell.setCellValue("ID");
		cell = row.createCell(1); cell.setCellStyle(cellStyle); cell.setCellValue("First Name");
		cell = row.createCell(2); cell.setCellStyle(cellStyle); cell.setCellValue("Last Name");
		cell = row.createCell(3); cell.setCellStyle(cellStyle); cell.setCellValue("Phone");
		cell = row.createCell(4); cell.setCellStyle(cellStyle); cell.setCellValue("Email");
	}

	@Override
	public <T> void writeData(T data, Row row) {
		Student student = (Student) data;
		Cell cell = null; int colIndex = 0;
		cell = row.createCell(colIndex++); cell.setCellValue(student.getStudentId());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getFirstName());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getLastName());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getPhone());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getEmail());
	}
}

class StudentExcelReader extends ExcelReader {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getData(Row row) {
		Student student = new Student();
        int colIndex = 0;
        student.setStudentId(row.getCell(colIndex++).getStringCellValue());
        student.setFirstName(row.getCell(colIndex++).getStringCellValue());
        student.setLastName(row.getCell(colIndex++).getStringCellValue());
        student.setPhone(row.getCell(colIndex++).getStringCellValue());
        student.setEmail(row.getCell(colIndex++).getStringCellValue());
		return (T) student;
	}
}