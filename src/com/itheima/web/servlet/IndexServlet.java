package com.itheima.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itheima.web.domain.Category;
import com.itheima.web.domain.Product;
import com.itheima.web.service.ICategoryService;
import com.itheima.web.service.IProductService;
import com.itheima.web.utils.BeanFactory;

/*
 * 和首页相关的servlet
 */
@WebServlet("/index")
public class IndexServlet extends BaseServlet {
	ICategoryService categoryService = (ICategoryService) BeanFactory.getBean("CategoryService");
	IProductService productService = (IProductService) BeanFactory.getBean("ProductService");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String index(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		// 去数据库中查询最新商品和热门商品，将他们放入request域中，然后进行请求转发
		// 对于首页，首页需要显示一些数据，这些数据是变化的，需要从数据库中查询得到，所以实现的逻辑就是首先当我们访问首页的时候，在index.jsp中使用forward指令定位到这个
		// servlet,然后在这个servlet中实现从数据库中查询的业务逻辑，然后将检索到的数据放入到 真真的 index.jsp 页面
		// 当我们将jsp页面放置到web-info路径下之后 可以通过请求转发的方式实现对页面的访问
		// 将返回值放入request中
		/*
		 * try { List<Category> categoryList = categoryService.findAll();
		 * req.setAttribute("categoryList", categoryList); } catch (Exception e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		try {
			List<Product> newList = productService.findNew();
			List<Product> hotList = productService.findHot();

			if (newList == null) {
				newList = new ArrayList<Product>();

			}
			if (hotList == null) {
				hotList = new ArrayList<Product>();
			}
			req.setAttribute("newList", newList);
			req.setAttribute("hotList", hotList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/jsp/index.jsp";
	}

	public void getCategory(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		// 去数据库中查询最新商品和热门商品，将他们放入request域中，然后进行请求转发
		// 对于首页，首页需要显示一些数据，这些数据是变化的，需要从数据库中查询得到，所以实现的逻辑就是首先当我们访问首页的时候，在index.jsp中使用forward指令定位到这个
		// servlet,然后在这个servlet中实现从数据库中查询的业务逻辑，然后将检索到的数据放入到 真真的 index.jsp 页面
		// 当我们将jsp页面放置到web-info路径下之后 可以通过请求转发的方式实现对页面的访问
		// 将返回值放入request中
		try {
			List<Category> categoryList = categoryService.findAll();
			String categoryStr = "";
			for (Category item : categoryList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cname", item.getCname());
				jsonObject.put("cid", item.getCid());
				categoryStr += jsonObject.toString() + ",";
			}
			JSONArray ara = new JSONArray(categoryList);
			if (categoryStr.length() > 0) {
				categoryStr = categoryStr.substring(0, categoryStr.length() - 1);
			}
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(categoryStr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

    System.out.println(BeanFactory.getBean("ProductService"));
	
	}
}
