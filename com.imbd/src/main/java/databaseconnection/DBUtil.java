package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import com.page.Imdb;

public class DBUtil {

	static Connection conn = null;

	public static Connection getDBConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "mom@1432");
		} catch (Exception e) {
		}
		return conn;
	}

}
