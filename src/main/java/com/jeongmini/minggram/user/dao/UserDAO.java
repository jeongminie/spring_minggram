package com.jeongmini.minggram.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jeongmini.minggram.user.model.User;

@Repository
public interface UserDAO {
	public int insertUser(
			@Param("name") String name,
			@Param("userName") String userName,
			@Param("password") String password,
			@Param("email") String email);

	public int countUserName(
			@Param("userName") String userName);
	
	public User selectUserByIdPassword(
			@Param("userName") String userName,
			@Param("password") String password);
}