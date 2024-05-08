
package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

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
	
//	public static void main(String[] args) throws Exception {
//		testDataSource();
//	}
//	private static void testDataSource() throws Exception {
//		Exam exam = new ExamAccess().get("2024-05-08 09:00:00");
//		new ExamAccess().getQuestions(exam);
//		System.out.println(exam);
//		StartServer server = new StartServer(exam, 2000);
//		try(Scanner sc = new Scanner(System.in)) {
//			System.out.println("Input: ");
//			sc.nextLine();
//		}
//		server.shutdownServer();
//		System.out.println("Server already shutdown");
//	}
}
