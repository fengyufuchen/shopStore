package com.itheima.web.utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {

	public static void sendMail(String email, String emailMsg) throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		// 设置发送的协议
		props.setProperty("mail.transport.protocol", "SMTP");

		// 设置发送邮件的服务器
		props.setProperty("mail.host", "localhost");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// 设置发送人的帐号和密码
				return new PasswordAuthentication("service", "service");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		// 设置发送者
		message.setFrom(new InternetAddress("service@sachin.com"));

		// 设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email));

		// 设置邮件主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		// 设置邮件内容
		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}

	public static void sendMail402948352(String email, String emailMsg)
			throws GeneralSecurityException, AddressException, MessagingException {

		Properties props = new Properties();
		// 开启debug调试
		props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置服务器主机名称
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.transport.protocol", "smtp");

		MailSSLSocketFactory sfd = new MailSSLSocketFactory();
		sfd.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sfd);
		Session session = Session.getInstance(props);
		Message message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress("sachinonline@foxmail.com"));

		// 设置邮件主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		// 设置邮件内容
		message.setContent(emailMsg, "text/html;charset=utf-8");

		Transport transport = session.getTransport();
		transport.connect("smtp.qq.com", "402948352@qq.com", "第三方验证登陆码");
		transport.sendMessage(message, new Address[] { new InternetAddress(email) });

		transport.close();

	}
}
