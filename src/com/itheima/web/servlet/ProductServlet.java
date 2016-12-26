package com.itheima.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.itheima.web.domain.Category;
import com.itheima.web.domain.PageBean;
import com.itheima.web.domain.Product;
import com.itheima.web.service.IProductService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.CookUtils;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	IProductService productService = (IProductService) BeanFactory.getBean("ProductService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getById(HttpServletRequest request, HttpServletResponse response) {
		String productId = request.getParameter("pid");
		
		Cookie cookie = CookUtils.getCookieByName("scanPids", request.getCookies());
		if (cookie == null) {
			cookie = new Cookie("scanPids", request.getParameter("pid"));
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie.setPath(request.getContextPath() + "/");
		

		} else {
			// cookie不为空
			String scanPids = cookie.getValue();
			String[] pids = scanPids.split("-");
			List<String> listPids = new ArrayList<String>();
			for (String t : pids) {
				listPids.add(t);
			}

			if (listPids.contains(productId)) {
				listPids.remove(productId);
				listPids.add(0, productId);
			} else {
				// 如果不包含这个商品的 的id，那么就判断商品的id数量是不是已经为3个了
				if (listPids.size() == 3) {
					listPids.remove(2);

				}
				listPids.add(0, productId);

			}

			String listResult = org.apache.commons.lang3.StringUtils.join(listPids.toArray(), "-");
			System.out.println("写回cookie信息：" + listResult);
			cookie.setValue(listResult);
		}
		response.addCookie(cookie);

		Product product = null;
		try {
			product = productService.getByPid(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("product", product);
		request.setAttribute("productServlet", this);

		return "/jsp/productinfo.jsp";

	}

	public String findByPage(HttpServletRequest request, HttpServletResponse response) {

		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currPage");
		int pageSize = 12;

		try {
			PageBean<Product> pageBean = productService.findByPage(cid, currentPage, pageSize);
			System.out.println(pageBean);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("productServlet", this);
			return "/jsp/product_list.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void findScanProduct(String pids, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("findScanProduct");
		String[] pidArray = pids.split("-");
		Product product = null;
		List<Product> listProduct = new ArrayList<Product>();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < pidArray.length; i++) {
			try {
				product = productService.getByPid(pidArray[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (product != null) {
				jsonArray.put(product.toJSONObject());
			}
		}
		

		try {
			response.getWriter().write(new JSONArray(listProduct).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Product findScanProductByProductId(String pid) {
		
		try {
			Product product= productService.getByPid(pid);
			System.out.println(product.getPimage());
			return product;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] arg) {
		Product product = new Product();
		product.setCategory(new Category("name", "67"));
		product.setPdesc("商品啊");

		Product product2 = new Product();
		product2.setPdesc("商品3");
		product2.setCategory(new Category("name2", "01"));
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(product.toJSONObject());
		jsonArray.put(product2.toJSONObject());
		System.out.println(jsonArray.toString());

	}

}
