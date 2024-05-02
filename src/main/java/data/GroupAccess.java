package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import business.model.Group;
import business.model.Student;
import utils.SQLUtils;

public class GroupAccess implements DataAccess<Group> {

	private Connection connection;

	@Override
	public boolean insert(Group group) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO SGroups VALUES (?,?,?)");
		pStatement.setString(1, group.getGroupID());
		pStatement.setString(2, group.getTeacher().getTeacherID());
		pStatement.setString(3, group.getGroupName());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Group group) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE SGroups SET"
				+ " SGroupID=?,"
				+ " TeacherID=?,"
				+ " SGroupName=?"
				+ " WHERE SGroupID=?");
		pStatement.setString(1, group.getTeacher().getTeacherID());
		pStatement.setString(2, group.getGroupName());
		pStatement.setString(3, group.getGroupID());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM SGroups WHERE SGroupID = '" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Group get(String... primaryKeyValues) throws SQLException {
		return get(Group.class,
				"SELECT * FROM SGroups"
				+ " INNER JOIN Teachers ON SGroups.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"SGroups.SGroupID", primaryKeyValues[0]);
	}

	public List<Group> getAll(String teacherID) throws SQLException {
		return getList(Group.class,
				"SELECT * FROM SGroups"
				+ " INNER JOIN Teachers ON SGroups.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Teachers.TeacherID", teacherID);
	}

	public void getStudents(Group group) throws SQLException {
		group.setStudents(getList(Student.class,
				"SELECT Students.StudentID, Person.* FROM SGroups"
				+ " INNER JOIN SGroupStudents ON SGroups.SGroupID = SGroupStudents.SGroupID"
				+ " INNER JOIN Students ON SGroupStudents.StudentID = Students.StudentID"
				+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
				"SGroups.SGroupID", group.getGroupID()));
	}

}