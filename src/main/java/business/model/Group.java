package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group {

	private String groupID;
	private Teacher teacher;
	private String groupName;
	private List<Student> students;
	
	public Group(String groupID, Teacher teacher, String groupName, List<Student> students) {
		this.groupID = groupID;
		this.teacher = teacher;
		this.groupName = groupName;
		this.students = students;
	}
	
	public Group(String groupID, Teacher teacher, String groupName) {
		this.groupID = groupID;
		this.teacher = teacher;
		this.groupName = groupName;
		this.students = new ArrayList<Student>();
	}
	
	public Group(ResultSet rs) throws SQLException {
		this.groupID = rs.getString("SGroupID");
		this.teacher = new Teacher(
				rs.getString("TeacherID"),
				rs.getString("FirstName"),
				rs.getString("LastName"),
				rs.getString("Phone"),
				rs.getString("Email")
				);
		this.groupName = rs.getString("SGroupName");
		this.students = null;
	}

	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
