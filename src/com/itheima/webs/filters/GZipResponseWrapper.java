package com.itheima.webs.filters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZipResponseWrapper extends HttpServletResponseWrapper {

	private HttpServletResponse response;
	private GZIPOutputStream gzipOutputStream;

	private PrintWriter writer;

	public GZipResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		if (gzipOutputStream == null) {
			gzipOutputStream = new GZIPOutputStream(response);
		}
		return gzipOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		if (writer == null) {
			writer = new PrintWriter(new OutputStreamWriter(new GZIPOutputStream(response), "UTF-8"));
		}
		return writer;
	}

	/*
	 * 压缩之后长度会发生变化，因此将该方法内容置空(non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponseWrapper#setContentLength(int)
	 */
	@Override
	public void setContentLength(int len) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		gzipOutputStream.flush();
	}

	public void finishResponse() throws IOException {
		if (gzipOutputStream != null) {
			gzipOutputStream.close();
		}
		if (writer != null) {
			writer.close();
		}

	}

}
