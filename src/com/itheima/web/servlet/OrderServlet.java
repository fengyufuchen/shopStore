package com.itheima.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.web.domain.Cart;
import com.itheima.web.domain.CartItem;
import com.itheima.web.domain.Order;
import com.itheima.web.domain.OrderItem;
import com.itheima.web.domain.PageBean;
import com.itheima.web.domain.User;
import com.itheima.web.service.IOrderService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.UUIDUtils;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = (IOrderService) BeanFactory.getBean("OrderService");

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String add(HttpServletRequest request, HttpServletResponse response) {
		IOrderService orderService = (IOrderService) BeanFactory.getBean("OrderService");
		Order order = new Order();
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断用户是否已经登陆
		if (user != null && cart != null) {
			// 订单id，订单生成事件， 订单状态，订单总金额，订单list中cartItem
			order.setOid(UUIDUtils.getId());

			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			order.setOrderTime(date);

			order.setTotal(cart.getSumTotal());
			order.setUser(user);
			/*
			 * 遍历cart中的购物商品项 集合，然后遍历这个集合组装OrderItem
			 * 
			 */

			Collection<CartItem> listCartItem = cart.getItems();

			for (Entry<String, CartItem> entry : cart.getCartMap().entrySet()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(UUIDUtils.getId());
				orderItem.setCount(entry.getValue().getCount());
				orderItem.setProduct(entry.getValue().getProduct());
				orderItem.setSubTotal(entry.getValue().getSubtotal());
				orderItem.setOrder(order);
				order.getListOrderItem().add(orderItem);
			}
			// 调用service处理
			try {
				orderService.addOrder(order);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 显示订单
			request.setAttribute("order", order);
			// 清空购物车

			cart.clearCart();

		} else {
			// 重定向导登陆
			try {
				response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "/jsp/order_info.jsp";

	}

	public void findAllByPage(HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否已经登陆

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {

			String uid = user.getUid();

			// 获取当前页面，固定pageSize
			int currPage = Integer.valueOf(request.getParameter("currPage"));

			PageBean<Order> pageBean = new PageBean<>();
			try {
				pageBean = orderService.findAllByPage(uid, currPage, 4);
				System.out.println(pageBean.getList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 根据用户查询所有的订单，返回值为PageBean

			request.setAttribute("pageBean", pageBean);

			// 放入到request中，请求转发到orderList页面
			try {
				request.getRequestDispatcher("/jsp/order_list.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
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

}
