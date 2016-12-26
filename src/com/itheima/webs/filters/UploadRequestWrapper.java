package com.itheima.webs.filters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;




/**
 * @author lenovo
 *  这是一个自定义Request，文件上传filter使用这个自定义request，这个自定义request对原生request做了封装，主要是处理文件上传请求。
 *  
 *  当外界请求到来的时候，会被文件上传filter拦截，然后替换为这个自定义Request
 */
public class UploadRequestWrapper extends HttpServletRequestWrapper {
	private static final String MULTIPART_HEADER = "Content-type";// 文件类型头
	private boolean mutipart;// 是否是上传文件
	private Map<String, Object> params = new HashMap<>();// 保存提价数据

	public UploadRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		mutipart = request.getHeader(MULTIPART_HEADER) != null
				&& request.getHeader(MULTIPART_HEADER).startsWith("multipart/form-data");
		if (mutipart) {
			@SuppressWarnings("deprecation")
			DiskFileUpload upload = new DiskFileUpload();
			upload.setHeaderEncoding("UTF-8");//设置编码
			try {
				List<FileItem> fileItems=upload.parseRequest(request);
				//解析上传数据
				for(Iterator<FileItem> it=fileItems.iterator();it.hasNext();){
					FileItem item=it.next();
					if(item.isFormField()){//如果是文本域 判断某项是否是普通的表单类型。
						params.put(item.getFieldName(), item.getString("UTF-8"));
						
					}else{
						//否则为文件域 否则该表单项是file 类型的
						String fileName=item.getName().replace("\\","/");
						fileName=fileName.substring(fileName.lastIndexOf("/")+1);
						//保存文件到系统临时文件夹中
						File file=new File(System.getProperty("java.io.tmpdir"),fileName);
						OutputStream ous=new FileOutputStream(file);
						ous.write(item.get());
						ous.close();
						params.put(item.getFieldName(), file);
						
						
					}
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
		}

	}
	
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		//如果Wie文件上传，则从map中取值，支持直接获取文件对象那个
		if(mutipart&&params.containsKey(name)){
			return params.get(name);
		}
		return super.getAttribute(name);
	}
	
	
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		if(mutipart&&params.containsKey(name)){
			return params.get(name).toString();
		}
		return super.getParameter(name);
	}

}
