
package jp.co.ca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.ca.entity.User;

public class UserDaoImpl implements UserDao {

	// SQLServer用
//	private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ca";
//	private static final String ID = "sa";
//	private static final String PASS = "sa";
	
	// Oracle用
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String DB_URL = "jdbc:oracle:thin:@dbserver:1521:xe";
//	private static final String ID = "sys";
//	private static final String PASS = "orcl";
	
	// MySQL用
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/ca";
	private static final String ID = "root";
	private static final String PASS = "";
	
	// PostgreSQL用
//	private static final String DRIVER_NAME = "org.postgresql.Driver";
//	private static final String DB_URL = "jdbc:postgresql:ca";
//	private static final String ID = "postgres";
//	private static final String PASS = "postgres";
	
	private static final String DELETE = "DELETE FROM user WHERE id=?";
	private static final String FIND_ALL = "SELECT * FROM user ORDER BY id";
	private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
	private static final String FIND_BY_NAME = "SELECT * FROM user WHERE name=?";
	private static final String INSERT = "INSERT INTO user(name, tel, passwd) VALUES(?, ?, ?)";
	private static final String UPDATE = "UPDATE user SET name=?, tel=?, passwd=? WHERE id=?";
	
	
	public int delete(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, id);
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	public List<User> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<User> list = new ArrayList<User>();
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_ALL);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setPasswd(rs.getString("passwd"));
				
				list.add(user);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
		
		return list;
	}
	
	public User findById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_BY_ID);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setPasswd(rs.getString("passwd"));
				
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	public User findByName(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_BY_NAME);
			stmt.setString(1, name);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setPasswd(rs.getString("passwd"));
				
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	public int insert(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getTel());
			stmt.setString(3, user.getPasswd());
			
			int result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
			
			return result;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	public int update(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getTel());
			stmt.setString(3, user.getPasswd());
			stmt.setInt(4, user.getId());
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}
	
	/**
	 * caデータベースにアクセスするコネクションを取得します。
	 *
	 * @return DBコネクション
	 */
	private Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(DB_URL, ID, PASS);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 開いているDBコネクションを閉じます。
	 * 
	 * @param con コネクション
	 */
	private static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 開いているステートメントを閉じます。
	 * 
	 * @param stmt ステートメント
	 */
	private static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
