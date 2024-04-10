package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import data.DTO.Subject;
import utils.DataSourceFactory;

public class SubjectDAO implements DAO<Subject> {

	private Connection connection;

	public SubjectDAO() {
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Subject subject) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"INSERT INTO Subjects VALUES (?,?,?)");
			pStatement.setString(1, subject.getSubjectID());
			pStatement.setString(2, subject.getTeacher().getTeacherID());
			pStatement.setString(3, subject.getSubjectName());
			if (pStatement.executeUpdate() >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Subject subject) {
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"UPDATE Subjects SET"
					+ "SubjectID=?,"
					+ "TeacherID=?,"
					+ "SubjectName=?"
					+ "WHERE SubjectID=?"
					);
			pStatement.setString(1, subject.getSubjectID());
			pStatement.setString(2, subject.getTeacher().getTeacherID());
			pStatement.setString(3, subject.getSubjectName());
			pStatement.setString(4, subject.getSubjectID());
			if (pStatement.executeUpdate() >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		try {
			if (connection.createStatement().executeUpdate(
					"DELETE FROM Subjects WHERE SubjectID = '" + id + "'") >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Subject> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Subject> get(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
