package com.itheima.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.itheima.web.dao.IOrderDao;
import com.itheima.web.domain.Order;
import com.itheima.web.domain.OrderItem;
import com.itheima.web.domain.PageBean;
import com.itheima.web.domain.Product;
import com.itheima.web.service.IOrderService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.DataSourceUtils;

public class OrderService implements IOrderService {
	private IOrderDao orderDao = (IOrderDao) BeanFactory.getBean("OrderDao");

	@Override
	public void addOrder(Order order) throws Exception {
		// TODO Auto-generated method stub

		// 1 开始事务

		try {
			DataSourceUtils.startTransaction();
			orderDao.addOrder(order);

			for (OrderItem or : order.getListOrderItem()) {
				orderDao.addOrderItem(or);

			}

			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}

		// 2向order中添加一条数据

	}

	public PageBean findAllByPage(String uid, int currPage, int pageSize) throws Exception {
		// currentpage pagesize list totalcount

		// 在orderDao中查询所有的订单，需要 Select * from orders where uid=? limit m,n
		// 可以查询出所有的订单
		// 还需要查询出每一个订单对应的订单项 select * from orderitem or,product pd where
		// or.pid=product.pid and or.oid=?

		return new PageBean<Order>(pageSize, orderDao.findAllByPage(uid), currPage, orderDao.getOrderCount(uid));
	}

}
