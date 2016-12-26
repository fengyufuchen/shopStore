package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.cage.Cage;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Cage cage = new Cage();

	/**
	 * Default constructor.
	 */
	public BaseServlet() {
		// TODO Auto-generated constructor stub
	}

	/** generates a captcha token and stores it in the session
	 * @param session
	 */
	public static void generateToke(HttpSession session) {
		final String token = cage.getTokenGenerator().next();
		session.setAttribute("captchaToken", token);
		markTokenUsed(session, false);
	}

	/**Marks token as used/unused for image generation.
	 * @param session
	 * @param used
	 */
	protected static void markTokenUsed(HttpSession session, boolean used) {
		session.setAttribute("captchaTokenUsed", used);
	}

	/** used to retrieve previously store captcha token from session
	 * @param session
	 * @return
	 */
	public static String getToken(HttpSession session) {
		final Object val = session.getAttribute("captchaToken");
		return val != null ? val.toString() : null;

	}
	/** Checks if the token was used/unused for image generation
	 * @param session
	 * @return
	 */
	protected static boolean isTokenUsed(HttpSession session) {
		return !Boolean.FALSE.equals(session.getAttribute("captchaTokenUsed"));
	}

	/*
	 * (non-Javadoc) 原本在HttpServlet中的service方法中实现区别请求的分类，get
	 * post等，现在我们重写了Service方法这一样就不需要进行分类，而是直接根据请求参数对方法进行调用。
	 * 那么为什么可以访问到userServlet呢？原因是当我们通过在访问的url路径中指定了要访问UserServlet，
	 * 然后Tomact本身会根据路径进行解析并转发给指定的serlvet，这一点是框架本身实现的。
	 */
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 1 锟斤拷取锟斤拷锟斤拷
		Class classz = this.getClass();

		// 2锟斤拷取锟斤拷锟斤拷姆锟斤拷锟�
		String methodName = request.getParameter("method");
		if (methodName == null) {
			methodName = "index";
		}
		System.out.println("被调用的方法名称是：" + methodName);
		// 3锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷
		try {
			Method method = classz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 4 锟矫凤拷锟斤拷执锟斤拷
			String strPath = (String) method.invoke(this, request, response);
			// 5 锟叫讹拷strPath锟角凤拷为锟斤拷
			if (strPath != null) {
				request.getRequestDispatcher(strPath).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			/* throw new RuntimeException(e); */
		}

	}

	public String index(HttpServletRequest request, HttpServletResponse respone) {
		return null;

	}
}
