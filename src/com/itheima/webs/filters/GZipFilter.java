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
 * Servlet Filter implementation class GZipFilter
 */
/**
 * @author lenovo GZip压缩filter，使用GZip压缩算法对网页内容进行压缩，然后传给浏览器，以便减小数据传输质量
 */
public class GZipFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public GZipFilter() {
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
		HttpServletRequest htreq = (HttpServletRequest) request;
		HttpServletResponse htresp = (HttpServletResponse) response;
		String acceptEncoding = htreq.getHeader("Accept-Encoding");

		if (acceptEncoding != null && acceptEncoding.toLowerCase().indexOf("gzip") != -1) {
			// 如果支持gzip编码
			GZipResponseWrapper gzipRes = new GZipResponseWrapper(htresp);
			chain.doFilter(request, response);
			gzipRes.finishResponse();

		} else

			// pass the request along the filter chain
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
