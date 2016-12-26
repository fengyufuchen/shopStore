package com.itheima.web.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {

	public static Object getBean(String id) {
		System.out.println(id);

		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			System.out.println("//bean[@id='" + id + "']");
			Element ele = (Element) doc.selectSingleNode("//bean[@id='" + id + "']");

			String value = ele.attributeValue("class");
			return Class.forName(value).newInstance();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public static Object getBean2(String id) {
		// 通过id获取一个指定的实现类

		try {
			// 1.获取document对象
			Document doc = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			// 2.获取指定的bean对象 xpath
			Element ele = (Element) doc.selectSingleNode("//bean[@id='" + id + "']");

			// 3.获取bean对象的class属性
			String value = ele.attributeValue("class");

			// 4.反射
			return Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(BeanFactory.getBean("CategoryService"));
	}

}
