package business.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subjectID;
	private Teacher teacher;
	private String subjectName;
	
	public Subject(String subjectID, Teacher teacher, String subjectName) {
		this.subjectID = subjectID;
		this.teacher = teacher;
		this.subjectName = subjectName;
	}
	
	public Subject(ResultSet rs) throws SQLException {
		this.subjectID = rs.getString("SubjectID");
		this.teacher = null;
		this.subjectName = rs.getString("SubjectName");
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", teacher=" + teacher + ", subjectName=" + subjectName + "]";
	}
	
}
