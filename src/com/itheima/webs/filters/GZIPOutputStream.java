package com.itheima.webs.filters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

public class GZIPOutputStream extends ServletOutputStream {
	private HttpServletResponse response;// 原response
	private java.util.zip.GZIPOutputStream gzipOutputStream;
	private ByteArrayOutputStream byteArrayOutputStream;// 压缩之后的数据存储到ByteArrayoutputStream;

	public GZIPOutputStream(HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		this.response = response;
		byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			gzipOutputStream = new java.util.zip.GZIPOutputStream(byteArrayOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public void setWriteListener(WriteListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(int arg0) throws IOException {
		// TODO Auto-generated method stub
		gzipOutputStream.write(arg0);

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		//执行压缩并将压缩数据输出到浏览器
		gzipOutputStream.finish();
		//将压缩后的数据传输到客户端
		byte[] content=byteArrayOutputStream.toByteArray();
		
		response.addHeader("Content-Encoding", "gzip");
		response.addHeader("Content-Length", Integer.toString(content.length));
		//输出到浏览器
		ServletOutputStream out=response.getOutputStream();
		
		out.write(content);
		
		out.close();
		
	}
	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		gzipOutputStream.flush();
	}
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		gzipOutputStream.write(b, off, len);
	}
	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		gzipOutputStream.write(b);
	}
}
