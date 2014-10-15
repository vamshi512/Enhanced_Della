
package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnection {
public DBConnection() {
		// TODO Auto-generated constructor stub
		try {
			getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Connection con = null;
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Going to establish a connection");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.107:3306/enhanced_della","team5","team5");
			System.out.println("Connection Established....");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return con;
	}

}
