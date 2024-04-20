package services;

import java.sql.SQLException;
import java.util.List;

import data.DataAccessObj.GroupAccess;
import data.DataAccessObj.GroupStudentAccess;
import data.DataAccessObj.StudentAccess;
import data.DataAccessObj.SubmissionAccess;
import data.DataTransferObj.Group;
import data.DataTransferObj.Score;
import data.DataTransferObj.Student;
import utils.SQLUtils;

public class GroupManager {

	public List<Group> getGroups() {
		try {
			return new GroupAccess().getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

	public boolean addGroup(Group newGroup) {
		try {
			return new GroupAccess().insert(newGroup);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
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
	
	public boolean addStudentToGroup(Group group, Student student) {
		try {
			new StudentAccess().insert(student);
			new GroupStudentAccess().addStudent(group.getGroupID(), student.getStudentID());
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
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
	
	public List<Score> getStudentScores(Student student) {
		try {
			return new SubmissionAccess().getStudentScores(student);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

}
