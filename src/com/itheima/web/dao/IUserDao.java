package com.itheima.web.dao;

import java.sql.SQLException;

import com.itheima.web.domain.User;

public interface IUserDao {

	void add(User user) throws Exception;



	void activeUser(String activeCode) throws SQLException;



	User loginUser(String username, String password)throws SQLException;




}
