package com.itheima.web.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

public class BeanFactory {

	public static Object getBean(String id) {
		System.out.println(id);

		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			System.out.println("//bean[@id='" + id + "']");
			Element ele = (Element) doc.selectSingleNode("//bean[@id='" + id + "']");

			String value = ele.attributeValue("class");
			//return Class.forName(value).newInstance();
			
			
			final Object object=Class.forName(value).newInstance();//原来的对象，该对象需要被代理
			
			Object obj=Proxy.newProxyInstance(Class.forName(value).getClassLoader(), object.getClass().getInterfaces(),new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// TODO Auto-generated method stub
					
					if(method.getName().contains("add")){
						System.out.println("拦截add方法");
						return method.invoke(object, args);
					}
					return method.invoke(object, args);
				}
			});
			return obj;
			

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
