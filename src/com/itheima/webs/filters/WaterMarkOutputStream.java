package com.itheima.webs.filters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class WaterMarkOutputStream extends ServletOutputStream {
	private ByteArrayOutputStream byteArrayOutputStream;

	public WaterMarkOutputStream() {
		// TODO Auto-generated constructor stub
		byteArrayOutputStream = new ByteArrayOutputStream();
	}

	

	@Override
	public void setWriteListener(WriteListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(int arg0) throws IOException {
		// TODO Auto-generated method stub
		byteArrayOutputStream.write(arg0);

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		byteArrayOutputStream.close();

	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		byteArrayOutputStream.flush();
	}
	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		byteArrayOutputStream.write(b);
	}
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		byteArrayOutputStream.write(b, off, len);
	}
	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
	}



	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

}
