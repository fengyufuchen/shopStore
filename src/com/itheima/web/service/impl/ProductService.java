package com.itheima.web.service.impl;

import java.util.List;

import org.apache.catalina.connector.Request;

import com.itheima.web.dao.IProductDao;
import com.itheima.web.dao.impl.ProductDao;
import com.itheima.web.domain.PageBean;
import com.itheima.web.domain.Product;
import com.itheima.web.service.IProductService;
import com.itheima.web.utils.BeanFactory;

public class ProductService implements IProductService {

	IProductDao productDao = (IProductDao) BeanFactory.getBean("ProductDao");

	@Override
	public List<Product> findNew() throws Exception {
		// TODO Auto-generated method stub

		return productDao.findNew();
	}

	@Override
	public List<Product> findHot() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	/*
	 * (non-Javadoc)根据商品的id查询出指定的商品
	 * 
	 * @see com.itheima.web.service.IProductService#getByPid(java.lang.String)
	 */
	@Override
	public Product getByPid(String productId) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductByPid(productId);
	}

	/*
	 * (non-Javadoc) 按照类别查询商品
	 * 
	 * @see com.itheima.web.service.IProductService#findByPage(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public PageBean findByPage(String cid, String currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		// 当前页数据 总条数
		int curPage = Integer.valueOf(currentPage);

		List<Product> listProduct = productDao.findByPage(curPage, pageSize, cid);
		int totalCount = productDao.getTotalCount(cid);
		PageBean pageBean = new PageBean<Product>(pageSize, listProduct, curPage, totalCount);
         

		return pageBean;
	}

	@Override
	public List<Product> findAllProduct() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findAllProduct();
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.addProduct(product);
	}

}
