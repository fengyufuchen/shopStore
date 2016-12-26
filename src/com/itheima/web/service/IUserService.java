package com.itheima.web.service;

import java.sql.SQLException;

import com.itheima.web.domain.User;

public interface IUserService {

	void regist(User user) throws Exception;
	
	
	void activeUser(String activeCode) throws Exception;


	User loginUser(String username, String password) throws SQLException;

}
