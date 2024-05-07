package business.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mysql.cj.exceptions.MysqlErrorNumbers;

import business.model.Group;
import business.model.Score;
import business.model.Student;
import business.model.Teacher;
import data.GroupAccess;
import data.GroupStudentAccess;
import data.StudentAccess;
import utils.ExcelReader;
import utils.ExcelWriter;
import utils.SQLUtils;

public class GroupManager {

	public List<Group> getGroups(Teacher teacher) {
		try {
			return new GroupAccess().getAll(teacher.getTeacherID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

	public boolean addGroup(Group newGroup) throws SQLException {
		try {
			return new GroupAccess().insert(newGroup);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("GroupID: '"+newGroup.getGroupID()+"' already exists", e);
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

	public List<Student> getStudentsFromGroup(Group group) {
		try {
			return new GroupAccess().getStudents(group);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	public List<Student> searchStudentsFromGroup(Group group, String keyword) {
		try {
			return new GroupAccess().searchStudents(group, keyword);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addStudent(Group group, Student student) throws SQLException {
		try {
			return new StudentAccess().insert(student) &&
			new GroupStudentAccess().addStudent(group.getGroupID(), student.getStudentID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("StudentID: '"+student.getStudentID()+"' already exists", e);
		}
		return false;
	}

	public boolean addStudentToGroup(String groupID, String studentID) throws SQLException {
		try {
			return new GroupStudentAccess().addStudent(groupID, studentID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
			if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
				throw new SQLException("StudentID: '"+studentID+"' already exists in Group(ID): '"+groupID+"'", e);
		}
		return false;
	}
	
	public boolean removeStudentFromGroup(Group group, Student student) {
		try {
			new GroupStudentAccess().removeStudent(group.getGroupID(), student.getStudentID());
			if (new GroupStudentAccess().countClassesOfStudent(student.getStudentID()) == 0)
				return new StudentAccess().delete(student.getStudentID());
			return true;
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

	public boolean importStudent(Group group, String excelFilePath, boolean useExist) throws IOException {
		boolean stat = true;
		List<Student> students = new StudentExcelReader().readExcel(excelFilePath);
		for (Student student : students) {
			try {
				addStudent(group, student);
			} catch (SQLException e) {
				SQLUtils.printSQLException(e);
				if (useExist) {
					try {
						addStudentToGroup(group.getGroupID(), student.getStudentID());
					} catch (SQLException e1) {
						SQLUtils.printSQLException(e1);
						stat = false;
					}
				}
				else stat = false;
			}
		}
		return stat;
	}

	public boolean exportStudent(Group group, List<Student> students, String excelFilePath) {
		if (students == null || students.isEmpty())
			return false;
		try {
			new StudentExcelWriter().writeExcel(
					group.getGroupID() + " | " + group.getGroupName(),
					students,
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
		cell = row.createCell(0); cell.setCellStyle(cellStyle); cell.setCellValue("ID");
		cell = row.createCell(1); cell.setCellStyle(cellStyle); cell.setCellValue("First Name");
		cell = row.createCell(2); cell.setCellStyle(cellStyle); cell.setCellValue("Last Name");
		cell = row.createCell(3); cell.setCellStyle(cellStyle); cell.setCellValue("Phone");
		cell = row.createCell(4); cell.setCellStyle(cellStyle); cell.setCellValue("Email");
		cell = row.createCell(5); cell.setCellStyle(cellStyle); cell.setCellValue("Scores");
	}

	@Override
	public <T> void writeData(T data, Row row) {
		Student student = (Student) data;
		Cell cell = null; int colIndex = 0;
		cell = row.createCell(colIndex++); cell.setCellValue(student.getStudentID());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getFirstName());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getLastName());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getPhone());
		cell = row.createCell(colIndex++); cell.setCellValue(student.getEmail());
		for (Score score : student.getScores()) {
			cell = row.createCell(colIndex++); cell.setCellValue(score.getScore());
		}
	}
}
