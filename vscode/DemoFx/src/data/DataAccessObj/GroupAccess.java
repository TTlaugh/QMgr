package data.DataAccessObj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.DataTransferObj.Group;
import data.DataTransferObj.Student;
import utils.DataSourceFactory;

public class GroupAccess implements DataAccess<Group> {

	private Connection connection;
	
	public boolean addStudent(String groupID, String studentID) throws SQLException {
		connection = DataSourceFactory.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"INSERT INTO SGroupStudents VALUES ('"
						+ groupID + "', '"
						+ studentID + "')") >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean insert(Group group) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO SGroups VALUES (?,?,?)");
		pStatement.setString(1, group.getGroupID());
		pStatement.setString(2, group.getTeacher().getTeacherID());
		pStatement.setString(3, group.getGroupName());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Group group) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE SGroups SET"
				+ "SGroupID=?,"
				+ "TeacherID=?,"
				+ "SGroupName=?"
				+ "WHERE SGroupID=?");
		pStatement.setString(1, group.getTeacher().getTeacherID());
		pStatement.setString(2, group.getGroupName());
		pStatement.setString(3, group.getGroupID());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = DataSourceFactory.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM SGroups WHERE SGroupID = '" + primaryKeyValues[0] + "'") >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}
	
	public static void getStudents(Group group) throws SQLException {
		group.setStudents(DataAccess.getList(Student.class,
				"SELECT Students.StudentID, Person.* FROM SGroups"
				+ " INNER JOIN SGroupStudents ON SGroups.SGroupID = SGroupStudents.SGroupID"
				+ " INNER JOIN Students ON SGroupStudents.StudentID = Students.StudentID"
				+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
				"SGroups.SGroupID", group.getGroupID()));
	}

	@Override
	public Group getOnly(String... primaryKeyValues) throws SQLException {
		return DataAccess.get(Group.class,
				"SELECT * FROM SGroups"
				+ " INNER JOIN Teachers ON SGroups.TeacherID = Teachers.TeacherID",
				"SGroups.SGroupID", primaryKeyValues[0]);
	}

	@Override
	public List<Group> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM SGroups"
				+ " INNER JOIN Teachers ON SGroups.TeacherID = Teachers.TeacherID";
		return DataAccess.getList(Group.class, selectFrom, columnName_values);
	}

	@Override
	public List<Group> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM SGroups LIMIT " + offset + ", " + limit
				+ " INNER JOIN Teachers ON SGroups.TeacherID = Teachers.TeacherID";
		return DataAccess.getList(Group.class, selectFrom, columnName_values);
	}
	
}
