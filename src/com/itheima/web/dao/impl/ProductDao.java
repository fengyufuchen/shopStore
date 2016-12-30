package com.itheima.web.dao.impl;

import java.sql.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.web.dao.IProductDao;
import com.itheima.web.domain.Product;
import com.itheima.web.utils.DataSourceUtils;

public class ProductDao implements IProductDao {

	@Override
	public List<Product> findNew() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product  order by pdate limit 9 ";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));

	}

	@Override
	public List<Product> findHot() throws Exception {
		// TODO Auto-generated method stub

		String sql = "select * from product  where is_hot=1  order by pdate limit 9 ";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product getProductByPid(String productId) throws Exception {
		// TODO Auto-generated method stub

		String sql = "select * from product where pid=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

		return qr.query(sql, new BeanHandler<>(Product.class), productId);
	}

	@Override
	public List<Product> findByPage(int curPage, int pageSize, String cid) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?,?";

		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid, (curPage - 1) * pageSize, pageSize);
	}

	/*
	 * (non-Javadoc)当前类别的总条数
	 * 
	 * @see com.itheima.web.dao.IProductDao#getTotalCount(java.lang.String)
	 */
	@Override
	public int getTotalCount(String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from product where cid=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		return ((Long) qr.query(sql, new ScalarHandler(), cid)).intValue();

	}

	@Override
	public List<Product> findAllProduct() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product ";

		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub

		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?) ";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		qr.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), new Date(product.getPdate().getTime()), product.getIs_hot(), product.getPdesc(),
				product.getPflag(), product.getCategory().getCid());
	}

}
