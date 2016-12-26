package com.itheima.web.service;

import java.util.List;

import com.itheima.web.domain.PageBean;
import com.itheima.web.domain.Product;

public interface IProductService {

	List<Product> findNew() throws Exception;

	List<Product> findHot() throws Exception;

	Product getByPid(String productId) throws Exception;

	PageBean<Product> findByPage(String cid, String currentPage, int pageSize) throws Exception;

}
