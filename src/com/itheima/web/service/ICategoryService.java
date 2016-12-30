package com.itheima.web.service;

import java.util.List;

import com.itheima.web.domain.Category;

public interface ICategoryService {

	List<Category> findAll() throws Exception;
	public void addCategory(String categoryName) throws Exception ;
	public List<Category>findAllLasted()throws Exception;
	public void deleteCategory(String cid)throws Exception;
	Category findCategoryByCid(String cid) throws Exception;

}
