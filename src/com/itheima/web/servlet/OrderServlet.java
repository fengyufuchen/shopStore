package com.itheima.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.ResourceBundle;

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
import com.itheima.web.service.impl.OrderService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.PaymentUtil;
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

	/**查看订单详情
	 * @param request
	 * @param response
	 */
	public void findOrderById(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {

		}
		String oid = request.getParameter("oid");
		Order order = null;

		try {
			order = orderService.findOrderById(oid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (order != null) {
			request.setAttribute("order", order);
			try {
				request.getRequestDispatcher("/jsp/order_info.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				request.setAttribute("msg", "出错了~");
				request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
		} else {

			try {
				response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public String pay(HttpServletRequest request,HttpServletResponse respone) throws Exception{
		//接受参数
		String address=request.getParameter("address");
		String name=request.getParameter("name");
		String telephone=request.getParameter("telephone");
		String oid=request.getParameter("oid");
		
		
		//通过id获取order
		IOrderService s=(IOrderService) BeanFactory.getBean("OrderService");
		Order order = s.findOrderById(oid);
		
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		
		//更新order
		s.update(order);
		

		// 组织发送支付公司需要哪些数据
		String pd_FrpId = request.getParameter("pd_FrpId");
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = oid;
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
	
		
		//发送给第三方
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		respone.sendRedirect(sb.toString());
		
		return null;
	}
	
	public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 响应数据有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				System.out.println("111");
				request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
				
			} else if (r9_BType.equals("2")) {
				// 服务器点对点 --- 支付公司通知你
				System.out.println("付款成功！222");
				// 修改订单状态 为已付款
				// 回复支付公司
				response.getWriter().print("success");
			}
			
			//修改订单状态
			OrderService s=(OrderService) BeanFactory.getBean("OrderService");
			Order order = s.findOrderById(r6_Order);
			order.setState(1);
			
			s.update(order);
			
		} else {
			// 数据无效
			System.out.println("数据被篡改！");
		}
		
		
		return "/jsp/msg.jsp";
		
	}

}
