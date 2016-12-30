package com.itheima.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.web.dao.ICategoryDao;
import com.itheima.web.domain.Category;
import com.itheima.web.utils.DataSourceUtils;
import com.itheima.web.utils.UUIDUtils;

public class CategoryDao implements ICategoryDao {

	@Override
	public List<Category> findAllCategory() throws Exception {
		// TODO Auto-generated method stub

		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<>(Category.class));

	}

	public void addCategory(String categoryName) throws Exception {
		if (categoryName == null)
			return;

		String sql = "insert into category values(?,?)";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		qr.update(sql, UUIDUtils.getId(), categoryName);
	}

	public static void main(String[] args) {

		try {
			new CategoryDao().addCategory("图书");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateCategory(String cid, String cname) throws Exception {
		// TODO Auto-generated method stub
		String sql="update category set cname=? where cid=?";
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		
		qr.update(sql, cname,cid);
		
	}

	@Override
	public void deleteCategory(String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from category where cid=?";
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		
		qr.update(sql, cid);
	}

}
