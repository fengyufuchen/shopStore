package com.itheima.webs.filters;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.itheima.web.utils.ImageUtil;

public class WaterMarkResponseWrapper extends HttpServletResponseWrapper {
	private String waterMarkFile;// 水印图片位置
	private HttpServletResponse response;
	// 自定义ServletOutputStream用于缓冲图像数据
	private WaterMarkOutputStream waterMarkOutputStream = new WaterMarkOutputStream();

	public WaterMarkResponseWrapper(HttpServletResponse response, String waterMarkFile) {
		super(response);
		// TODO Auto-generated constructor stub
		this.waterMarkFile = waterMarkFile;
		this.response = response;
		

	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub

		return waterMarkOutputStream;
	}

	public void finishResponse() {
		// TODO Auto-generated method stub
		// 得到原始图片数据
		try {
			byte[] imageData = waterMarkOutputStream.getByteArrayOutputStream().toByteArray();
			byte[] image = ImageUtil.waterMark(imageData, waterMarkFile);
			response.setContentLength(image.length);
			response.getOutputStream().write(image);
			waterMarkOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
