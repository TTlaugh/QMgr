package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.SQLUtils;

public class GroupStudentAccess {

	private Connection connection;

	public boolean addStudent(String groupID, String studentID) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"INSERT INTO SGroupStudents VALUES ('"
						+ groupID + "', '"
						+ studentID + "')") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}
	
	public boolean removeStudent(String groupID, String studentID) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM SGroupStudents WHERE SGroupID = '"
						+ groupID + "' AND StudentID = '"
						+ studentID + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}
	
	public int countClassesOfStudent(String studentID) throws SQLException {
		connection = SQLUtils.getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT COUNT(*) FROM SGroupStudents WHERE StudentID = '" + studentID + "'");
		int count = rs.next() ? rs.getInt(1) : 0;
		SQLUtils.closeConnection(connection);
		return count;
	}

}
