package com.itheima.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.web.domain.Category;

public interface ICategoryDao {

	List<Category> findAllCategory() throws Exception;

}
