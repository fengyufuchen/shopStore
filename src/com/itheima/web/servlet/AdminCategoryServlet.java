package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itheima.web.domain.Category;
import com.itheima.web.service.impl.CategoryService;
import com.itheima.web.utils.BeanFactory;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Category> listCategory = null;
		try {
			listCategory = cs.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", listCategory);

		return "/admin/category/list.jsp";

	}

	public void addCategory(HttpServletRequest request, HttpServletResponse response) {
		String categoryName = request.getParameter("categoryname");
		Map<String, String[]> paramMap = request.getParameterMap();
		try {
			cs.addCategory(categoryName);
			List<Category> listCategory = cs.findAll();
			JSONArray jsonArray = new JSONArray();

			for (Category cg : listCategory) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cname", cg.getCname());
				jsonObject.put("cid", cg.getCid());
				jsonArray.put(jsonObject);

			}
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCategory(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("categoryname");
		try {
			cs.updateCategory(cid, cname);

			List<Category> listCategory = cs.findAll();
			JSONArray jsonArray = new JSONArray();

			for (Category cg : listCategory) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cname", cg.getCname());
				jsonObject.put("cid", cg.getCid());
				jsonArray.put(jsonObject);

			}
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
	}

	public void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");

		try {
			cs.deleteCategory(cid);

			List<Category> listCategory = cs.findAll();
			JSONArray jsonArray = new JSONArray();

			for (Category cg : listCategory) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cname", cg.getCname());
				jsonObject.put("cid", cg.getCid());
				jsonArray.put(jsonObject);

			}
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getAllCategory(HttpServletRequest request, HttpServletResponse response) {
		List<Category> listCategory;
		try {
			listCategory = cs.findAll();
			JSONArray jsonArray = new JSONArray();

			for (Category cg : listCategory) {

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cname", cg.getCname());
				jsonObject.put("cid", cg.getCid());
				jsonArray.put(jsonObject);

			}
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
