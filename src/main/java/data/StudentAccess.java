package data;

import java.sql.Connection;
import java.sql.SQLException;

import business.model.Score;
import business.model.Student;
import utils.SQLUtils;

public class StudentAccess implements DataAccess<Student> {

	private Connection connection;

	@Override
	public boolean insert(Student student) throws SQLException {
		connection = SQLUtils.getConnection();
		String id = student.getStudentID();
		String personID = "ST" + id;
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String phone = student.getPhone();
		String email = student.getEmail();
		String sql = "INSERT INTO Person VALUES ('"
				+ personID + "','"
				+ firstName + "','"
				+ lastName + "','"
				+ phone + "','"
				+ email + "');"
				+ "INSERT INTO Students VALUES ('"
				+ id + "','"
				+ personID + "');";
		boolean i = connection.createStatement().executeUpdate(sql) >= 1;
		SQLUtils.closeConnection(connection);
		return i;
	}

	@Override
	public boolean update(Student student) throws SQLException {
		connection = SQLUtils.getConnection();
		String personID = "ST" + student.getStudentID();
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String phone = student.getPhone();
		String email = student.getEmail();
		String sql = "UPDATE Person SET "
				+ "FirstName = '" + firstName + "', "
				+ "LastName = '" + lastName + "', "
				+ "Phone = '" + phone + "', "
				+ "Email = '" + email + "' "
				+ "WHERE PersonID = '" + personID + "'";
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
				"SELECT ExamID, Score FROM Submissions",
				"StudentID", student.getStudentID()));
		return student;
	}

}
