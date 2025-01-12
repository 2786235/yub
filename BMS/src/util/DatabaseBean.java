package util;

import java.sql.*;

public class DatabaseBean {
	public static Connection getConnection() {
		Connection conn = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/slm?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "111111");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
