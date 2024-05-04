package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import business.model.Subject;
import business.model.Teacher;
import utils.SQLUtils;

public class SubjectAccess implements DataAccess<Subject> {

	private Connection connection;

	@Override
	public boolean insert(Subject subject) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO Subjects VALUES (?,?,?)");
		pStatement.setString(1, subject.getSubjectID());
		pStatement.setString(2, subject.getTeacher().getTeacherID());
		pStatement.setString(3, subject.getSubjectName());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Subject subject) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(
				"UPDATE Subjects SET"
						+ " SubjectID=?,"
						+ " TeacherID=?,"
						+ " SubjectName=?"
						+ " WHERE SubjectID=?");
		pStatement.setString(1, subject.getSubjectID());
		pStatement.setString(2, subject.getTeacher().getTeacherID());
		pStatement.setString(3, subject.getSubjectName());
		pStatement.setString(4, subject.getSubjectID());
		boolean i = pStatement.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
				"DELETE FROM Subjects WHERE SubjectID = '" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Subject get(String... primaryKeyValues) throws SQLException {
		return get(Subject.class,
				"SELECT * FROM Subjects"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID",
				"Subjects.SubjectID", primaryKeyValues[0]);
	}

	public List<Subject> getAll(String teacherID) throws SQLException {
		return getList(Subject.class,
				"SELECT * FROM Subjects"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID",
				"Teachers.TeacherID", teacherID);
	}

	public void getTeacher(Subject subject) throws SQLException {
		subject.setTeacher(get(Teacher.class,
				"SELECT Teachers.TeacherID, Person.* FROM Subjects"
				+ " INNER JOIN Teachers ON Subjects.TeacherID = Teachers.TeacherID"
				+ " INNER JOIN Person ON Teachers.PersonID = Person.PersonID",
				"Subjects.SubjectID", subject.getSubjectID()));
	}

}
