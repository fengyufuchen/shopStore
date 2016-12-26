package com.itheima.webs.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import sun.security.krb5.Config;

public class EncodingFilter implements Filter {
	private String charcterEncoding;//编码方式，配置在webxml文件中
	private boolean enabled;//是否启用该filter，配置在webxml中

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		HttpServletRequest htreq=(HttpServletRequest) req;
		HttpServletResponse htresp=(HttpServletResponse) resp;
		if(enabled||charcterEncoding!=null){
			htreq.setCharacterEncoding(charcterEncoding);
			htresp.setCharacterEncoding(charcterEncoding);
		}
		
		fc.doFilter(htreq,htresp);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		charcterEncoding=config.getInitParameter("charcterEncoding");
		enabled="true".equalsIgnoreCase(config.getInitParameter("enabled").trim());//启用
		

	}

}
class MyRequest extends HttpServletRequestWrapper{

	public MyRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
}
