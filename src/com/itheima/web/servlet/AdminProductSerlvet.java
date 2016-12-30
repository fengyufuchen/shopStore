package com.itheima.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.itheima.web.domain.Category;
import com.itheima.web.domain.Product;
import com.itheima.web.service.ICategoryService;
import com.itheima.web.service.IProductService;
import com.itheima.web.utils.BeanFactory;
import com.itheima.web.utils.UUIDUtils;

/**
 * Servlet implementation class AdminProductSerlvet
 */
public class AdminProductSerlvet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	IProductService productService = (IProductService) BeanFactory.getBean("ProductService");
	ICategoryService categoryService = (ICategoryService) BeanFactory.getBean("CategoryService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminProductSerlvet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void findAllProduct(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Product> list = productService.findAllProduct();

			if (list == null) {
				list = new ArrayList<Product>();
			}

			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/product/product_list.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addProduct(HttpServletRequest request, HttpServletResponse response) {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		/*
		 * private String pid; private String pname; private Double
		 * market_price; private Double shop_price; private String pimage;
		 * private Date pdate; private Integer is_hot = 0;// 0:非热门， 1 热门，
		 * private String pdesc; private Integer pflag = 0;// 是否下架，1表示下架 0表示没有下架
		 * private Category category;
		 */

		String pid = UUIDUtils.getId();
		Map<String, Object> mapValue = new HashMap<String, Object>();
		mapValue.put("pid", pid);
		mapValue.put("pflag", 0);
		mapValue.put("pdate", new Date(System.currentTimeMillis()));

		Category category = null;
		Product product = new Product();

		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {

				if (item.isFormField()) {
					System.out.println(item.getString());
					System.out.println(new String(item.getString().getBytes("ISO8859-1"), "UTF-8"));
					System.out.println(item.getString("UTF-8"));
					// 如果是普通表单
					if (item.getFieldName().equals("productname")) {
						mapValue.put("pname", item.getString("utf-8"));
					}
					if (item.getFieldName().equals("productmarketprice")) {

						mapValue.put("market_price", item.getString("utf-8"));
					}
					if (item.getFieldName().equals("productdescription")) {
						mapValue.put("pdesc", item.getString("utf-8"));
					}
					if (item.getFieldName().equals("productishot")) {
						mapValue.put("is_hot", item.getString("utf-8"));
					}
					if (item.getFieldName().equals("productshopprice")) {
						mapValue.put("shop_price", item.getString("utf-8"));
					}
					if (item.getFieldName().equals("productcategory")) {
						mapValue.put("cid", item.getString("utf-8"));
						String cid = item.getString("utf-8");
						if (cid.length() > 0) {
							category = categoryService.findCategoryByCid(cid);
							mapValue.put("category", category);

						}
					}

				} else {
					String inputName = item.getFieldName();// productimage
					String fileName = item.getName();
					File remoteFile = new File(new String(item.getName().getBytes(), "UTF-8"));

					File file1 = new File(this.getServletContext().getRealPath("products"), remoteFile.getName());
					file1.getParentFile().mkdirs();
					file1.createNewFile();
					InputStream ins = item.getInputStream();

					OutputStream ous = new FileOutputStream(file1);

					byte[] buffer = new byte[1024];
					int len = 0;
					try {
						while ((len = ins.read(buffer)) > -1) {
							ous.write(buffer, 0, len);

						}
						String contextPath = request.getContextPath();

						String imagePath = file1.getAbsolutePath().split("\\Store18s")[1].replaceAll("\\\\", "/");
						String pimagePath = imagePath.substring(1, imagePath.length());

						mapValue.put("pimage", pimagePath);
						System.out.println(file1.getPath());
						System.out.println("文件已经保存了:" + file1.getAbsolutePath());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		
			// product.setCategory(category);
			BeanUtils.populate(product, mapValue);
			productService.addProduct(product);
			findAllProduct(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
