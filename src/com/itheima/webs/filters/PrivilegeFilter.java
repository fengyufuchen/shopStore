package com.itheima.webs.filters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
/**
 * @author lenovo 权限验证，一般需要一个url与权限角色的规则检查，规则检查一般存储在配置文件中或者数据库中
 *
 */
public class PrivilegeFilter implements Filter {

	private Properties pp = new Properties();

	/**
	 * Default constructor.
	 */
	public PrivilegeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		pp=null;
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

		String requestURL = htreq.getRequestURI().replace(htreq.getContextPath() + "/", "");
		String action = htreq.getParameter("action");
		// 获取action参数，例如add
		action = action == null ? "" : action;
		String uri = requestURL + "?action=" + action;
		// 拼接成URL，列如log.do?action=list
		String role = (String) htreq.getSession(true).getAttribute("role");
		role = role == null ? "guest" : role;
		
		boolean authentificated=false;
		for(Object obj:pp.keySet()){
			String key=(String) obj;
			if(uri.matches(key.replace("?", "\\?").replace(".", "\\.").replace("*", ".*"))){
				
				if(role.equals(pp.get(key))){
					authentificated=true;//通过验证，
					break;
				}
			}
		}
		if(!authentificated){
			/*throw new RuntimeException();*/
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String file = fConfig.getInitParameter("file");

		String realPath = fConfig.getServletContext().getRealPath(file);
		try {
			pp.load(new FileInputStream(realPath));// 加载所有权限配置
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
