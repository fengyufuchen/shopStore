package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.itheima.web.constant.Constant;
import com.itheima.web.domain.User;
import com.itheima.web.service.IProductService;
import com.itheima.web.service.IUserService;
import com.itheima.web.service.impl.UserService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.CustomerDateConvert;
import com.itheima.web.utils.MD5Utils;
import com.itheima.web.utils.UUIDUtils;

/**
 * @author lenovo 和用户相关的方法
 *
 */
@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet {
	IUserService userService = (UserService) BeanFactory.getBean("UserService");
	private static final long serialVersionUID = 1L;

	/**
	 * 必须是public修饰，返回值类型是string类型
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("add");
		return null;
	}

	public String registUI(HttpServletRequest req, HttpServletResponse resp) {
		// 生成注册页面对应的注册码

		return "jsp/register.jsp";
	}

	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String birthday = request.getParameter("birthday");//

		System.out.println(birthday);

		User user = new User();
		ConvertUtils.register(new CustomerDateConvert(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());// 注意BeanUtils不支持String转为时间类型，所以需要处理，使用自定义转换器
		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());// 设置激活码
		user.setPassword(MD5Utils.md5(user.getPassword()));

		userService.regist(user);

		request.setAttribute("msg", "注册成功,请前往邮箱激活！");

		return "/jsp/msg.jsp";
	}

	protected void setResponseHeader(HttpServletResponse resp) {
		resp.setContentType("image/" + cage.getFormat());// text/html text/xml
															// image/png
															// image/gif
		resp.setHeader("Cache-Control", "no-cache, no-store");
		resp.setHeader("Pragma", "no-cache");
		final long time = System.currentTimeMillis();
		resp.setDateHeader("Last-Modified", time);
		resp.setDateHeader("Date", time);
		resp.setDateHeader("Expires", time);
	}

	public String active(HttpServletRequest request, HttpServletResponse response) {
		try {
			userService.activeUser(request.getParameter("code"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "激活失败，请重新注册！");
			request.setAttribute("activeResult", "false");
			return "jsp/msg.jsp";

		}

		request.setAttribute("msg", "激活成功，3秒之后跳转到首页：");
		request.setAttribute("activeResult", "true");

		return "/jsp/msg.jsp";// 跳转到激活成功 jsp.msg
	}

	public String loginUI(HttpServletRequest request, HttpServletResponse response) {

		return "/jsp/login.jsp";// 跳转到激活成功 jsp.msg
	}

	public void login(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String clientAuthCode = request.getParameter("code");
		if (!request.getSession(true).getAttribute("captchaToken").equals(clientAuthCode)) {
			/*
			 * org.json.JSONObject json = new org.json.JSONObject(); try {
			 * json.put("authResult", "验证码输入有误！");
			 * response.getWriter().write(json.toString()); } catch (Exception
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 */

			try {
				request.setAttribute("loginResult", "验证码输入错误，请重新登陆");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		User user = null;
		try {
			user = userService.loginUser(username, MD5Utils.md5(password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (user == null) {
				request.setAttribute("loginResult", "用户名或密码输入错误，请重新登陆！");
				try {
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				} catch (ServletException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		}
		if (user == null) {
			request.setAttribute("loginResult", "用户名或密码输入错误，请重新登陆！");
			try {
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		} else {// 登陆成功
			if (Constant.USER_IS_ACTIVE == user.getState()) {

				request.getSession().setAttribute("user", user);

				// 注意这里将会转向到IndexServlet，所以我们需要将请求参数中的method值清空，或者使用重定向
				try {
					System.out.println(request.getServletContext().getContextPath() + "index.jsp");
					System.out.println(request.getContextPath() + "index.jsp");
					response.sendRedirect(request.getServletContext().getContextPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return;

			} else {
				// 用户没有激活
				request.setAttribute("loginResult", "您还没有激活，请激活或重新注册");
				try {
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public void logOut(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute("username", null);
		request.getSession().setAttribute("uid", null);
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
