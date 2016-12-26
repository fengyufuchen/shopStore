package com.itheima.web.service;

import com.itheima.web.domain.Order;
import com.itheima.web.domain.PageBean;

public interface IOrderService {

	void addOrder(Order order) throws Exception;

	PageBean findAllByPage(String uid, int currPage, int i) throws Exception;

}
