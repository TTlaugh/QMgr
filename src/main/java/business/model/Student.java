package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends Person {

	private String studentID;
	private List<Score> scores;

	public Student(String personID, String firstName, String lastName, String phone, String email, String studentID,
			List<Score> scores) {
		super(personID, firstName, lastName, phone, email);
		this.studentID = studentID;
		this.scores = scores;
	}
	
	public Student() {
		super();
		this.studentID = null;
		this.scores = new ArrayList<>();
	}

	public Student(ResultSet rs) throws SQLException {
		super(
				rs.getString("PersonID"),
				rs.getString("FirstName"),
				rs.getString("LastName"),
				rs.getString("Phone"),
				rs.getString("Email"));
		this.studentID = rs.getString("StudentID");
		this.scores = new ArrayList<>();
	}
	public String getFirstName() {
		return super.getFirstName();
	}
	public String getLastName() {
		return super.getLastName();
	}
	public StringProperty getFullName() {
		StringProperty fullName = new SimpleStringProperty(super.getFirstName() + " " + super.getLastName());
		return fullName;
	}
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", scores=" + scores + "]";
	}

}
