package kr.co.triptrip.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.istack.internal.NotNull;

import jdbc.JDBCListener;
import user.model.UserAccount;

public class UserDao {
	 
	private UserAccount resultMapping(ResultSet rs) throws SQLException {
		String id = rs.getString("id");
		String name = rs.getString("name");
		String pw = rs.getString("pw");
		String salt = rs.getString("salt");
		return new UserAccount(id, name, pw, salt);
	}
	
	private UserAccount resultMappingNoPw(ResultSet rs) throws SQLException {
		String id = rs.getString("id");
		String name = rs.getString("name");
		
		return new UserAccount(id, name);
	}
	
	
	// c:: sign Up logic // hasing f(x) use
	public int signUpByOwn(Connection conn, UserAccount user) throws SQLException {
		String sql = "insert into user (id, pw, name, salt) values (?, ?, ?, ?)";
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getSalt());
			
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	// r:: read // selectById
	public UserAccount userSelectById(Connection conn, String id) throws SQLException {
		String sql = "select * from triptrip.user where id = ?";
		ResultSet rs = null;
		UserAccount user = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = resultMapping(rs);
			} 
			return user;
		} finally {
			if (rs != null) {
				JDBCListener.closeRs(rs);
			}
		}
	}
	
	public UserAccount userSelectByIdnoPw(Connection conn, String id) throws SQLException {
		String sql = "select * from triptrip.user where id = ?";
		ResultSet rs = null;
		UserAccount user = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = resultMappingNoPw(rs);
			} 
			return user;
		} finally {
			if (rs != null) {
				JDBCListener.closeRs(rs);
			}
		}
	}
	
	//u:: update
	
	//d:: delete	
}
