package kr.co.triptrip.model.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.triptrip.exception.LoginFailExcepion;
import util.SHA256Util;

@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	public UserAccount login(String id, String pw) throws SQLException {

		UserAccount user = userDao.userSelectById(id);

		if (user == null) {
			throw new LoginFailExcepion();
		}
		pw = SHA256Util.getEncrypt(pw, user.getSalt());

		if (!user.matchPassword(pw)) {
			throw new LoginFailExcepion();
		}
		
		return new UserAccount(user.getId(), user.getName());

	}
}
