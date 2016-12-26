package com.itheima.webs.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet Filter implementation class LogFilter
 */
public class LogFilter implements Filter {
	private Log log=LogFactory.getLog(this.getClass());
	private String filterName;

	/**
	 * Default constructor.
	 */
	public LogFilter() {
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

		HttpServletRequest htreq=(HttpServletRequest) request;
		HttpServletResponse htresp=(HttpServletResponse) response;
		
		long startTime=System.currentTimeMillis();
		String requestURL=htreq.getRequestURI();
		requestURL=htreq.getQueryString()==null?requestURL:(requestURL+"?"+htreq.getQueryString());
		
		
				
		// pass the request along the filter chain
		chain.doFilter(request, response);
		long endTime=System.currentTimeMillis();
		/*log.info(htreq.getRemoteAddr()+"访问了"+requestURL+",总用时："+(endTime-startTime));*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterName=fConfig.getFilterName();//获取filter的名称
		log.info("启动fiilter:s"+filterName);
	}

}
