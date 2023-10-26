package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private Connection connection;
	private static 	MyConnection instance;
	
	public MyConnection() {
		String url ="jdbc:sqlserver://localhost:1433;databaseName=QLLaoDong";
		try {
			connection = DriverManager.getConnection(url,"sa","sa");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static MyConnection getInstance() {
		if (instance==null) {
			instance = new MyConnection();
		}
		return instance;
	}
	 public Connection getConnection() {
		return connection;
	}
	 public void disconnect() {
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
