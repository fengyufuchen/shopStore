package com.itheima.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.web.dao.ICategoryDao;
import com.itheima.web.dao.impl.CategoryDao;
import com.itheima.web.domain.Category;
import com.itheima.web.service.ICategoryService;
import com.itheima.web.utils.BeanFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CategoryService implements ICategoryService {
	ICategoryDao categoryDao = (ICategoryDao) BeanFactory.getBean("CategoryDao");

	// 创建缓存管理器
	CacheManager manager = CacheManager
			.create(CategoryService.class.getClassLoader().getResourceAsStream("ehcache.xml"));

	// 获取指定的缓存
	// 通过指定的缓存获取数据。

	@Override
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
		String sql = "select  ";
		QueryRunner qr = new QueryRunner();
		Cache cache = manager.getCache("categoryCache");
		Element element = cache.get("cList");
		if (element == null) {
			// 从数据库中获取，然后将list放入缓存，
			System.out.println("缓存中没有数据，需要到数据库中查询，并放入缓存");

			list = categoryDao.findAllCategory();
			cache.put(new Element("cList", list));

		} else {
			// 直接返回就可以
			list = (List<Category>) element.getObjectValue();
			System.out.println("直接从缓存中读取缓存数据");
			
		}

		return list;
	}

	public static void main(String[] args) {
		CategoryService.class.getClassLoader().getResourceAsStream("ehcache.xml");
		System.out.println(CategoryService.class.getClassLoader().getResourceAsStream("ehcache.xml"));
	}

}
