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

/**
 * Servlet Filter implementation class ImageRedirectFilter
 */
public class ImageRedirectFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ImageRedirectFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest htRequest=(HttpServletRequest) request;
		HttpServletResponse htResponse=(HttpServletResponse) response;
		
		String refer=htRequest.getHeader("referer");//链接来源地址
		if(refer==null ||!refer.contains(request.getServerName())){
			//若来自其他网站，显示错误图片
			request.getRequestDispatcher("/error.gif").forward(htRequest, htResponse);
			
		}else{
			chain.doFilter(request, response);
		}
		
		
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
