package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import business.model.Exam;
import business.model.Question;
import business.model.Student;
import business.model.Subject;
import business.services.WelcomeFunction;
import data.ExamAccess;
import data.StudentAccess;

public class SQLUtils {

	public static DataSource getDataSource() {
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(Constant.MySQLProperties.URL);
		mysqlDS.setUser(Constant.MySQLProperties.USERNAME);
		mysqlDS.setPassword(Constant.MySQLProperties.PASSWORD);
		mysqlDS.setDatabaseName("QuizzServer");
		return mysqlDS;
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = getDataSource().getConnection();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		System.out.println("Releasing all open resources ...");
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException)e).getSQLState());
				System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
				System.err.println("Message: " + e.getMessage()); 
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		testDataSource();
	}
	private static void testDataSource() {
		Exam exam = new Exam(
				new DateTime("2024-01-01 18:00:00"),
				null,
				null,
				0,
				0,
				null,
				null,
				false,
				null
				);
		try {
			new ExamAccess().getQuestions(exam);
		} catch (SQLException e) { }
		System.out.println(exam);
	}
}
