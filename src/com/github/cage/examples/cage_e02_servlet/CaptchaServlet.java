/*
 * Copyright 2011 Király Attila
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cage.examples.cage_e02_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.github.cage.Cage;
import com.github.cage.GCage;







/**
 * An example servlet that generates captcha images directly to the response
 * stream.
 * 
 * @author akiraly
 * 
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1490947492185481844L;

	private static final Cage cage = new GCage();

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	public static void generateToken(HttpSession session) {
		System.out.println("generateToken");
		final String token = cage.getTokenGenerator().next();

		session.setAttribute("captchaToken", token);
		markTokenUsed(session, false);
	}

	public static String getToken(HttpSession session) {
		final Object val = session.getAttribute("captchaToken");

		return val != null ? val.toString() : null;
	}

	protected static void markTokenUsed(HttpSession session, boolean used) {
		session.setAttribute("captchaTokenUsed", used);
	}

	protected static boolean isTokenUsed(HttpSession session) {
		return !Boolean.FALSE.equals(session.getAttribute("captchaTokenUsed"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(" captchaServlet doGet");
		System.out.println(req.getParameter("method"));
		if(req.getParameter("method")!=null){
			checkClientAuthCode(req,resp);
		}else{
			final HttpSession session = req.getSession(true);
			CaptchaServlet.generateToken(session);
			final String token = session != null ? getToken(session) : null;
			System.out.println("token:" + token);
			/*
			 * if (token == null || isTokenUsed(session)) {
			 * resp.sendError(HttpServletResponse.SC_NOT_FOUND,
			 * "Captcha not found.");
			 * 
			 * return ; }
			 */

			setResponseHeaders(resp);
			markTokenUsed(session, true);
			cage.draw(token, resp.getOutputStream());
		}
	}

	public void checkClientAuthCode(HttpServletRequest request, HttpServletResponse response)   {

		String clientAuthCode = request.getParameter("clientAuthCode");
		if (clientAuthCode != null) {
			org.json.JSONObject json = new org.json.JSONObject();
			String captchaToken = (String) request.getSession().getAttribute("captchaToken");
			try {
				if (captchaToken.equals(clientAuthCode)) {

					json.put("authResult", "验证码输入正确！");

				} else {
					json.put("authResult", "验证码输入错误！");
				}
				response.getWriter().write(json.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

	protected void setResponseHeaders(HttpServletResponse resp) {
		/*
		 * cache-control:no-cache :
		 * 除非资源进行了再验证，否则这个客户端不会接收已经缓存的资源,Progma:no-cache同样也是这个意思，
		 * 只不过Progma存在的意义是为了支持http10 Cache-control： no-store
		 * 缓存应该尽快从存储器中删除文档的所有痕迹，因为其中可能会包含敏感信息
		 * 
		 */
		resp.setContentType("image/" + cage.getFormat());
		resp.setHeader("Cache-Control", "no-cache, no-store");
		resp.setHeader("Pragma", "no-cache");
		final long time = System.currentTimeMillis();
		resp.setDateHeader("Last-Modified", time);
		resp.setDateHeader("Date", time);
		resp.setDateHeader("Expires", time);
	}
}
