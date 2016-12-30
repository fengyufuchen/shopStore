package com.itheima.web.dao;

import java.util.List;

import com.itheima.web.domain.Product;

public interface IProductDao {

	List<Product> findNew() throws Exception;

	List<Product> findHot() throws Exception;

	Product getProductByPid(String productId) throws Exception;

	List<Product> findByPage(int curPage, int pageSize, String cid) throws Exception;

	int getTotalCount(String cid) throws Exception;

	List<Product> findAllProduct() throws Exception;

	void addProduct(Product product) throws  Exception;

}
