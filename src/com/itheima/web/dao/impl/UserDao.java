package com.itheima.web.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.web.dao.IUserDao;
import com.itheima.web.domain.User;
import com.itheima.web.utils.DataSourceUtils;

public class UserDao implements IUserDao {

	@Override
	public void add(User user) throws SQLException {
		// TODO Auto-generated method stub

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());

		/*
		 * String sql="insert into user values(?,?,?,?,?,?,?,?,?,?);";
		 * qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
		 * user.getName(),user.getEmail(),user.getTelephone(),
		 * user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		 */

	}

	@SuppressWarnings("unchecked")
	@Override
	public void activeUser(String activeCode) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select uid from user where code=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		@SuppressWarnings("deprecation")
		User user = qr.query(sql, new BeanHandler<User>(User.class), activeCode);
		if (user != null) {
			System.out.println("查找到用户");
			String sql2 = "update  user set state=1, code=null where uid=?";

			qr.update(sql2, new String[] { user.getUid() });

		} else {
			System.out.println("为查找到用户");
		}

	}

	public User loginUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		
	  String sql="select * from user where username=? and password=?";
	  
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		@SuppressWarnings("deprecation")
		User user = qr.query(sql, new String[]{username,password},new BeanHandler<User>(User.class));
		return user;
	}

}
