package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.web.domain.Cart;
import com.itheima.web.domain.CartItem;
import com.itheima.web.domain.User;
import com.itheima.web.service.IProductService;
import com.itheima.web.utils.BeanFactory;

/**
 * Servlet implementation class CartServlet
 */
/**
 * @author lenovo 购物车模块
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	IProductService productService = (IProductService) BeanFactory.getBean("ProductService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 我们是在productInfo.jsp页面中填写订单购买信息，然后提交购买之后，服务器接收到这个购买信息CartItem，然后将数据放入到session中的cart对象中。
	 * 那么为什么要放置到session中呢？
	 * 原因是当我们在productInfo.jsp页面中提交之后，需要重定向到订单页面，也就是需要使用cart.jsp页面显示当前所有的购买信息，那么显然这里需要使用重定向到cart.jsp页面
	 * 在cart.jsp页面中需要显示用于提交的购买信息，这个购买信息该如何获取呢？
	 * 虽然用于在ProductInfo页面提交请求的时候会带有购买信息，但是页面重定向到cart.jsp 之后request中就已经不存在这些购买信息了
	 * 所以使用session是为了解决 跨请求（重定向）共享数据，从而可以在cart.jsp中使用展示数据
	 * 
	 * @param request
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession(true).getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		return cart;
	}

	public void addToCart(HttpServletRequest request, HttpServletResponse response) {

		String pid = request.getParameter("pid");
		String quantity = request.getParameter("quantity");

		CartItem cartItem = new CartItem();
		cartItem.setCount(Integer.parseInt(quantity));
		try {
			cartItem.setProduct(productService.getByPid(pid));

			Cart cart = getCart(request);
			cart.addToCar(cartItem);

			response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void remove(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.removeFromCart(pid);
		}
		try {
			request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clearCart(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.clearCart();
		}
		try {
			request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void goToCart(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			
			try {
				response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			try {
				response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] arg) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("deleResult", "OK");
			System.out.println(jsonObject.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
