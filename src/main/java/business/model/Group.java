package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group {

	private String groupID;
	private Teacher teacher;
	private String groupName;
	
	public Group(String groupID, Teacher teacher, String groupName) {
		this.groupID = groupID;
		this.teacher = teacher;
		this.groupName = groupName;
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
	
}
