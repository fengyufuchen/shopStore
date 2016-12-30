package com.itheima.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.web.domain.Category;

public interface ICategoryDao {

	List<Category> findAllCategory() throws Exception;
	public void addCategory(String categoryName) throws Exception;
	void updateCategory(String cid, String cname) throws Exception;
	void deleteCategory(String cid) throws Exception;


}
