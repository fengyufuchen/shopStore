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
 * Servlet Filter implementation class WaterMarkFilter
 */
public class WaterMarkFilter implements Filter {
	private String waterMarkFile;//水印图片的路径，配置在初始化参数中

    /**
     * Default constructor. 
     */
    public WaterMarkFilter() {
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
		
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		WaterMarkResponseWrapper waterMarkRes=new WaterMarkResponseWrapper(httpResponse,waterMarkFile);
		
		
	
		// pass the request along the filter chain
		chain.doFilter(request, waterMarkRes);
		waterMarkRes.finishResponse();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		//获取水印文件的位置
		String  file=fConfig.getInitParameter("waterMarkFile");
		waterMarkFile=fConfig.getServletContext().getRealPath(file);
	}

}
