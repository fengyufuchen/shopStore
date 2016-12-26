package com.itheima.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.web.dao.ICategoryDao;
import com.itheima.web.domain.Category;
import com.itheima.web.utils.DataSourceUtils;

public class CategoryDao implements ICategoryDao {

	@Override
	public List<Category> findAllCategory() throws Exception {
		// TODO Auto-generated method stub

		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		return  qr.query(sql, new BeanListHandler<>(Category.class));

	}

}
