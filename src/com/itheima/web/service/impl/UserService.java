package com.itheima.web.service.impl;

import java.sql.SQLException;

import com.itheima.web.dao.IUserDao;
import com.itheima.web.dao.impl.UserDao;
import com.itheima.web.domain.User;
import com.itheima.web.service.IUserService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.MailUtils;

public class UserService implements IUserService {
	private IUserDao userDao = (IUserDao) BeanFactory.getBean("UserDao");

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Override
	public void regist(User user) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(user.getPassword());
		userDao.add(user);
		StringBuilder sb = new StringBuilder();
		String userURL = "http://localhost:8080/Store18s/user?method=active&code=" + user.getCode();
		sb.append("<div style=\"border:1px solid #CCC;background:#F4F4F4;width:100%;text-align:left\">");
		sb.append("<div style=\"font-size:14px;margin-bottom:20px;\">");
		sb.append("<h3 style=\"margin-left:30px;\">亲爱的" + user.getUsername() + "</h3>");
		sb.append("<p style=\"margin-left:30px;\">感谢注册 <a href=\"" + "http://localhost:8080/Stores18/"
				+ "\" target=\"_blank\">Sotre</a>，点击以下链接完成安全验证！</p>");
		sb.append("<a href=\"" + userURL + "\" style=\"margin-left:30px;\" target=\"_blank\">" + userURL + "</a></br>");
		sb.append("<p style=\"margin-left:30px;\">您的Email：<a href=\"" + user.getEmail() + "\" target=\"_blank\">"
				+ user.getEmail() + "</a></p>");
		sb.append("<br><br>");
		sb.append("<b style=\"margin-left:30px;font-size:14px;\">您的Email将会作为账户名来登录Store</b><br>");
		sb.append("<p style=\"margin-left:30px;\">本邮件为系统自动发送，请勿回复，感谢使用Store。</p>");

		sb.append("<p style=\"height:20px;border-top:1px #afb4db solid;margin:20px 20px 0;\">&nbsp;</p>");

		sb.append("<p style=\"margin-left:30px;\">Store</p>");
		sb.append("<a href=\"" + "http://localhost:8080/Store18s"
				+ "\" style=\"margin-left:30px;\" target=\"_blank\">https://www.shiyanlou.com</a>");
		sb.append("</div>");
		sb.append("</div>");

		String smg = "欢迎注册  <a href=\"http:localhost:8080/Store18s/user?method=active&code=" + user.getCode()
				+ "\">点击激活</a>";
		// 发送激活邮件
		MailUtils.sendMail(user.getEmail(), sb.toString());

	}

	// 通过激活码获取一个用户，用户可能为空，如果不为空，修改用户的state
	public void activeUser(String userActiveCode) throws SQLException {
		userDao.activeUser(userActiveCode);

	}

	@Override
	public User loginUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		 return userDao.loginUser(username,password);
		
	}

}
