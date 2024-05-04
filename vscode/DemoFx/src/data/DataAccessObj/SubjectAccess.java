package data.DataAccessObj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.DataTransferObj.Subject;
import data.DataTransferObj.Teacher;
import utils.DataSourceFactory;

public class SubjectAccess implements DataAccess<Subject> {

	private Connection connection;

	@Override
	public boolean insert(Subject subject) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO Subjects VALUES (?,?,?)");
		pStatement.setString(1, subject.getSubjectID());
		pStatement.setString(2, subject.getTeacher().getTeacherID());
		pStatement.setString(3, subject.getSubjectName());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Subject subject) throws SQLException {
		connection = DataSourceFactory.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Subjects SET"
						+ "SubjectID=?,"
						+ "TeacherID=?,"
						+ "SubjectName=?"
						+ "WHERE SubjectID=?");
		pStatement.setString(1, subject.getSubjectID());
		pStatement.setString(2, subject.getTeacher().getTeacherID());
		pStatement.setString(3, subject.getSubjectName());
		pStatement.setString(4, subject.getSubjectID());
		boolean i = pStatement.executeUpdate() >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = DataSourceFactory.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Subjects WHERE SubjectID = '" + primaryKeyValues[0] + "'") >= 1;
		DataSourceFactory.closeConnection(connection);
		return i;
	}
	
	public static void getTeacher(Subject subject) throws SQLException {
		subject.setTeacher(DataAccess.get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Subjects"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Subjects.SubjectID", subject.getSubjectID()));
	}

	@Override
	public Subject getOnly(String... primaryKeyValues) throws SQLException {
		return DataAccess.get(Subject.class,
				"SELECT * FROM Subjects"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID",
				"Subjects.SubjectID", primaryKeyValues[0]);
	}

	@Override
	public List<Subject> getAll(String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Subjects"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID";
		return DataAccess.getList(Subject.class, selectFrom, columnName_values);
	}

	@Override
	public List<Subject> getLimit(int offset, int limit, String... columnName_values) throws SQLException {
		String selectFrom = "SELECT * FROM Subjects LIMIT " + offset + ", " + limit
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID";
		return DataAccess.getList(Subject.class, selectFrom, columnName_values);
	}

}
