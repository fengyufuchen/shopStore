package com.itheima.webs.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.itheima.web.exception.AccountException;

/**
 * Servlet Filter implementation class ExceptionHandlerFilter
 */
/**
 * @author lenovo 异常捕捉filter
 * 
 */
public class ExceptionHandlerFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ExceptionHandlerFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		

		try {
			// pass the request along the filter chain
			chain.doFilter(request, response);// dofilter执行下一个FIlter或者servlet，如果抛出了异常，那么会回溯到这里
		} catch (Exception e) {
			e.printStackTrace();
			

			Throwable rootCause = e;
			while (rootCause.getCause() != null) {
				// 循环，知道找到根异常为止
				rootCause = rootCause.getCause();
			}
			String message = rootCause.getMessage();
			message = message == null ? "异常：" + rootCause.getClass().getName() : message;
			System.out.println(message);
			// request传递异常原因

			request.setAttribute("execeptionmessage", message);
			request.setAttribute("e", e);// request 中传递异常
			
			if(rootCause instanceof AccountException){
				//如果是AccountException异常
				request.getRequestDispatcher("/jsp/accountException.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/500.jsp").forward(request, response);
			}

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
