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
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/enhanced_della","root","root");
			System.out.println("Connection Established....");
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return con;
	}

}
