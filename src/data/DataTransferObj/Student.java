package data.DataTransferObj;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends Person {

	private String studentID;
	
	public Student(String personID, String firstName, String lastName, String email, String phone, String studentID) {
		super(personID, firstName, lastName, email, phone);
		this.studentID = studentID;
	}
	
	public Student(ResultSet rs) throws SQLException {
		super(
				rs.getString("PersonID"),
				rs.getString("FirstName"),
				rs.getString("LastName"),
				rs.getString("Email"),
				rs.getString("Phone")
				);
		this.studentID = rs.getString("StudentID");
	}

	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
}
