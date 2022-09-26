package kr.co.triptrip.model.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private AccountRowMapper accMapper = new AccountRowMapper();
	private AccountRowMapperNoPW accMapperNoPW = new AccountRowMapperNoPW(); 
	
	// c:: sign Up logic // hashing f(x) use
	public Integer signUpByOwn(UserAccount user) throws SQLException {
		String sql = "insert into user (id, pw, name, salt) values (?, ?, ?, ?)";
		Integer result = jdbcTemplate.update(sql, user.getId(), user.getPw(), user.getName(), user.getSalt(), Integer.class);
		return result;
	}
	
	// r:: read // selectById
	public UserAccount userSelectById(String id) throws SQLException {
		String sql = "select * from triptrip.user where id = ?";
		UserAccount user = jdbcTemplate.queryForObject(sql, accMapper, id);
		return user;
	}
	
	// r:: read// selectById // NoPW
	public UserAccount userSelectByIdnoPw(Connection conn, String id) throws SQLException {
		String sql = "select * from triptrip.user where id = ?";
		UserAccount user = jdbcTemplate.queryForObject(sql, accMapperNoPW, id);
		return user;
	}
	
	//u:: update
	
	//d:: delete	
	
	
	public class AccountRowMapper implements RowMapper<UserAccount> {
		@Override
		public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String pw = rs.getString("pw");
			String salt = rs.getString("salt");
			return new UserAccount(id, name, pw, salt);
		}
	}
	
	public class AccountRowMapperNoPW implements RowMapper<UserAccount> {
		@Override
		public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			String id = rs.getString("id");
			String name = rs.getString("name");
			
			return new UserAccount(id, name);
		}
	}
}
