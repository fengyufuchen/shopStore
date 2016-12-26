package com.itheima.web.dao;

import java.util.List;

import com.itheima.web.domain.Order;
import com.itheima.web.domain.OrderItem;

public interface IOrderDao {
	/*
	 * 添加一条订单
	 */
	void addOrder(Order order) throws Exception;

	/*
	 * 
	 */
	void addOrderItem(OrderItem or) throws Exception;

	public List<Order> findAllByPage(String uid) throws Exception;

	public int getOrderCount(String uid) throws Exception;

}
