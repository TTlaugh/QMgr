package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject {

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
	public StringProperty getSubjectNameStringProperty() {
		StringProperty name = new SimpleStringProperty(getSubjectName());
		return name;
	}

	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", teacher=" + teacher + ", subjectName=" + subjectName + "]";
	}

}
