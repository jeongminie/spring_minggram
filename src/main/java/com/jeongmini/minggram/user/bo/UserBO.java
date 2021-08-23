package com.jeongmini.minggram.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeongmini.minggram.common.EncryptUtils;
import com.jeongmini.minggram.user.dao.UserDAO;
import com.jeongmini.minggram.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int signUp(String name, String userName, String password, String eamil) {
		String encryptPassword = EncryptUtils.md5(password);
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUp] 암호화 실패");
			return 0;
		}
		
		return userDAO.insertUser(name, userName, encryptPassword, eamil);
	}
	
	public boolean existUserName(String userName) {
		if(userDAO.countUserName(userName) == 0) {
			return false; //중복아님
		} else {
			return true;
		}
		
		//return userDAO.countUserName(userName) != 0;
	}
	
	public User signIn(String userName, String password) {
		String encryptPassword = EncryptUtils.md5(password);
		return userDAO.selectUserByIdPassword(userName, encryptPassword);
	}
}
