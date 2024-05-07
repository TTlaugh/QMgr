package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Teacher extends Person {

	private static final long serialVersionUID = 1L;
	private String teacherID;
	
	public Teacher(String teacherID, String firstName, String lastName, String phone, String email) {
		super("TC"+teacherID, firstName, lastName, phone, email);
		this.teacherID = teacherID;
	}

	public Teacher(ResultSet rs) throws SQLException {
		super(
				rs.getString("PersonID"),
				rs.getString("FirstName"),
				rs.getString("LastName"),
				rs.getString("Phone"),
				rs.getString("Email")
				);
		this.teacherID = rs.getString("TeacherID");
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	@Override
	public String toString() {
		return "Teacher [teacherID=" + teacherID + "]";
	}

}
