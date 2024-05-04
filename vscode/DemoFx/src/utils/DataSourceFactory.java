package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import data.DataAccessObj.ExamAccess;
import data.DataTransferObj.Exam;
import data.DataTransferObj.Submission;

public class DataSourceFactory {

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
			SQLUtils.printSQLException(e);
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
			SQLUtils.printSQLException(e);
		}
	}

	public static void main(String[] args) {

		testDataSource();

	}

	private static void testDataSource() {
		List<Exam> list = null;
		try {
			ExamAccess examAccess = new ExamAccess();
			list = examAccess.getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		System.out.println(list.toString());
	}

}