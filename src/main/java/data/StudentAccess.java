package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import business.model.Score;
import business.model.Student;
import utils.SQLUtils;

public class StudentAccess implements DataAccess<Student> {

	private Connection connection;

	@Override
	public boolean insert(Student student) throws SQLException {
		connection = SQLUtils.getConnection();
		PreparedStatement pStatement1 = connection.prepareStatement(
				"INSERT INTO Person VALUES (?,?,?,?,?)");
		pStatement1.setString(1, student.getPersonID());
		pStatement1.setString(2, student.getFirstName());
		pStatement1.setString(3, student.getLastName());
		pStatement1.setString(4, student.getPhone());
		pStatement1.setString(5, student.getEmail());
		boolean a = pStatement1.executeUpdate() >= 1;
		PreparedStatement pStatement2 = connection.prepareStatement(
				"INSERT INTO Students VALUES (?,?)");
		pStatement2.setString(1, student.getStudentID());
		pStatement2.setString(2, student.getPersonID());
		boolean b = pStatement2.executeUpdate() >= 1;
		SQLUtils.closeConnection(connection);
		return a && b;
	}

	@Override
	public boolean update(Student student) throws SQLException {
		connection = SQLUtils.getConnection();
		String personID = student.getPersonID();
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String phone = student.getPhone();
		String email = student.getEmail();
		String sql = "UPDATE Person SET"
				+ " FirstName = '" + firstName + "',"
				+ " LastName = '" + lastName + "',"
				+ " Phone = '" + phone + "',"
				+ " Email = '" + email + "'"
				+ " WHERE PersonID = '" + personID + "'";
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean delete(String... primaryKeyValues) throws SQLException {
		connection = SQLUtils.getConnection();
		boolean i = connection.createStatement().executeUpdate(
					"DELETE FROM Person WHERE PersonID = 'ST" + primaryKeyValues[0] + "'") >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public Student get(String... primaryKeyValues) throws SQLException {
		Student student = get(Student.class,
				"SELECT Students.StudentID, Person.* FROM Students"
				+ " INNER JOIN Person ON Students.PersonID = Person.PersonID",
				"Students.StudentID", primaryKeyValues[0]);
		student.setScores(getList(Score.class,
				"SELECT Exams.ExamID, Score, SubjectID FROM Submissions"
				+ " INNER JOIN Exams ON Submissions.ExamID = Exams.ExamID",
				"StudentID", student.getStudentID()));
		return student;
	}

}
