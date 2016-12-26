package com.itheima.web.service;

import java.util.List;

import com.itheima.web.domain.Category;

public interface ICategoryService {

	List<Category> findAll() throws Exception;

}
