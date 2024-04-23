package main.java.business.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

import main.java.business.model.Group;
import main.java.business.model.Score;
import main.java.business.model.Student;
import main.java.data.GroupAccess;
import main.java.data.GroupStudentAccess;
import main.java.data.StudentAccess;
import main.java.utils.ExcelReader;
import main.java.utils.ExcelWriter;
import main.java.utils.SQLUtils;

public class GroupManager {

	// choose a Group
	public List<Group> getGroups() {
		try {
			return new GroupAccess().getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

	// new Group
	public boolean btnaddGroup(Group newGroup) {
		return true;
	}

	public boolean addGroup(Group newGroup) throws SQLException {
		try {
			return new GroupAccess().insert(newGroup);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("GroupID: '" + newGroup.getGroupID() + "' already exists", e);
		}
		return false;
	}

	public boolean editGroup(Group newGroup) {
		try {
			return new GroupAccess().update(newGroup);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public boolean deleteGroup(String groupID) {
		try {
			return new GroupAccess().delete(groupID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public void getStudentsForGroup(Group group) {
		try {
			new GroupAccess().getStudents(group);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
	}

	public boolean btnaddStudentToGroup(Group group, Student student) throws SQLException {
		return true;
	}

	public boolean addStudentToGroup(Group group, Student student) throws SQLException {

		try {
			new StudentAccess().insert(student);
			new GroupStudentAccess().addStudent(group.getGroupID(), student.getStudentID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("StudentID: '" + student.getStudentID() + "' already exists", e);
		}
		return false;
	}

	public boolean removeStudentFromGroup(Group group, Student student) {
		try {
			new GroupStudentAccess().removeStudent(group.getGroupID(), student.getStudentID());
			if (new GroupStudentAccess().countClassesOfStudent(student.getStudentID()) == 0)
				new StudentAccess().delete(student.getStudentID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public boolean editStudent(Student student) {
		try {
			return new StudentAccess().update(student);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;

	}

	// public List<Score> getStudentScores(Student student) {
	// }

	public boolean importStudent(Group group, String excelFilePath) throws SQLException {

		try {
			List<Student> students = new StudentExcelReader().readExcel(excelFilePath);
			for (Student student : students) {
				addStudentToGroup(group, student);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean exportStudent(Group group, String excelFilePath) {
		if (group.getStudents() == null || group.getStudents().isEmpty())
			return false;
		try {
			new StudentExcelWriter().writeExcel(
					group.getGroupID() + " | " + group.getGroupName(),
					group.getStudents(),
					excelFilePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}

class StudentExcelReader extends ExcelReader {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getData(Row row) {
		Student student = new Student();
		for (Cell cell : row) {
			Object cellValue = getCellValue(cell);
			if (cellValue == null || cellValue.toString().isEmpty())
				continue;
			int columnIndex = cell.getColumnIndex();
			switch (columnIndex) {
				case 0:
					student.setStudentID((String) getCellValue(cell));
					break;
				case 1:
					student.setFirstName((String) getCellValue(cell));
					break;
				case 2:
					student.setLastName((String) getCellValue(cell));
					break;
				case 3:
					student.setPhone((String) getCellValue(cell));
					break;
				case 4:
					student.setEmail((String) getCellValue(cell));
					break;
				default:
					break;
			}
		}
		return (T) student;
	}
}

class StudentExcelWriter extends ExcelWriter {
	@Override
	public void writeHeader(Sheet sheet, int rowIndex) {
		CellStyle cellStyle = createStyleForHeader(sheet);
		Row row = sheet.createRow(rowIndex);
		Cell cell = null;
		cell = row.createCell(0);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("ID");
		cell = row.createCell(1);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("First Name");
		cell = row.createCell(2);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Last Name");
		cell = row.createCell(3);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Phone");
		cell = row.createCell(4);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Email");
		cell = row.createCell(5);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Scores");
	}

	@Override
	public <T> void writeData(T data, Row row) {
		Student student = (Student) data;
		Cell cell = null;
		int colIndex = 0;
		cell = row.createCell(colIndex++);
		cell.setCellValue(student.getStudentID());
		cell = row.createCell(colIndex++);
		cell.setCellValue(student.getFirstName());
		cell = row.createCell(colIndex++);
		cell.setCellValue(student.getLastName());
		cell = row.createCell(colIndex++);
		cell.setCellValue(student.getPhone());
		cell = row.createCell(colIndex++);
		cell.setCellValue(student.getEmail());
		for (Score score : student.getScores()) {
			cell = row.createCell(colIndex++);
			cell.setCellValue(score.getScore());
		}
	}
}