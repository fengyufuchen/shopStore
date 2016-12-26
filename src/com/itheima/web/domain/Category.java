package com.itheima.web.domain;

import java.io.Serializable;

public class Category  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4661218608727994367L;
	/**
	 * 
	 */

	private String cname;
	private String cid;

	public Category(String cname, String cid) {
		super();
		this.cname = cname;
		this.cid = cid;
	}

	public Category() {

	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
