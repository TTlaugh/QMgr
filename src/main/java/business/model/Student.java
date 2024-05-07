package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

	private static final long serialVersionUID = 1L;
	private String studentID;
	private List<Score> scores;
	
	public Student(String studentID, String firstName, String lastName, String phone, String email,
			List<Score> scores) {
		super("ST"+studentID, firstName, lastName, phone, email);
		this.studentID = studentID;
		this.scores = scores;
	}

	public Student(String studentID, String firstName, String lastName, String phone, String email) {
		super("ST"+studentID, firstName, lastName, phone, email);
		this.studentID = studentID;
		this.scores = null;
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
				rs.getString("Email")
				);
		this.studentID = rs.getString("StudentID");
		this.scores = new ArrayList<>();
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
