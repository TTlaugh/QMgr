package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceFactory {
	
	public static DataSource getDataSource() {
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(Constant.MySQLProperties.URL);
		mysqlDS.setUser(Constant.MySQLProperties.USERNAME);
		mysqlDS.setPassword(Constant.MySQLProperties.PASSWORD);
		mysqlDS.setDatabaseName("QuizzServer");
		return mysqlDS;
	}
	public static void main(String[] args) {

		testDataSource();
		System.out.println("**********");

	}

	private static void testDataSource() {
		DataSource ds = DataSourceFactory.getDataSource();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			rs = con.createStatement().executeQuery("SELECT * FROM Person");
			while(rs.next()){
				System.out.println(
						rs.getString("PersonID")+", "+
						rs.getString("FirstName")+", "+
						rs.getString("LastName")+", "+
						rs.getString("Email")+", "+
						rs.getString("Phone")+", "
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null) rs.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}